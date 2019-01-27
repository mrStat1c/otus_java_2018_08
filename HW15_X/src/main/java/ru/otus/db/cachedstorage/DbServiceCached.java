package ru.otus.db.cachedstorage;

import ru.otus.db.cache.CacheEngine;
import ru.otus.db.cache.CacheHelper;
import ru.otus.db.storage.DBService;
import ru.otus.db.storage.dataSets.UserDataSet;

import java.util.List;

public class DbServiceCached implements DBService {
    private final DBService decoratedService;
    private final CacheEngine<Long, UserDataSet> cache;

    public DbServiceCached(DBService decoratedService, int cacheSize) {
        this.decoratedService = decoratedService;
        this.cache = CacheHelper.getSoftCache(cacheSize);
    }

    public DbServiceCached(DBService decoratedService, CacheEngine<Long, UserDataSet> cache) {
        this.decoratedService = decoratedService;
        this.cache = cache;
    }

    @Override
    public void save(UserDataSet dataSet) {
        decoratedService.save(dataSet); // Если ID не был заполнен, то теперь заполнился.
        cache.put(dataSet.getID(), dataSet);
    }

    @Override
    public UserDataSet read(long id) {
        return cache.getOrCalculate(id, decoratedService::read);
    }

    @Override
    public UserDataSet readByName(String name) {
        UserDataSet result = decoratedService.readByName(name);
        cache.put(result.getID(), result);
        return result;
    }

    @Override
    public List<UserDataSet> readAll() {
        List<UserDataSet> result = decoratedService.readAll();
        result.forEach(it -> cache.put(it.getID(), it));
        return result;
    }

    @Override
    public void close() throws Exception {
        cache.dispose();
        decoratedService.close();
    }
}

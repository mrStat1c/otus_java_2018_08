package ru.otus.storage;

import ru.otus.storage.dataSets.UserDataSet;

import java.util.List;

public interface DBService extends AutoCloseable {
    void save(UserDataSet dataSet);
    UserDataSet read(long id);
    UserDataSet readByName(String name);
    List<UserDataSet> readAll();
}

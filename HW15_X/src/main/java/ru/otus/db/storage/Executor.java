package ru.otus.db.storage;

public interface Executor extends AutoCloseable {
    <T extends DataSet> void save(T entry) throws StorageException;
    <T extends DataSet> T load(long id, Class<T> clazz) throws StorageException;
}

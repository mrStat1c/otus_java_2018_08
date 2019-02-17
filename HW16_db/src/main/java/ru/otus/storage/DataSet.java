package ru.otus.storage;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    public long getID() {
        return ID == null ? 0 : ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}

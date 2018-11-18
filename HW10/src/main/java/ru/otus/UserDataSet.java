package ru.otus;

public class UserDataSet extends DataSet {

    private String name;
    private int age;

    public UserDataSet(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

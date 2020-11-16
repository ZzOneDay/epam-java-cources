package com.epam.university.java.core.task062;

import java.io.Serializable;

public class SingletonObjectImpl implements SingletonObject, Serializable {
    private static SingletonObjectImpl instance = null;


    private SingletonObjectImpl() {

    }


    static SingletonObjectImpl getInstance() {
        if (instance == null) {
            return new SingletonObjectImpl();
        }
        return instance;
    }
}

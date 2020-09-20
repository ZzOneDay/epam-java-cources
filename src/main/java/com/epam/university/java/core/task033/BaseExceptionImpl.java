package com.epam.university.java.core.task033;

public class BaseExceptionImpl extends RuntimeException implements BaseException {
    BaseExceptionImpl() {
        super();
    }

    BaseExceptionImpl(String string, RuntimeException e) {
        super(string, e);
    }
}

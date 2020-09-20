package com.epam.university.java.core.task033;

public class LessExceptionImpl extends RuntimeException implements LessException {
    LessExceptionImpl() {
        super();
    }

    LessExceptionImpl(String string, RuntimeException e) {
        super(string, e);
    }
}

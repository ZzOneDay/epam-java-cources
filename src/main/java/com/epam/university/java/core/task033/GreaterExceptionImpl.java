package com.epam.university.java.core.task033;

public class GreaterExceptionImpl extends RuntimeException implements GreaterException {
    GreaterExceptionImpl() {
        super();
    }

    GreaterExceptionImpl(String string, RuntimeException e) {
        super(string, e);
    }
}

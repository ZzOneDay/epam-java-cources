package com.epam.university.java.core.task023;

public class Task023Impl implements Task023 {
    @Override
    public String extract(String phoneString) {
        if (phoneString == null) {
            throw new IllegalArgumentException();
        }

        phoneString = phoneString.replaceAll("[^0-9]+", "");

        if (phoneString.length() != 11) {
            throw new IllegalArgumentException();
        }

        return phoneString.substring(1, 4);
    }
}

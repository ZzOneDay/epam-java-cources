package com.epam.university.java.core.task051;

public class Task051Impl implements Task051 {
    @Override
    public String replace(String source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }

        if (source.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        if (source.replaceAll("the", "")
                .trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        if (source.contains("THE")) {
            throw new IllegalArgumentException();
        }

        String[] strings = source.split("\\s");
        String letters = "aeyuoj";
        boolean needToCheck = false;

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equalsIgnoreCase("the")) {
                strings[i] = "a";
                needToCheck = true;
                continue;
            }

            if (needToCheck) {
                if (letters.contains(String.valueOf(strings[i].charAt(0)))) {
                    strings[i - 1] = "an";
                }
                needToCheck = false;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString().trim();
    }
}

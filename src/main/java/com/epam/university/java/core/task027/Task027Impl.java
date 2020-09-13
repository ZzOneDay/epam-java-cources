package com.epam.university.java.core.task027;

import java.util.ArrayList;
import java.util.Collection;

public class Task027Impl implements Task027 {
    @Override
    public Collection<Integer> extract(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }

        int countCharacters = 1;
        String valueString = "";
        while (countCharacters < (sourceString.length() / 2) + 1) {
            valueString = getNumbers(sourceString, countCharacters);
            if (valueString.equals(" ERROR")) {
                countCharacters++;
                continue;
            }
            break;
        }
        String[] values = valueString.trim().split(" ");
        ArrayList<Integer> valueList = new ArrayList<>();
        for (String value : values) {
            value = value.trim();
            if (value.equals("ERROR") || value.equals("")) {
                valueList.clear();
                return valueList;
            }
            int integer = Integer.parseInt(value);
            if (integer == 0) {
                valueList.clear();
                return valueList;
            }
            valueList.add(integer);
        }
        return valueList;
    }

    private String getNumbers(String numbers, int countNumbers) {
        if (numbers.length() == countNumbers) {
            return " " + numbers;
        }

        if (numbers.equals(" ERROR")) {
            countNumbers++;
        }

        String value = "";
        String numberString = numbers.substring(0, countNumbers);
        int number = Integer.parseInt(numberString);
        int newNumber = number + 1;
        int countCharacters = String.valueOf(newNumber).length();
        if ((countCharacters + countNumbers) > numbers.length()) {
            return " ERROR";
        }
        String newNumberString = numbers.substring(countNumbers, countNumbers + countCharacters);
        int newNumberOfNewString = Integer.parseInt(newNumberString);
        if ((newNumberOfNewString - number) == 1) {
            value += " " + number;
            String result = getNumbers(numbers.substring(countNumbers), countCharacters);
            value += result;
        } else {
            return " ERROR";
        }
        return value;
    }
}

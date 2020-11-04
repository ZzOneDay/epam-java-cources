package com.epam.university.java.core.task048;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Task048Impl implements Task048 {
    @Override
    public Collection<Integer> getArmstrongNumbers(Integer from, Integer to) {
        if (from == null || to == null || from < 0 || to < 0) {
            throw new IllegalArgumentException();
        }

        List<Integer> list = new LinkedList<>();
        for (int i = from; i < to; i++) {
            String number = String.valueOf(i);
            int rank = number.length();
            String[] strings = number.split("");
            int[] numbers = getNumbersOfStrings(strings);
            int sum = getSum(numbers, rank);
            if (sum == i) {
                list.add(i);
            }
        }
        return list;
    }

    private int[] getNumbersOfStrings(String[] strings) {
        int[] numbers = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            numbers[i] = Integer.parseInt(strings[i]);
        }
        return numbers;
    }

    private int getSum(int[] numbers, int rank) {
        int sum = 0;
        for (int number : numbers) {
            sum += Math.pow(number, rank);
        }
        return sum;
    }
}

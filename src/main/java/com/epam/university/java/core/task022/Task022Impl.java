package com.epam.university.java.core.task022;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Task022Impl implements Task022 {
    @Override
    public int maxSum(Collection<Integer> numbers) {
        if (!isCorrectList(numbers)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> numbersList = new ArrayList<>(numbers);
        Collections.sort(numbersList);
        numbersList.remove(0);
        return getSumOfList(numbersList);
    }

    @Override
    public int minSum(Collection<Integer> numbers) {
        if (!isCorrectList(numbers)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> numbersList = new ArrayList<>(numbers);
        Collections.sort(numbersList);
        Collections.reverse(numbersList);
        numbersList.remove(0);
        return getSumOfList(numbersList);
    }

    private int getSumOfList(ArrayList<Integer> list) {
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }
        return sum;
    }

    private boolean isCorrectList(Collection<Integer> numbers) {
        if (numbers == null || numbers.size() == 0) {
            return false;
        }
        return true;
    }
}

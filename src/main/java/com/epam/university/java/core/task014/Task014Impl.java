package com.epam.university.java.core.task014;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This solution generate vampire numbers.
 * @author Pavel Novikov
 */

public class Task014Impl implements Task014 {
    /**
     * Generate all numbers, and return only vampireNumbers.
     * @return vampireNumbers.
     */
    public Collection<VampireNumber> getVampireNumbers() {
        ArrayList<VampireNumber> vampireNumbers = new ArrayList<>();

        for (int i = 1000; i < 9999; i++) {
            int[] arr = getResultOfNumber(i);
            if (arr != null) {
                vampireNumbers.add(new VampireNumberImpl(arr[0], arr[1], arr[2]));
            }
        }
        return vampireNumbers;
    }

    private int[] getResultOfNumber(Integer number) {
        ArrayList<Integer> variants = getVariantsOfNumber(number);
        for (Integer value : variants) {
            int[] arr = getResultOfTest(value, number);
            if (arr != null) {
                return arr;
            }
        }
        return null;
    }


    private ArrayList<Integer> getVariantsOfNumber(Integer number) {
        ArrayList<Integer> integers = new ArrayList<>();
        String numberString = number.toString();
        int[] arr = getArrayIntegers(numberString);

        for (int i = 0; i < 4; i++) {
            swap(arr, 0, i);

            int a;
            int b = 0;
            int[] miniArr = new int[]{arr[1], arr[2], arr[3]};

            for (int j = 0; j < 6; j++) {
                a = b;
                b++;
                if (b == 3) {
                    b = 0;
                }

                swap(miniArr, a, b);
                int num1 = arr[0];
                int num2 = miniArr[0];
                int num3 = miniArr[1];
                int num4 = miniArr[2];
                Integer value = getNumberOfArray(new int[]{num1,num2,num3,num4});
                if (value.toString().length() < 4) {
                    continue;
                }
                integers.add(value);
            }
        }
        return integers;
    }

    private int[] getArrayIntegers(String numberString) {

        int[] arr = new int[numberString.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(numberString.charAt(i) + "");
        }
        return arr;
    }


    private int[] getResultOfTest(Integer numberForDivorced, Integer result) {
        String stringNumber = numberForDivorced.toString();

        if (stringNumber.length() != result.toString().length()) {
            return null;
        }

        String firstPart = stringNumber.substring(0, 2);
        String secondPart = stringNumber.substring(2);

        int number1 = Integer.parseInt(firstPart);
        int number2 = Integer.parseInt(secondPart);

        int sum = number1 * number2;
        if (sum == result) {
            return new int[]{result, number1, number2};
        }
        return null;
    }

    private Integer getNumberOfArray(int[] arr) {
        StringBuilder number = new StringBuilder();
        for (Integer integer : arr) {
            number.append(integer);
        }
        return Integer.parseInt(number.toString());
    }


    private void swap(int[] arr, int index1, int index2) {
        int a = arr[index1];
        int b = arr[index2];
        arr[index2] = a;
        arr[index1] = b;
    }
}

package com.epam.university.java.core.task052;

public class Task052Impl implements Task052 {
    private int checkResult;

    @Override
    public boolean validateCard(long number) {
        if (String.valueOf(number).length() < 14
                || String.valueOf(number).length() > 19) {
            throw new IllegalArgumentException();
        }

        String numberString = String.valueOf(number);
        checkResult = Integer.parseInt(numberString.charAt(numberString.length() - 1) + "");
        numberString = numberString.substring(0, numberString.length() - 1);
        int[] numbers = getNumbers(numberString);

        int sumOfArray = 0;

        int index = 1;
        for (int i = 0; i < numbers.length; i++) {
            if (index % 2 != 0) {
                numbers[i] = numbers[i] * 2;
                if (String.valueOf(numbers[i]).length() == 2) {
                    String longValue = String.valueOf(numbers[i]);
                    int[] values = getNumbers(longValue);
                    numbers[i] = values[0] + values[1];
                }
            }
            index++;
            sumOfArray += numbers[i];
        }

        int test = 10;
        if (String.valueOf(sumOfArray).length() > 1) {
            String longValue = String.valueOf(sumOfArray);
            int[] values = getNumbers(longValue);
            test -= values[0];
        } else {
            test -= sumOfArray;
        }

        return test == checkResult;
    }


    private int[] getNumbers(String numberString) {
        int index = numberString.length() - 1;
        int[] ints = new int[numberString.length()];
        String[] strings = numberString.split("");
        for (int i = 0; i < ints.length; i++) {
            ints[index - i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }
}

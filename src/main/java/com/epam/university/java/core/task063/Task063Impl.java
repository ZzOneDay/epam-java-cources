package com.epam.university.java.core.task063;

public class Task063Impl implements Task063 {
    @Override
    public Integer calcDigitalRoot(Integer number) {
        if (number == null) {
            throw new IllegalArgumentException();
        }

        while (number.toString().length() > 1) {
            int[] values = numbersOfNumber(number);
            int sum = 0;
            for (int value : values) {
                sum += value;
            }
            number = sum;
        }

        return number;
    }


    private int[] numbersOfNumber(Integer number) {
        String stringNumber = String.valueOf(number);
        String[] strings = stringNumber.split("");
        int[] values = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            values[i] = Integer.parseInt(strings[i]);
        }
        return values;
    }
}

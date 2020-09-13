package com.epam.university.java.core.task028;

public class Task028Impl implements Task028 {
    private int power;
    private int value;
    private int count;

    @Override
    public int getWays(int value, int power) {
        this.value = value;
        this.power = power;

        findCount(0, 1);

        return count;
    }

    private void findCount(int sumNow, int number) {
        int addToSum = (int) Math.pow(number, power);

        if (addToSum <= value - sumNow) {
            findCount(sumNow, number + 1);
            sumNow += addToSum;

            if (sumNow == value) {
                count++;
            } else if (sumNow < value) {
                findCount(sumNow, number + 1);
            }
        }
    }
}

package com.epam.university.java.core.task014;

public class VampireNumberImpl implements VampireNumber {
    private final int multiplication;
    private final int first;
    private final int second;

    VampireNumberImpl(int multiplication, int first, int second) {
        this.multiplication = multiplication;
        this.first = first;
        this.second = second;
    }

    @Override
    public int getMultiplication() {
        return multiplication;
    }

    @Override
    public int getFirst() {
        return first;
    }

    @Override
    public int getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        VampireNumber vampireNumber1 = (VampireNumber) obj;
        VampireNumber vampireNumber2 = this;
        if (this.multiplication == vampireNumber1.getMultiplication()) {
            boolean result11 = vampireNumber2.getFirst() == vampireNumber1.getFirst();
            boolean result22 = vampireNumber2.getSecond() == vampireNumber1.getSecond();

            boolean result12 = vampireNumber2.getFirst() == vampireNumber1.getSecond();
            boolean result21 = vampireNumber2.getSecond() == vampireNumber1.getFirst();

            return (result11 && result12) || (result21 || result22);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return multiplication * first * second;
    }
}

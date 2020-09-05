package com.epam.university.java.core.task015;

public class VerticalLineFunction extends Function {
    private double[] maxAndMinValueOfX;

    VerticalLineFunction(Point point) {
        maxAndMinValueOfX = new double[]{point.getX(), point.getX()};
    }

    @Override
    public double[] getMaxAndMinValueOfX() {
        return maxAndMinValueOfX;
    }

    @Override
    Double getValueY(double indexX) {
        return null;
    }
}

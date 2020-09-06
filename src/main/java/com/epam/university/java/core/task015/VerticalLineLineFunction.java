package com.epam.university.java.core.task015;

public class VerticalLineLineFunction implements Function {
    private double[] maxAndMinValueOfX;

    VerticalLineLineFunction(Point point) {
        maxAndMinValueOfX = new double[]{point.getX(), point.getX()};
    }

    @Override
    public Double getValueY(double indexX) {
        return null;
    }
}

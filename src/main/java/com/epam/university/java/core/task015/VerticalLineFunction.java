package com.epam.university.java.core.task015;

public class VerticalLineFunction extends Function {
    private double valueX;
    private double valueY;
    private double[] maxAndMinValueOfX;

    VerticalLineFunction(Point point) {
        valueX = point.getX();
        valueY = point.getY();
        maxAndMinValueOfX = new double[]{point.getX(), point.getX()};
    }

    @Override
    public double[] getMaxAndMinValueOfX() {
        return maxAndMinValueOfX;
    }

    @Override
    Double getValueY(double indexX) {
//        if (indexX == valueX) {
//            return valueY;
//        }
        return null;
    }
}

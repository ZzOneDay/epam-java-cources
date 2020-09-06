package com.epam.university.java.core.task015;

public class HorizontalLineLineFunction implements Function {
    private double valueY;
    private double[] maxAndMinValueOfX;


    HorizontalLineLineFunction(Point point1, Point point2) {
        valueY = point1.getY();
        maxAndMinValueOfX = new double[]{point1.getX(), point2.getX()};
        if (point1.equals(point2)) {
            maxAndMinValueOfX = new double[]{-100, 100};
        }
    }

    @Override
    public Double getValueY(double indexX) {
        if (!isCorrectValueX(indexX)) {
            return null;
        }
        return valueY;
    }

    private boolean isCorrectValueX(double indexX) {
        boolean test1 = maxAndMinValueOfX[0] <= indexX && indexX <= maxAndMinValueOfX[1];
        boolean test2 = maxAndMinValueOfX[0] >= indexX && indexX >= maxAndMinValueOfX[1];
        if (test1 || test2) {
            return true;
        }
        return false;
    }
}

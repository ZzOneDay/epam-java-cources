package com.epam.university.java.core.task015;

public class Function {
    private Function function;
    private double[] maxAndMinValueOfX;

    Function getLineFunction(Point point1, Point point2) {
        if (point1.getX() == point2.getX()) {
            function = new VerticalLineFunction(point1);

            return function;
        }
        if (point1.getY() == point2.getY()) {
            function = new HorizontalLineFunction(point1, point2);
            return function;
        }
        function = new OtherLineFunction(point1, point2);
        return function;
    }


    Double getValueY(double indexX) {
        return function.getValueY(indexX);
    }

    Function getOppositeLineFunction(Function function, Point point) {
        Function newFunction = null;
        if (function instanceof VerticalLineFunction) {
            newFunction = new HorizontalLineFunction(point, point);
        }

        if (function instanceof HorizontalLineFunction) {
            newFunction = new VerticalLineFunction(point);
            maxAndMinValueOfX = new double[]{point.getX(), point.getX()};
        }

        if (function instanceof OtherLineFunction) {
            OtherLineFunction oppositeLineFunction = (OtherLineFunction) function;
            newFunction = oppositeLineFunction.getPerpendicularFunction(point);
        }
        return newFunction;
    }

    public double[] getMaxAndMinValueOfX() {
        return function.getMaxAndMinValueOfX();
    }


}

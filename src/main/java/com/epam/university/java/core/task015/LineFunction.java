package com.epam.university.java.core.task015;

class LineFunction {
    private LineFunction() {
    }

    static Function getLineFunction(Point point1, Point point2) {
        if (point1.getX() == point2.getX()) {
            return new VerticalLineLineFunction(point1);
        }
        if (point1.getY() == point2.getY()) {
            return new HorizontalLineLineFunction(point1, point2);
        }
        return new OtherLineLineFunction(point1, point2);
    }

    static Function getOppositeLineFunction(Function lineFunction, Point point) {
        Function newLineFunction = null;
        if (lineFunction instanceof VerticalLineLineFunction) {
            newLineFunction = new HorizontalLineLineFunction(point, point);
        }

        if (lineFunction instanceof HorizontalLineLineFunction) {
            newLineFunction = new VerticalLineLineFunction(point);
        }

        if (lineFunction instanceof OtherLineLineFunction) {
            OtherLineLineFunction oppositeLineFunction = (OtherLineLineFunction) lineFunction;
            newLineFunction = oppositeLineFunction.getPerpendicularFunction(point);
        }
        return newLineFunction;
    }
}

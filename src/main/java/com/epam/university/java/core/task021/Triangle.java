package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;

import java.math.BigDecimal;
import java.math.MathContext;

class Triangle {
    private Point pointA;
    private Point pointB;
    private Point pointC;

    Triangle(Point pointA, Point pointB, Point pointC) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
    }

    static BigPoint getEquilateralOf2Point(Point point1, Point point2, Point otherPoint) {
        BigDecimal valueX = BigDecimal.valueOf(point2.getX())
                .subtract(BigDecimal.valueOf(point1.getX()));
        BigDecimal valueY = BigDecimal.valueOf(point2.getY())
                .subtract(BigDecimal.valueOf(point1.getY()));

        BigDecimal centralX = BigDecimal.valueOf(point1.getX())
                .add(valueX.multiply(BigDecimal.valueOf(0.5)));
        BigDecimal centralY = BigDecimal.valueOf(point1.getY())
                .add(valueY.multiply(BigDecimal.valueOf(0.5)));

        BigDecimal length = (valueX.multiply(valueX)
                .add(valueY.multiply(valueY))).sqrt(MathContext.DECIMAL128);
        BigDecimal stepX = valueX.divide(length, MathContext.DECIMAL128);
        BigDecimal stepY = valueY.divide(length, MathContext.DECIMAL128);

        BigDecimal height = BigDecimal.valueOf(3).sqrt(MathContext.DECIMAL128)
                .divide(BigDecimal.valueOf(2), MathContext.DECIMAL128)
                .multiply(length);

        BigDecimal valueXPointA = centralX.subtract(height.multiply(stepY));
        BigDecimal valueYPointA = centralY.add(height.multiply(stepX));

        BigDecimal valueXPointB = centralX.add(height.multiply(stepY));
        BigDecimal valueYPointB = centralY.subtract(height.multiply(stepX));

        BigPoint pointA = new BigPoint(valueXPointA, valueYPointA);
        BigPoint pointB = new BigPoint(valueXPointB, valueYPointB);


        double value1 = (Math.pow(pointA.getX() - otherPoint.getX(), 2)
                + Math.pow(pointA.getY() - otherPoint.getY(), 2));
        double value2 = (Math.pow(pointB.getX() - otherPoint.getX(), 2)
                + Math.pow(pointB.getY() - otherPoint.getY(), 2));

        if (value1 > value2) {
            return pointA;
        } else {
            return pointB;
        }
    }

    Angle[] getAngles() {
        Angle[] angles = new Angle[3];
        angles[0] = new Angle(pointA, countDegrees(pointA, pointB, pointC));
        angles[1] = new Angle(pointB, countDegrees(pointB, pointC, pointA));
        angles[2] = new Angle(pointC, countDegrees(pointC, pointA, pointB));
        return angles;
    }

    private double countDegrees(Point mid, Point first, Point second) {
        Vector vector1 = Vector.getVectorOf2Points(mid, first);
        Vector vector2 = Vector.getVectorOf2Points(mid, second);
        return Vector.getAngle(vector1, vector2);
    }
}

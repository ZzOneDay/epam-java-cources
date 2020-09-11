package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;

import java.util.ArrayList;
import java.util.Collection;

public class Task021Impl implements Task021 {

    @Override
    public Point calculate(Collection<Point> minePositions) {

        if (minePositions == null || minePositions.size() != 3) {
            throw new IllegalArgumentException();
        }
        ArrayList<Point> points = new ArrayList<>(minePositions);

        Point pointA = points.get(0);
        Point pointC = points.get(1);
        Point pointB = points.get(2);

        Triangle triangle = new Triangle(pointA, pointB, pointC);

        double maxAngle = 120;
        Angle[] angles = triangle.getAngles();
        for (Angle angle : angles) {
            if (angle.getDegrees() > maxAngle) {
                return angle.getPoint();
            }
        }

        BigPoint pointOfAb = Triangle.getEquilateralOf2Point(pointA, pointB, pointC);
        BigPoint pointOfBc = Triangle.getEquilateralOf2Point(pointB, pointC, pointA);


        Function function1 = Function.getFunctionOf2Points(pointOfAb, pointC);
        Function function2 = Function.getFunctionOf2Points(pointOfBc, pointA);

        return FunctionCalculator.getIntersectionPointOfLines(function1, function2);
    }
}

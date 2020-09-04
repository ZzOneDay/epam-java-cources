package com.epam.university.java.core.task015;

import java.util.ArrayList;

/**
 * Class parents for Square.
 * This program have ability for other Figures.
 * If you want create other figure you can add new methods.
 */

public class Figure {
    /**
     * Method getSquare found new Point for build figure.
     * Found new point this opposite line, and Rhombus.
     */

    private Point point1;
    private Point point2;

    Figure() {
    }

    public Figure(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public Square getSquare() {
        Point point1 = this.point1;
        Point point2 = this.point2;
        double centralPointX = ((point1.getX() + point2.getX()) / 2);
        double centralPointY = ((point1.getY() + point2.getY()) / 2);
        Point centralPoint = new PointImpl(centralPointX, centralPointY);

        Function lineFunction = new Function().getLineFunction(point1, point2);
        Function perpendicularFunction = new Function().getOppositeLineFunction(lineFunction, centralPoint);

        Rhombus rhombus = new Rhombus(point1, point2, perpendicularFunction);
        Point point3 = rhombus.getFoundedPoint();
        Point point4 = rhombus.getOppositeFoundedPoint();
        Point []orderPoints = getPointInOrder(point1, point2, point3, point4);

        return new SquareImpl(orderPoints);
    }

    private Point[] getPointInOrder(Point point1, Point point2, Point point3, Point point4) {
        if (point1 == null || point2 == null || point3 == null || point4 == null) {
            //this is not Square
            throw new IllegalArgumentException();
        }

        ArrayList<Point> pointsOld = new ArrayList<Point>();
        pointsOld.add(point1);
        pointsOld.add(point2);
        pointsOld.add(point3);
        pointsOld.add(point4);

        ArrayList<Point> pointsNew = new ArrayList<Point>();
        Point pointStart = new PointImpl(0, 0);

        Point moreClose = pointStart;
        double distance = 10000000;

        while (pointsOld.size() > 0) {
            distance = 10000000;
            for (Point point : pointsOld) {
                double someDistance = getBetweenDistance(point, moreClose);
                if (someDistance < distance) {
                    distance = someDistance;
                    moreClose = point;
                    if (pointsNew.size() == 0) {
                        break;
                    }
                }
            }
            pointsNew.add(moreClose);
            pointsOld.remove(moreClose);
        }


//        if (point1.getY() >= point2.getY() && point1.getX() <= point2.getX()) {
//            System.out.println("test1");
//            pointA = point1;
//            pointB = point2;
//            pointC = point3;
//            pointD = point4;
//            return new Point[]{pointA,pointB,pointC,pointD};
//        }
//
//        if (point1.getY() >= point2.getY() && point1.getX() >= point2.getX()) {
//            System.out.println("test2");
//            pointA = point4;
//            pointB = point1;
//            pointC = point2;
//            pointD = point3;
//        }
//
//        if (point1.getY() <= point2.getY() && point1.getX() <= point2.getX()) {
//            System.out.println("test3");
//            pointA = point3;
//            pointB = point2;
//            pointC = point1;
//            pointD = point4;
//        }
//
//        if (point1.getY() <= point2.getY() && point1.getX() >= point2.getX()) {
//            System.out.println("test4");
//            pointA = point2;
//            pointB = point3;
//            pointC = point4;
//            pointD = point1;
//        }
        if (pointsNew.size() != 4) {
            throw new IllegalArgumentException();
        }

        Point pointA = pointsNew.get(0);
        Point pointB = pointsNew.get(1);
        Point pointC = pointsNew.get(2);
        Point pointD = pointsNew.get(3);
        return new Point[]{pointA, pointB, pointC, pointD};
    }

    private Double getBetweenDistance(Point point1, Point point2) {
        double x1 = point1.getX();
        double y1 = point1.getY();

        double x2 = point2.getX();
        double y2 = point2.getY();

        return Math.sqrt(Math.abs(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1))));
    }
}



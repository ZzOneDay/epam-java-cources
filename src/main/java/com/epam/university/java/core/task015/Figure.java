package com.epam.university.java.core.task015;

import java.util.ArrayList;

/**
 * Class constructor other figure.
 * <p>
 * You can create a lot of figure, used 2 point.
 * Main idea of this class this created new Figure, and get square of 2 points.
 * </p>
 *
 * @author Pavel_Novikov
 * @since 1.0
 */

public class Figure {
    private Point point1;
    private Point point2;

    Figure() {
    }

    public Figure(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    Point getPoint1() {
        return point1;
    }

    Point getPoint2() {
        return point2;
    }


    /**
     * This method create new Square, used only 2 point.
     * <p>
     * Use 2 point, we'll create {@link Rhombus}.
     * This object will found 2 missing point for our square.
     * Use these points for create new Square.
     * And we create new Square used 4 point, because square has 4 point.
     * </p>
     *
     * @return return correct Square
     */
    public Square getSquare() {
        Point point1 = this.point1;
        Point point2 = this.point2;
        double centralPointX = ((point1.getX() + point2.getX()) / 2);
        double centralPointY = ((point1.getY() + point2.getY()) / 2);
        Point centralPoint = new PointImpl(centralPointX, centralPointY);

        Function lineFunction = new Function().getLineFunction(point1, point2);
        Function perpendicular = new Function().getOppositeLineFunction(lineFunction, centralPoint);

        Rhombus rhombus = new Rhombus(point1, point2, perpendicular);
        Point point3 = rhombus.getFoundedPoint();
        Point point4 = rhombus.getOppositeFoundedPoint();
        Point[] orderPoints = getPointInOrder(point1, point2, point3, point4);

        return new SquareImpl(orderPoints);
    }


    /**
     * Points will order.
     * <p>
     * Method will order our point in order, for create correct square.
     * Correct order is A,B,C,D. See what point and where square has in {@link SquareImpl}.
     * you can put your points in method in random order. Don't caring.
     * </p>
     * <p>
     * Method found 1st point of 0.0 that has more close distance, and Start.
     * Next point add in new List, and remove of old list.
     * Find new point is method that find more close point.
     * Throw exception when any point is null
     * or when method try to find more close point is broken
     * </p>
     *
     * @param point1 - point of Constructor.
     * @param point2 - other point of Constructor.
     * @param point3 - founded point of Rhombus.
     * @param point4 - other founded point of Rhombus.
     * @return arrays of Point, and these points have order.
     * @throws IllegalArgumentException {@link IllegalArgumentException}
     *                                  Throw exception when any point is null
     *                                  or when method try to find more close point is broken
     */
    private Point[] getPointInOrder(Point point1, Point point2, Point point3, Point point4) {
        if (point1 == null || point2 == null || point3 == null || point4 == null) {
            //this is not Square
            throw new IllegalArgumentException();
        }

        ArrayList<Point> pointsOld = new ArrayList<>();
        pointsOld.add(point1);
        pointsOld.add(point2);
        pointsOld.add(point3);
        pointsOld.add(point4);

        ArrayList<Point> pointsNew = new ArrayList<>();

        Point moreClose = new PointImpl(0, 0);
        double distance;

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



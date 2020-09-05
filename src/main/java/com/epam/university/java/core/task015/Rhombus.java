package com.epam.university.java.core.task015;

class Rhombus extends Figure {
    private Point foundedPoint;
    private Point oppositeFoundedPoint;
    private Function lineFunction;

    Rhombus(Point point1, Point point2, Function lineFunction) {
        this.lineFunction = lineFunction;
        Point[] points = getFoundedPointByFunction(point1, point2, lineFunction);
        this.foundedPoint = points[0];
        this.oppositeFoundedPoint = points[1];
    }

    Point getFoundedPoint() {
        return foundedPoint;
    }

    Point getOppositeFoundedPoint() {
        return oppositeFoundedPoint;
    }

    private Point[] getFoundedPointByFunction(Point point1, Point point2, Function lineFunction) {
        double valueHypotenuse = getValueHypotenuseIsosceles(point1, point2);

        if (lineFunction instanceof VerticalLineFunction) {
            return getFoundPointsOfVertical(point1, point2);
        }

        if (lineFunction instanceof HorizontalLineFunction) {
            return getFoundPointsOfHorizontal(point1, point2);
        }

        return getFoundedPointsOfOther(point1, point2, valueHypotenuse);
    }


    private double getValueHypotenuseIsosceles(Point point1, Point point2) {
        double distance = getBetweenDistance(point1, point2);
        return Math.sqrt((distance * distance) / 2);
    }


    private Point[] getFoundedPointsOfOther(Point point1, Point point2, double hypotenuse) {
        //Carefully. Rounding the found points
        int radius = 15;
        double centralPointX = ((point1.getX() + point2.getX()) / 2);
        Point foundPoint1 = null;
        Point foundPoint2 = null;

        for (double x = centralPointX; x <= radius; x = x + 0.001) {
            foundPoint1 = new PointImpl(x, lineFunction.getValueY(x));
            double value1 = getBetweenDistance(point1, foundPoint1);
            double value2 = getBetweenDistance(point2, foundPoint1);
            if (Math.abs(value1 - hypotenuse) < 0.0001 && Math.abs(value2 - hypotenuse) < 0.001) {
                foundPoint1 = new PointImpl(Math.round(x), Math.round(lineFunction.getValueY(x)));
                break;
            }
        }

        for (double x = centralPointX; x >= -radius; x = x - 0.001) {
            foundPoint2 = new PointImpl(x, lineFunction.getValueY(x));
            double value1 = getBetweenDistance(point1, foundPoint2);
            double value2 = getBetweenDistance(point2, foundPoint2);
            if (Math.abs(value1 - hypotenuse) < 0.0001 && Math.abs(value2 - hypotenuse) < 0.001) {
                foundPoint2 = new PointImpl(Math.round(x), Math.round(lineFunction.getValueY(x)));
                break;
            }
        }

        if (foundPoint1 == null || foundPoint2 == null) {
            throw new IllegalArgumentException();
        }

        return new Point[]{foundPoint1, foundPoint2};
    }

    private Point[] getFoundPointsOfVertical(Point point1, Point point2) {
        Point foundPoint1;
        Point foundPoint2;
        double centralPointX = ((point1.getX() + point2.getX()) / 2);
        double centralPointY = ((point1.getY() + point2.getY()) / 2);

        double distanceForNewPoints = Math.abs(point1.getX() - centralPointX);
        foundPoint1 = new PointImpl(centralPointX, (centralPointY + distanceForNewPoints));
        foundPoint2 = new PointImpl(centralPointX, (centralPointY - distanceForNewPoints));
        return new Point[]{foundPoint1, foundPoint2};
    }

    private Point[] getFoundPointsOfHorizontal(Point point1, Point point2) {
        Point foundPoint1;
        Point foundPoint2;
        double centralPointX = ((point1.getX() + point2.getX()) / 2);
        double centralPointY = ((point1.getY() + point2.getY()) / 2);

        double distanceForNewPoints = Math.abs(point1.getY() - centralPointY);
        foundPoint1 = new PointImpl(centralPointX + distanceForNewPoints, centralPointY);
        foundPoint2 = new PointImpl(centralPointX - distanceForNewPoints, centralPointY);
        return new Point[]{foundPoint1, foundPoint2};
    }

    private Double getBetweenDistance(Point point1, Point point2) {
        double x1 = point1.getX();
        double y1 = point1.getY();

        double x2 = point2.getX();
        double y2 = point2.getY();

        return Math.sqrt(Math.abs(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1))));
    }
}

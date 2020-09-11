package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Vector.
     * <p>
     * If you have 2 point, you can create vector of these points.
     * But, you need to know, what the point is first, because Vector has direction.
     * </p>
     *
     * @param point1 point that will start of new Vector.
     * @param point2 point that will end of new Vector
     * @return new Vector, this parameters.
     */
    public static Vector getVectorOf2Points(Point point1, Point point2) {
        double x = point2.getX() - point1.getX();
        double y = point2.getY() - point1.getY();
        return new Vector(x, y);
    }

    /**
     * Angle of vectors.
     * <p>
     * Vector has direction.
     * We can find angle of Vectors.
     * Be careful, this method return value in degrees.
     * </p>
     *
     * @param vector1 first vector.
     * @param vector2 second vector.
     * @return value in degrees,
     */
    public static double getAngle(Vector vector1, Vector vector2) {
        double value1 = (vector1.getX() * vector2.getX()) + (vector1.getY() * vector2.getY());
        double value2 = vector1.getLength() * vector2.getLength();
        return Math.toDegrees(Math.acos(value1 / value2));
    }
}

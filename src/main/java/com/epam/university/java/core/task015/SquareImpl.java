package com.epam.university.java.core.task015;

import java.util.ArrayList;

/**
 * Correct square, which has order in points.
 * <p>
 * If you know, where points of square, you can get correct {@link VectorSquare},
 * this class is used for find valueY of indexX.
 * If you don't have correct position of square,
 * you will have not correct vectorSquare and correct result valueY.
 * Order of points are A-B-C-D. Not necessary know where point of system coordinate,
 * main this is correct order.
 * </p>
 *
 * @author Pavel_Novikov
 * @since 1.0
 */
public class SquareImpl extends Figure implements Square {
    private final Point pointA;
    private final Point pointB;
    private final Point pointC;
    private final Point pointD;


    /**
     * Constructor correct square.
     * <p>
     * Check result and set points used order arrays of points.
     * This array was created in {@link Figure}.
     * </p>
     *
     * @param orderPoints arrays this point in order
     */
    SquareImpl(Point[] orderPoints) {
        if (orderPoints.length != 4) {
            throw new IllegalArgumentException();
        }
        this.pointA = orderPoints[0];
        this.pointB = orderPoints[1];
        this.pointC = orderPoints[2];
        this.pointD = orderPoints[3];
    }

    /**
     * Create VectorSquare of correct square.
     *
     * <p>
     * This is the main idea of this solution.
     * When you have simple square,
     * you can create system of functions that have limits.
     * and get result valueY of indexX.
     * </p>
     *
     * @return new {@link VectorSquare} that has system functions.
     */
    VectorSquare getVectorSquare() {
        ArrayList<Function> lineFunctions = new ArrayList<>();
        lineFunctions.add(LineFunction.getLineFunction(pointA, pointB));
        lineFunctions.add(LineFunction.getLineFunction(pointB, pointC));
        lineFunctions.add(LineFunction.getLineFunction(pointC, pointD));
        lineFunctions.add(LineFunction.getLineFunction(pointD, pointA));
        return new VectorSquare(lineFunctions);
    }


    public Point getFirst() {
        return super.getPoint1();
    }

    public void setFirst(Point first) {
    }

    public Point getSecond() {
        return super.getPoint2();
    }

    public void setSecond(Point second) {
    }
}

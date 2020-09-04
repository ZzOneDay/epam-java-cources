package com.epam.university.java.core.task015;

import java.util.ArrayList;

//
//    A-----------B
//    |           |
//    |           |
//    D-----------C
//
public class SquareImpl extends Figure implements Square {
    private final Point pointA;
    private final Point pointB;
    private final Point pointC;
    private final Point pointD;

    public SquareImpl(Point pointA, Point pointB, Point pointC, Point pointD) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.pointD = pointD;
    }

    public SquareImpl(Point[] orderPoints) {
        if (orderPoints.length != 4) {
            throw new IllegalArgumentException();
        }
        this.pointA = orderPoints[0];
        this.pointB = orderPoints[1];
        this.pointC = orderPoints[2];
        this.pointD = orderPoints[3];
    }

    public VectorSquare getVectorSquare() {
        ArrayList<Function> functions = new ArrayList<Function>();
        functions.add(new Function().getLineFunction(pointA, pointB));
        functions.add(new Function().getLineFunction(pointB, pointC));
        functions.add(new Function().getLineFunction(pointC, pointD));
        functions.add(new Function().getLineFunction(pointD, pointA));
        return new VectorSquare(functions);
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public Point getPointC() {
        return pointC;
    }

    public Point getPointD() {
        return pointD;
    }

    //Не нужные методы
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

    @Override
    public String toString() {
        return "SquareImpl{" +
                "pointA=" + pointA +
                ", pointB=" + pointB +
                ", pointC=" + pointC +
                ", pointD=" + pointD +
                '}';
    }
}

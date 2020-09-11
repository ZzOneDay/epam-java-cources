package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;

public class Angle {
    private Point point;
    private double degrees;

    Angle(Point point, double degrees) {
        this.point = point;
        this.degrees = degrees;
    }

    public Point getPoint() {
        return point;
    }

    double getDegrees() {
        return degrees;
    }
}

package com.epam.university.java.core.task015;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class PointImpl implements Point {
    private double x;
    private double y;

    public PointImpl(double x, double y) {
        this.x = x;
        this.y = y;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PointImpl point = (PointImpl) o;
        BigDecimal valueX = BigDecimal.valueOf(x);
        BigDecimal valueOtherX = BigDecimal.valueOf(point.getX());
        BigDecimal valueY = BigDecimal.valueOf(y).setScale(15, RoundingMode.HALF_UP);
        BigDecimal valueOther = BigDecimal.valueOf(point.y).setScale(15, RoundingMode.HALF_UP);


        return valueX.equals(valueOtherX) && valueY.equals(valueOther);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

package com.epam.university.java.core.task021;

import java.math.BigDecimal;

public class BigPoint {
    private final BigDecimal valueX;
    private final BigDecimal valueY;

    BigPoint(BigDecimal valueX, BigDecimal valueY) {
        this.valueX = valueX;
        this.valueY = valueY;
    }

    BigDecimal getValueX() {
        return valueX;
    }

    BigDecimal getValueY() {
        return valueY;
    }

    public double getX() {
        return valueX.doubleValue();
    }

    public double getY() {
        return valueY.doubleValue();
    }
}

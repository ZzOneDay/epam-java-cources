package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;

import java.math.BigDecimal;

public class Function {
    private final BigDecimal valueA;
    private final BigDecimal valueB;
    private final BigDecimal valueC;

    /**
     * Line function.
     * <p>
     * Function was created by 2 point.
     * This functions need to find point of Intersection,
     * because this point is main task of {@link Task021}.
     * </p>
     *
     * @param valueA indexA of Line Function.
     * @param valueB indexB of Line Function.
     * @param valueC indexC of Line Function.
     */
    public Function(BigDecimal valueA, BigDecimal valueB, BigDecimal valueC) {
        this.valueA = valueA;
        this.valueB = valueB;
        this.valueC = valueC;
    }

    static Function getFunctionOf2Points(BigPoint point1, Point point2) {
        BigDecimal valueA = BigDecimal.valueOf(point2.getY())
                .subtract(point1.getValueY());
        BigDecimal valueB = point1.getValueX()
                .subtract(BigDecimal.valueOf(point2.getX()));
        BigDecimal valueC = valueA.multiply(point1.getValueX())
                .add(valueB.multiply((point1.getValueY())));
        return new Function(valueA, valueB, valueC);
    }

    BigDecimal getValueA() {
        return valueA;
    }

    BigDecimal getValueB() {
        return valueB;
    }

    BigDecimal getValueC() {
        return valueC;
    }
}

package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;
import com.epam.university.java.core.task015.PointImpl;

import java.math.BigDecimal;
import java.math.MathContext;

public class FunctionCalculator {


    /**
     * Intersection of Lines.
     * <p>
     * If line is not parallel, they will intersection.
     * Intersection of lines is point. We can you this method for find this point.
     * </p>
     *
     * @param function1 first line function of {@link Function}.
     * @param function2 second line function of {@link Function}.
     * @return point of intersection lines.
     */
    public static Point getIntersectionPointOfLines(Function function1, Function function2) {
        BigDecimal a1 = function1.getValueA();
        BigDecimal b1 = function1.getValueB();
        BigDecimal c1 = function1.getValueC();

        BigDecimal a2 = function2.getValueA();
        BigDecimal b2 = function2.getValueB();
        BigDecimal c2 = function2.getValueC();

        BigDecimal value1 = b2.multiply(c1).subtract(b1.multiply(c2));
        BigDecimal value2 = a1.multiply(c2).subtract(a2.multiply(c1));
        BigDecimal value3 = a1.multiply(b2).subtract(a2.multiply(b1));

        BigDecimal x = value1.divide(value3, MathContext.DECIMAL128);
        BigDecimal y = value2.divide(value3, MathContext.DECIMAL128);
        return new PointImpl(x.doubleValue(), y.doubleValue());

    }
}

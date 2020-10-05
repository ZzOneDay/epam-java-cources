package com.epam.university.java.core.task036;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

public class IntegratorImpl implements Integrator {

    @Override
    public double integrate(double left, double right,
                            Function<Double, Double> function) {
        double step = 0.0000001;
        double sum = 0;
        while (left <= right) {
            sum += function.apply(left);
            left += step;
        }
        sum = sum / 10000000;
        BigDecimal result = new BigDecimal(sum);
        result = result.setScale(4, RoundingMode.UP);
        return result.doubleValue();
    }
}

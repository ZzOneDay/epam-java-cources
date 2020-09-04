package com.epam.university.java.core.task015;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class VectorSquare {
    ArrayList<Function> functions;

    public VectorSquare(ArrayList<Function> functions) {
        this.functions = functions;
    }

    ArrayList<Double> getValueYofX(double indexX) {
        ArrayList<Double> values = new ArrayList<>();
        for (Function function : functions) {
            Double value = function.getValueY(indexX);
            if (value != null) {
                values.add(value);
            }
        }
        Set<Double> set = new HashSet<>(values);
        values.clear();
        values.addAll(set);

        return values;
    }
}

package com.epam.university.java.core.task015;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class VectorSquare {
    private ArrayList<Function> lineFunctions;

    VectorSquare(ArrayList<Function> lineFunctions) {
        this.lineFunctions = lineFunctions;
    }

    ArrayList<Double> getValueYofX(double indexX) {
        ArrayList<Double> values = new ArrayList<>();
        for (Function lineFunction : lineFunctions) {
            Double value = lineFunction.getValueY(indexX);
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

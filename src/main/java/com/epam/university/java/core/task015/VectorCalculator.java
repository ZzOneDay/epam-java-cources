package com.epam.university.java.core.task015;

import java.util.ArrayList;
import java.util.Collections;

public class VectorCalculator {

    Double[] getValueOfIntersection(VectorSquare square1, VectorSquare square2, Double valueX) {
        ArrayList<Double> values = new ArrayList<>();
        values.addAll(square1.getValueYofX(valueX));
        values.addAll(square2.getValueYofX(valueX));
        if (values.size() == 4) {
            Collections.sort(values);
            return new Double[]{values.get(1), values.get(2)};
        }
        return null;
    }

}

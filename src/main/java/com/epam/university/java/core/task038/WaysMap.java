package com.epam.university.java.core.task038;

import java.util.ArrayList;

class WaysMap {
    private ArrayList<Vector> vectors = new ArrayList<>();

    void addVector(Vector vector) {
        vectors.add(vector);
    }

    Way getWay(Vertex vertexStart, Vertex vertexFinish) {
        Way way = new Way();
        for (Vector vector : vectors) {
            if (vector.getStart().equals(vertexStart)
                    && vector.getFinish().equals(vertexFinish)) {
                way.addVector(vector);
                return way;
            }
        }

        for (Vector vector : vectors) {
            Vertex start = vector.getStart();
            if (start.equals(vertexStart)) {
                Vertex finish = vector.getFinish();
                way.addVector(vector);
                for (Vector vector1 : vectors) {
                    if (finish.equals(vector1.getStart())
                            && vector1.getFinish().equals(vertexFinish)) {
                        way.addVector(vector);
                        return way;
                    }
                }
                way.vectors.clear();
            }
        }
        return way;
    }


}

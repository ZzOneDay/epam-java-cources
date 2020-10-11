package com.epam.university.java.core.task038;

import java.util.ArrayList;

class Way {
    ArrayList<Vector> vectors = new ArrayList<>();

    void addVector(Vector vector) {
        vectors.add(vector);
    }


    ArrayList<Vertex> getVertex() {
        ArrayList<Vertex> vertices = new ArrayList<>();
        for (Vector vector : vectors) {
            vertices.add(vector.getStart());
            vertices.add(vector.getFinish());
        }
        return vertices;
    }

}

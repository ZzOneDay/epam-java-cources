package com.epam.university.java.core.task038;

class Vector {
    private Vertex start;
    private Vertex finish;

    Vector(Vertex start, Vertex finish) {
        this.start = start;
        this.finish = finish;
    }

    Vertex getStart() {
        return start;
    }

    Vertex getFinish() {
        return finish;
    }
}

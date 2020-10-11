package com.epam.university.java.core.task038;

public class VertexImpl implements Vertex {
    private int id;
    private int x;
    private int y;

    VertexImpl(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}

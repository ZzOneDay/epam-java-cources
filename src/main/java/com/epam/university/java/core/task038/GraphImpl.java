package com.epam.university.java.core.task038;

import java.util.ArrayList;

public class GraphImpl implements Graph {
    private int size;
    private WaysMap waysMap = new WaysMap();
    private ArrayList<Vertex> vertices = new ArrayList<>();


    GraphImpl(int size) {
        this.size = size;
    }

    @Override
    public void createVertex(int id, int x, int y) {
        vertices.add(new VertexImpl(id, x, y));
    }

    @Override
    public void connectVertices(int fromId, int toId) {
        Vertex start = getVertexById(fromId);
        Vertex finish = getVertexById(toId);
        waysMap.addVector(new Vector(start, finish));
    }

    Vertex getVertexById(int id) {
        for (Vertex vertex : vertices) {
            if (vertex.getId() == id) {
                return vertex;
            }
        }
        throw new IllegalArgumentException();
    }

    public WaysMap getWaysMap() {
        return waysMap;
    }
}

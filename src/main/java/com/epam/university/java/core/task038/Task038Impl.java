package com.epam.university.java.core.task038;

import java.util.Collection;

public class Task038Impl implements Task038 {
    @Override
    public Graph invokeActions(Graph sourceGraph, Collection<GraphAction> actions) {
        if (sourceGraph == null || actions == null) {
            throw new IllegalArgumentException();
        }

        if (actions.size() == 0) {
            throw new IllegalArgumentException();
        }

        for (GraphAction graphAction : actions) {
            graphAction.run(sourceGraph);
        }

        return sourceGraph;
    }

    @Override
    public Collection<Vertex> getShortestPath(Graph graph, int startId, int endId) {
        if (graph == null) {
            throw new IllegalArgumentException();
        }

        GraphImpl graphSimple = (GraphImpl) graph;
        WaysMap waysMap = graphSimple.getWaysMap();
        Vertex vertexStart = graphSimple.getVertexById(startId);
        Vertex vertexFinish = graphSimple.getVertexById(endId);

        Way way = waysMap.getWay(vertexStart, vertexFinish);
        return way.getVertex();
    }
}

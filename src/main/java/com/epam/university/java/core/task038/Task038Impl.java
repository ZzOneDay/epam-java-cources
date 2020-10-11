package com.epam.university.java.core.task038;

import java.util.Collection;

public class Task038Impl implements Task038 {
    @Override
    public Graph invokeActions(Graph sourceGraph, Collection<GraphAction> actions) {
        for (GraphAction graphAction : actions) {
            graphAction.run(sourceGraph);
        }
        return sourceGraph;
    }

    @Override
    public Collection<Vertex> getShortestPath(Graph graph, int startId, int endId) {
        GraphImpl graphSimple = (GraphImpl) graph;
        WaysMap waysMap = graphSimple.getWaysMap();
        Vertex vertexStart = graphSimple.getVertexById(startId);
        Vertex vertexFinish = graphSimple.getVertexById(endId);

        Way way = waysMap.getWay(vertexStart, vertexFinish);
        return way.getVertex();
    }
}

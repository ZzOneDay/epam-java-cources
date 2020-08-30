package com.epam.university.java.core.task012;

import java.util.Collection;

public class Task012Impl implements Task012 {
    @Override
    public Graph invokeActions(Graph sourceGraph, Collection<GraphAction> actions) {
        if (sourceGraph == null || actions == null || actions.size() == 0) {
            throw new IllegalArgumentException();
        }
        for (GraphAction graphAction : actions) {
            graphAction.run(sourceGraph);
        }
        return sourceGraph;
    }

    @Override
    public boolean pathExists(Graph graph, int from, int to) {
        if (graph == null) {
            throw new IllegalArgumentException();
        }
        GraphImpl myGraph = (GraphImpl) graph;
        Edge edgeStart = myGraph.getEdgeFromIndex(from);
        Edge edgeFinish = myGraph.getEdgeFromIndex(to);
        return myGraph.hasConnect(edgeStart, edgeFinish);
    }
}

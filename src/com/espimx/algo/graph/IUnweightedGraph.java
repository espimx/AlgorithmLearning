package com.espimx.algo.graph;

import java.util.List;

public class IUnweightedGraph<V> extends IAbstractGraph<V> {
    public IUnweightedGraph() {
    }

    public IUnweightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    public IUnweightedGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    public IUnweightedGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }
}

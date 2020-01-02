package com.espimx.algo.graph;

import java.util.*;

public abstract class IAbstractGraph<V> implements IGraph<V> {
    //顶点集合
    protected List<V> vertices = new ArrayList<>();

    //邻接表
    protected List<List<Edge>> adjacencyList = new ArrayList<>();

    IAbstractGraph() {

    }

    IAbstractGraph(V[] vertices, int[][] edges) {
        for (V vertex : vertices) {
            addVertex(vertex);
        }
        createAdjacencyLists(edges);
    }

    IAbstractGraph(List<V> vertices, List<Edge> edges) {
        for (V vertex : vertices) {
            addVertex(vertex);
        }
        createAdjacencyLists(edges);
    }

    IAbstractGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            addVertex((V)(new Integer(i)));
        }
        createAdjacencyLists(edges);
    }

    /**
     * 使用二维数组存储的边构建连接表
     */
    private void createAdjacencyLists(int[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            //因为是无向图，所以一条边要添加两次
            addEdge(edges[i][0], edges[i][1]);
            addEdge(edges[i][1], edges[i][0]);
        }
    }

    /**
     * 使用边对象构建邻接表
     */
    private void createAdjacencyLists(List<Edge> edges) {
        for (Edge edge : edges) {
            //因为是无向图，所以一条边要添加两次
            addEdge(edge.u, edge.v);
            addEdge(edge.v, edge.u);
        }
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override
    public List<Integer> getNeighborVertices(int index) {
        List<Integer> result = new ArrayList<>();
        for (Edge e : adjacencyList.get(index)) {
            result.add(e.v);
        }
        return result;
    }

    @Override
    public int getDegree(int v) {
        return adjacencyList.get(v).size();
    }

    @Override
    public void printEdges() {
        for (int u = 0; u < adjacencyList.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Edge edge : adjacencyList.get(u)) {
                System.out.print("(" + edge.u + ", " + edge.v + ") ");
                //System.out.print("(" + getVertex(edge.u) + ", " + getVertex(edge.v) + ") ");
            }
            System.out.println();
        }
    }

    @Override
    public void clear() {
        vertices.clear();
        adjacencyList.clear();
    }

    @Override
    public boolean addVertex(V vertex) {
        //如果该顶点已经存在，则不添加
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);   //添加顶点到顶点集合中
            adjacencyList.add(new ArrayList<>()); //添加新的数组到邻接表中
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v)) && addEdge(new Edge(v, u));
    }

    @Override
    public List<Integer> dfs(int s) {
        if (s < 0 || s >= vertices.size()) {
            throw new IndexOutOfBoundsException();
        }
        List<Integer> list = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        //用来记录节点是否被访问
        boolean[] visited = new boolean[vertices.size()];
        stack.push(s);
        visited[s] = true;
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            list.add(pop);
            List<Integer> neighborVertices = getNeighborVertices(pop);
            for (Integer neighborVertex : neighborVertices) {
                //将未访问的邻居顶点压入栈中，并标记为已访问
                if (!visited[neighborVertex]) {
                    stack.push(neighborVertex);
                    visited[neighborVertex] = true;
                }
            }

            //以边插入的顺序优先，因此这里为从后往前遍历邻居顶点压入栈中
            /*for (int i = neighborVertices.size() - 1; i >= 0; i--) {
                if (!visited[neighborVertices.get(i)]) {
                    stack.push(neighborVertices.get(i));
                    visited[neighborVertices.get(i)] = true;
                }
            }*/
        }
        return list;
    }

    @Override
    public List<Integer> bfs(int s) {
        if (s < 0 || s >= vertices.size()) {
            throw new IndexOutOfBoundsException();
        }
        List<Integer> list = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        //用来记录顶点是否被访问
        boolean[] visited = new boolean[vertices.size()];
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            list.add(poll);
            //获取邻居顶点
            List<Integer> neighborVertices = getNeighborVertices(poll);
            for (Integer neighborVertex : neighborVertices) {
                //如果邻居顶点没被访问，则添加到队列中，并标记为已访问
                if (!visited[neighborVertex]) {
                    queue.add(neighborVertex);
                    visited[neighborVertex] = true;
                }
            }
        }
        return list;
    }

    /**
     * 添加一条边
     */
    private boolean addEdge(Edge e) {
        if (e.u < 0 || e.u > getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + e.u);
        }
        if (e.v < 0 || e.v > getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + e.u);
        }
        //如果邻接表中有这条边，则不添加
        if (!adjacencyList.get(e.u).contains(e)) {
            adjacencyList.get(e.u).add(e);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 图的边
     */
    public static class Edge {
        int u;   //边的起点
        int v;   //边的终点

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public boolean equals(Object o) {
            return u == ((Edge)o).u && v == ((Edge)o).v;
        }
    }
}

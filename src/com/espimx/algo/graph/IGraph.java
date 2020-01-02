package com.espimx.algo.graph;

import java.util.List;

/**
 * 图
 * @param <V> 顶点的类型
 */
public interface IGraph<V> {
    /**
     * 获取图的顶点数
     */
    int getSize();

    /**
     * 获取图的顶点
     */
    List<V> getVertices();

    /**
     * 获取指定顶点下标的顶点对象
     */
    V getVertex(int index);

    /**
     * 获取指定顶点的索引位置
     */
    int getIndex(V v);

    /**
     * 获取指定下标的顶点的邻居顶点
     */
    List<Integer> getNeighborVertices(int index);

    /**
     * 获取指定顶点下标的度
     */
    int getDegree(int v);

    /**
     * 打印边
     */
    void printEdges();

    /**
     * 清空图
     */
    void clear();

    /**
     * 添加顶点到图中，如果图中已经存在该顶点，则返回false
     */
    boolean addVertex(V vertex);

    /**
     * 添加从u到v的边到图中，
     * 如果添加成功返回true，如果u到v的边已经存在，则返回false
     */
    boolean addEdge(int u, int v);

    /**
     * 从顶点s开始进行广度优先遍历，将搜索路径存到List中
     */
    List<Integer> bfs(int s);

    /**
     * 从顶点s开始进行深度优先遍历，将搜索路径存到List中
     */
    List<Integer> dfs(int s);
}

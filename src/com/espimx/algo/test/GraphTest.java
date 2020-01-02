package com.espimx.algo.test;

import com.espimx.algo.graph.IGraph;
import com.espimx.algo.graph.IUnweightedGraph;

import java.util.List;

public class GraphTest {
    public static void main(String[] args) {
        String[] vertices = {
                "深圳", "广州", "上海", "北京", "长沙", "重庆", "成都", "拉萨"
        };
        int[][] edges = {
                {0, 1}, {0, 3},
                {1, 2}, {1, 4},
                {2, 5},
                {4, 5}, {4, 6},
                {5, 7}, {6, 7},
        };
        IGraph<String> graph = new IUnweightedGraph<>(vertices, edges);
        System.out.println("顶点数：" + graph.getSize());
        System.out.println("深圳顶点的度：" + graph.getDegree(0));
        List<Integer> neighbor = graph.getNeighborVertices(0);
        System.out.println("深圳的邻居顶点：" + neighbor);
        System.out.println("位置1的顶点为：" + graph.getVertex(1));
        System.out.println("深圳在哪个顶点：" + graph.getIndex("深圳"));
        System.out.println("输出边：");
        graph.printEdges();
        System.out.println("-------");
        List<Integer> list = graph.bfs(0);
        System.out.println(list);
        System.out.println("-------");
        List<Integer> list2 = graph.dfs(2);
        System.out.println(list2);
    }
}

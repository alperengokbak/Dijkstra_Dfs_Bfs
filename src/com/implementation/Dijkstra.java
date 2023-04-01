package com.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijkstra {
    static class Graph {
        int[][] adjMatrix;
        int vertex;

        public Graph(int[][] adjMatrix, int vertex) {
            this.adjMatrix = adjMatrix;
            this.vertex = vertex;
        }

        void addEdge(int v, int w, int weight){
            adjMatrix[v][w] = weight;
            adjMatrix[w][v] = weight;
        }
    }

    public static int getClosestVertex(int distance[], boolean visited[]){
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < distance.length; i++){
            if (distance[i] < min) {
                if(!visited[i]){
                    min = distance[i];
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    public static int[] dijkstra(Graph graph, int src){
        int[] result = new int[graph.vertex];

        boolean[] visited = new boolean[graph.vertex];

        for (int i = 0; i < graph.vertex; i++){
            result[i] = Integer.MAX_VALUE;
        }
        result[src] = 0;

        for (int i = 0; i < graph.vertex; i++){
            int closestVertex = getClosestVertex(result, visited);
            visited[closestVertex] = true;
            for (int j = 0; j < graph.vertex; j++){
                if (!visited[j]){
                    if (graph.adjMatrix[closestVertex][j] != 0){
                        int d = result[closestVertex] + graph.adjMatrix[closestVertex][j];
                        if (d < result[j]){
                            result[j] = d;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] adjMatrix = new int[6][6];
        Graph graph = new Graph(adjMatrix, 6);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 8);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 4, 9);
        graph.addEdge(1, 4, 1);
        graph.addEdge(1, 3, 3);
        graph.addEdge(4, 3, 4);
        graph.addEdge(4, 5, 6);
        graph.addEdge(3, 5, 2);

        System.out.println(Arrays.toString(dijkstra(graph, 0)));
    }
}

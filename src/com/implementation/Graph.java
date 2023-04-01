package com.implementation;


import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private final int weight;
    private final LinkedList<Integer>[] graph; // ArrayList<ArrayList<Integer>> adj []; --> x ya da y değerine ulaşmak için get methodunu kullanmamız gerekecekti.

    public Graph(int weight) {
        this.weight = weight;
        graph = new LinkedList[weight];

        for (int i = 0; i < weight; i++){
            graph[i] = new LinkedList<>();
        }
    }
    void addEdge(int vertex1, int vertex2){
        graph[vertex1].add(vertex2);
        graph[vertex2].add(vertex1);
    }

    // *********************** DFS ******************************
    List<Integer> dfsRecursive(int root){
        boolean[] visited = new boolean[this.weight];
        List<Integer> result = new ArrayList<>();
        return dfsHelper(root, visited, result);
    }

    List<Integer> dfsHelper(int root, boolean visited[], List<Integer> result){
        visited[root] = true;
        result.add(root);

        int a = 0;
        for (int i = 0; i < graph[root].size(); i++){
            a = graph[root].get(i);
            if (!visited[a]) {
                dfsHelper(a, visited, result);
            }
        }
        return result;

    }

    List<Integer> dfsIterative(int root){
        List<Integer> result = new ArrayList<>();

        boolean[] visited = new boolean[this.weight];
        Stack<Integer> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            root = stack.pop();
            if (!visited[root]){
                result.add(root);
                visited[root] = true;
            }
            LinkedList<Integer> neighbours = graph[root];
            List<Integer> unvisitedNeighbours = neighbours.stream()
                    .filter(node -> !visited[node])
                    .collect(Collectors.toList());
            Collections.reverse(unvisitedNeighbours);
            unvisitedNeighbours.forEach(stack::push);
        }
        return result;
    }

    /*List<Integer> dfsIterative(int root){
        List<Integer> result = new ArrayList<>();

        boolean[] visited = new boolean[this.weight];
        Stack<Integer> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            root = stack.pop();
            if (!visited[root]) {
                result.add(root);
                visited[root] = true;
            }
            LinkedList<Integer> neighbours = graph[root];
            for (int i = 0; i < neighbours.size(); i++){
                root = neighbours.get(i);
                if (!visited[root]) {
                    stack.push(root);
                }
            }
        }

        return result;
    }*/

    // *********************** BFS ******************************

    List<Integer> bfsIterative(int root){
        List<Integer> result = new ArrayList<>();

        boolean[] visited = new boolean[this.weight];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            root = queue.poll();
            if (!visited[root]){
                result.add(root);
                visited[root] = true;
            }
            LinkedList<Integer> neighbours = graph[root];
            List<Integer> unvisitedNeighbours = neighbours.stream()
                    .filter(node -> !visited[node])
                    .collect(Collectors.toList());
            Collections.reverse(unvisitedNeighbours);
            queue.addAll(unvisitedNeighbours);
        }
        return result;
    }



    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(2,4);

        System.out.println(graph.bfsIterative(0));
    }
}

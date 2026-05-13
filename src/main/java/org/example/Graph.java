package org.example;

import java.util.*;



public class Graph {
    private Map<Integer, Vertex> vertices = new HashMap<>();
    private Map<Integer, List<Integer>> adjList = new HashMap<>();


    public void addVertex(Vertex v) {
        vertices.put(v.getId(), v);
        adjList.put(v.getId(), new ArrayList<>());
    }


    public void addEdge(int from, int to) {
        adjList.get(from).add(to);
    }


    public void printGraph() {
        System.out.println("Graph:");
        for (int id : adjList.keySet()) {
            System.out.println("  " + id + " -> " + adjList.get(id));
        }
    }



    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }



    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS: ");
        dfsRecursive(start, visited);
        System.out.println();
    }

    private void dfsRecursive(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(current + " ");

        for (int neighbor : adjList.get(current)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public int vertexCount() { return vertices.size(); }
    public int edgeCount() {
        int count = 0;
        for (List<Integer> list : adjList.values()) count += list.size();
        return count;
    }
    public List<Integer> getNeighbors(int id) { return adjList.get(id); }
}

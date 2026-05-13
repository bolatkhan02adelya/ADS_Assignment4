package org.example;

import java.util.*;


public class Experiment {
    private List<String> results = new ArrayList<>();


    public void runTraversals(Graph g) {
        boolean small = g.vertexCount() <= 10;


        long t1 = System.nanoTime();
        if (small) g.bfs(0);
        else silentBfs(g, 0);
        long bfsTime = System.nanoTime() - t1;

        long t2 = System.nanoTime();
        if (small) g.dfs(0);
        else silentDfs(g, 0);
        long dfsTime = System.nanoTime() - t2;

        String line = String.format("  Size: %3d vertices | BFS: %,d ns | DFS: %,d ns",
                g.vertexCount(), bfsTime, dfsTime);
        results.add(line);
        System.out.println(line);
    }


    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};
        Random rnd = new Random(42);

        for (int size : sizes) {
            System.out.println("\n--- Graph with " + size + " vertices ---");
            Graph g = buildGraph(size, rnd);
            if (size <= 10) g.printGraph();
            runTraversals(g);
        }
    }


    private Graph buildGraph(int size, Random rnd) {
        Graph g = new Graph();
        for (int i = 0; i < size; i++) g.addVertex(new Vertex(i));
        for (int i = 0; i < size - 1; i++) g.addEdge(i, i + 1);
        for (int k = 0; k < size * 2; k++) {
            int from = rnd.nextInt(size);
            int to = rnd.nextInt(size);
            if (from != to) g.addEdge(from, to);
        }
        return g;
    }


    public void printResults() {
        System.out.println("\n====== Results ======");
        for (String r : results) System.out.println(r);
        System.out.println("=====================");
    }


    private void silentBfs(Graph g, int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start); queue.add(start);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int nb : g.getNeighbors(cur))
                if (!visited.contains(nb)) { visited.add(nb); queue.add(nb); }
        }
    }

    private void silentDfs(Graph g, int start) {
        silentDfsHelper(g, start, new HashSet<>());
    }
    private void silentDfsHelper(Graph g, int cur, Set<Integer> visited) {
        visited.add(cur);
        for (int nb : g.getNeighbors(cur))
            if (!visited.contains(nb)) silentDfsHelper(g, nb, visited);
    }
}

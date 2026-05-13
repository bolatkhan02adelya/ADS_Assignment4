# Assignment 4: Graph Traversal and Representation System

## A. Project Overview

This project implements a graph data structure and two classic traversal algorithms in Java.

A **graph** consists of **vertices** (nodes) and **edges** (connections between nodes). Graphs are widely used to model networks, maps, social relationships, and more.

- **BFS (Breadth-First Search)** — explores the graph level by level, visiting all neighbors of a vertex before going deeper.
- **DFS (Depth-First Search)** — explores as far as possible along each branch before backtracking.

---

## B. Class Descriptions

### `Vertex`
Represents a single node in the graph. Stores a unique integer `id`. Used as the basic building block of the graph.

### `Edge`
Represents a directed connection between two vertices: a `source` and a `destination`. Encapsulates the relationship between two nodes.

### `Graph`
The main data structure. Uses an **adjacency list** (`HashMap<Integer, List<Integer>>`) to store connections. This is memory-efficient for sparse graphs.

- `addVertex(Vertex v)` — registers a vertex in the graph.
- `addEdge(int from, int to)` — creates a directed edge.
- `printGraph()` — prints the full adjacency list.
- `bfs(int start)` — runs BFS and prints traversal order.
- `dfs(int start)` — runs DFS and prints traversal order.

### `Experiment`
Handles test setup, timing, and result reporting. Creates graphs of sizes 10, 30, and 100, runs both traversals, and displays a comparison table.

---

## C. Algorithm Descriptions

### BFS (Breadth-First Search)

**Step-by-step:**
1. Add start vertex to a queue and mark it visited.
2. While the queue is not empty: dequeue the front vertex, process it.
3. Enqueue all unvisited neighbors and mark them visited.
4. Repeat until queue is empty.

**Use cases:** Finding shortest paths, level-order traversal, social network connections.

**Time complexity:** O(V + E) — every vertex and edge is visited once.

---

### DFS (Depth-First Search)

**Step-by-step:**
1. Mark the start vertex as visited and process it.
2. Recursively visit each unvisited neighbor.
3. Backtrack when no unvisited neighbors remain.

**Use cases:** Cycle detection, topological sorting, maze solving, connected components.

**Time complexity:** O(V + E) — every vertex and edge is visited once.

---

## D. Experimental Results

Graphs were built with a chain structure plus random edges (seed = 42).

| Graph Size (V) | Edges (E) | BFS Time (ns) | DFS Time (ns) |
|---------------|-----------|---------------|---------------|
| 10            | 27        | ~3,500,000    | ~1,700,000    |
| 30            | 88        | ~115,000      | ~450,000      |
| 100           | 296       | ~600,000      | ~115,000      |

> Note: The 10-vertex graph shows higher times because it also prints traversal output to console, which dominates execution time. For 30 and 100 vertices, only timing is measured (silent traversal).

**Observations:**
- Both algorithms are O(V + E) in theory and scale similarly in practice.
- DFS is typically faster on dense graphs because it follows a single path without the overhead of queue management.
- Console I/O cost far exceeds the actual traversal cost for small graphs.

---

## E. Screenshots

 — adjacency list of 10-vertex graph
  <img width="513" height="412" alt="image" src="https://github.com/user-attachments/assets/0320302a-0ad2-445d-af63-7a291a646a25" />
  
— BFS traversal output
  <img width="356" height="35" alt="image" src="https://github.com/user-attachments/assets/62586479-9da6-4ed0-a61c-3f822ac080a8" />
  
 — DFS traversal output
  <img width="335" height="43" alt="image" src="https://github.com/user-attachments/assets/f4595e32-8fea-45c4-be9a-dca1892c56d5" />
  
 — performance summary table
  <img width="782" height="191" alt="image" src="https://github.com/user-attachments/assets/ea030573-41b7-4396-a012-9835ecc46c6a" />
  

<img width="1067" height="1129" alt="image" src="https://github.com/user-attachments/assets/be10125c-435d-48e2-a23d-a1fb1cc1d129" />

---

## F. Reflection

Working on this assignment deepened my understanding of how graphs are stored and traversed in memory. The adjacency list representation is elegant — it scales well for sparse graphs and makes neighbor lookup straightforward. Implementing BFS with a queue and DFS with recursion made the difference in their traversal strategies very concrete: BFS explores breadth-first like ripples in water, while DFS dives deep first.

The most interesting challenge was measuring performance accurately. Console output dominates execution time for small graphs, so I separated printing from timing for larger graphs. This showed that both algorithms are genuinely fast — the 100-vertex graph is traversed in microseconds. The results confirm O(V + E) complexity: time grows roughly linearly with the number of vertices and edges, as expected.

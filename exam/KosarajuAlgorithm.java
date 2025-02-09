package exam;

import java.util.*;

public class KosarajuAlgorithm {
    static class Edge {
        int src, dest;

        Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph, Scanner sc, int v, int e) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.println("Enter edges (src dest):");
        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            graph[src].add(new Edge(src, dest));
        }
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] vis) {
        vis[curr] = true;
        System.out.print(curr + " ");

        for (Edge e : graph[curr]) {
            if (!vis[e.dest]) {
                dfs(graph, e.dest, vis);
            }
        }
    }

    public static void topSort(ArrayList<Edge>[] graph, int curr, boolean[] vis, Stack<Integer> stack) {
        vis[curr] = true;

        for (Edge e : graph[curr]) {
            if (!vis[e.dest]) {
                topSort(graph, e.dest, vis, stack);
            }
        }
        stack.push(curr);
    }

    public static void kosaraju(ArrayList<Edge>[] graph, int v) {
        Stack<Integer> stack = new Stack<>();
        boolean[] vis = new boolean[v];

        // Step 1: Perform Topological Sorting
        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                topSort(graph, i, vis, stack);
            }
        }

        // Step 2: Create Transpose Graph
        ArrayList<Edge>[] transpose = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            transpose[i] = new ArrayList<>();
        }

        for (int i = 0; i < v; i++) {
            for (Edge e : graph[i]) {
                transpose[e.dest].add(new Edge(e.dest, e.src));
            }
        }

        // Step 3: DFS on Transposed Graph
        Arrays.fill(vis, false);
        System.out.println("Strongly Connected Components:");
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!vis[curr]) {
                dfs(transpose, curr, vis);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices:");
        int v = sc.nextInt();
        System.out.println("Enter number of edges:");
        int e = sc.nextInt();

        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph, sc, v, e);

        kosaraju(graph, v);
        sc.close();
    }
}


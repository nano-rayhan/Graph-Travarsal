package exam;

import java.util.*;

public class PrimsAlgorithm {
    static class Edge {
        int src, dest, wt;
        
        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph, Scanner sc, int v) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.println("Enter number of edges:");
        int edges = sc.nextInt();
        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < edges; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int wt = sc.nextInt();
            graph[src].add(new Edge(src, dest, wt));
            graph[dest].add(new Edge(dest, src, wt)); // Since it's an undirected graph
        }
    }

    public static class Pair implements Comparable<Pair> {
        int node, cost;

        public Pair(int n, int c) {
            this.node = n;
            this.cost = c;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost;
        }
    }

    public static void prims(ArrayList<Edge>[] graph, int v) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[v];
        pq.add(new Pair(0, 0));

        int mstCost = 0;
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!vis[curr.node]) {
                vis[curr.node] = true;
                mstCost += curr.cost;

                for (Edge e : graph[curr.node]) {
                    if (!vis[e.dest]) {
                        pq.add(new Pair(e.dest, e.wt));
                    }
                }
            }
        }
        System.out.println("Minimum Spanning Tree Cost: " + mstCost);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices:");
        int v = sc.nextInt();
        
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph, sc, v);
        
        prims(graph, v);
        sc.close();
    }
}
package exam;
import java.util.*;

public class DijkstraAlgorithm {

    static class Edge {
        int src, dest, wt;
        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph, Scanner sc) {
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
        }
    }

    public static class Pair implements Comparable<Pair> {
        int node, dist;

        public Pair(int n, int d) {
            this.node = n;
            this.dist = d;
        }

        @Override
        public int compareTo(Pair p2) {
            return Integer.compare(this.dist, p2.dist);
        }
    }

    public static void dijkstra(ArrayList<Edge>[] graph, int src, int v) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[v];

        for (int i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        pq.add(new Pair(src, 0));
        boolean[] vis = new boolean[v];

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            if (vis[curr.node]) continue;
            
            vis[curr.node] = true;

            for (Edge e : graph[curr.node]) {
                int u = e.src;
                int V = e.dest;
                
                if (!vis[V] && dist[u] + e.wt < dist[V]) {
                    dist[V] = dist[u] + e.wt;
                    pq.add(new Pair(V, dist[V]));
                }
            }
        }

        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < v; i++) {
            System.out.println("Node " + i + " -> " + dist[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices:");
        int v = sc.nextInt();
        
        ArrayList<Edge>[] graph = (ArrayList<Edge>[]) new ArrayList[v];
        createGraph(graph, sc);
        
        System.out.println("Enter source vertex:");
        int src = sc.nextInt();
        
        dijkstra(graph, src, v);
        sc.close();
    }
}

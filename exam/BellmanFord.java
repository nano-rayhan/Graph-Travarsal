package exam;
import java.util.*;

public class BellmanFord {
    static class Edge {
        int src, dest, wt;
        Edge(int s, int d, int w) {
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
        }
    }

    public static void bellmanFord(ArrayList<Edge>[] graph, int src, int v) {
        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < v - 1; i++) {
            for (int k = 0; k < v; k++) {
                for (Edge e : graph[k]) {
                    int u = e.src;
                    int n = e.dest;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + e.wt < dist[n]) {
                        dist[n] = dist[u] + e.wt;
                    }
                }
            }
        }

        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices:");
        int v = sc.nextInt();
        
        ArrayList<Edge>[] graph = (ArrayList<Edge>[]) new ArrayList[v];
        createGraph(graph, sc, v);
        
        System.out.println("Enter source vertex:");
        int src = sc.nextInt();
        
        bellmanFord(graph, src, v);
        sc.close();
    }
}

package exam;

import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;
    
    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
    
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class Subset {
    int parent, rank;
    
    public Subset(int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }
}

class Graph {
    private int V;
    private List<Edge> edges;

    public Graph(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    private int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    private void union(Subset[] subsets, int x, int y) {
        int rootX = find(subsets, x);
        int rootY = find(subsets, y);
        
        if (subsets[rootX].rank < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        } else if (subsets[rootX].rank > subsets[rootY].rank) {
            subsets[rootY].parent = rootX;
        } else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    public void kruskalMST() {
        List<Edge> result = new ArrayList<>();
        Collections.sort(edges);
        
        Subset[] subsets = new Subset[V];
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset(i, 0);
        }
        
        int e = 0, i = 0;
        while (e < V - 1 && i < edges.size()) {
            Edge nextEdge = edges.get(i++);
            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);
            
            if (x != y) {
                result.add(nextEdge);
                union(subsets, x, y);
                e++;
            }
        }
        
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter number of vertices:");
        int V = sc.nextInt();
        Graph g = new Graph(V);
        
        System.out.println("Enter number of edges:");
        int E = sc.nextInt();
        
        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < E; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            g.addEdge(src, dest, weight);
        }
        
        g.kruskalMST();
        sc.close();
    }
}
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
        Graph g = new Graph(4);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 3, 5);
        g.addEdge(1, 3, 15);
        g.addEdge(2, 3, 4);
        
        g.kruskalMST();
    }
}
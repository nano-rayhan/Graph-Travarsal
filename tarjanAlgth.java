import java.util.*;

class Graph {
    private int V;
    private List<Integer>[] adj;
    private int time;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
        time = 0;
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
    }

    public List<List<Integer>> tarjanSCC() {
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] stackMember = new boolean[V];
        Stack<Integer> st = new Stack<>();
        List<List<Integer>> sccList = new ArrayList<>();

        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);

        for (int i = 0; i < V; i++) {
            if (disc[i] == -1) {
                sccUtil(i, disc, low, st, stackMember, sccList);
            }
        }
        return sccList;
    }

    private void sccUtil(int u, int[] disc, int[] low, Stack<Integer> st, boolean[] stackMember, List<List<Integer>> sccList) {
        disc[u] = low[u] = time++;
        stackMember[u] = true;
        st.push(u);

        for (int v : adj[u]) {
            if (disc[v] == -1) {
                sccUtil(v, disc, low, st, stackMember, sccList);
                low[u] = Math.min(low[u], low[v]);
            } else if (stackMember[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (low[u] == disc[u]) {
            List<Integer> scc = new ArrayList<>();
            int w;
            do {
                w = st.pop();
                stackMember[w] = false;
                scc.add(w);
            } while (w != u);
            sccList.add(scc);
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        List<List<Integer>> sccs = g.tarjanSCC();
        System.out.println("Strongly Connected Components: " + sccs);
    }
}

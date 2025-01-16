import java.util.ArrayList;
public class bellmanford {
     static class Edge{
        int src;
        int dest;
        int wt;
        Edge(int s,int d, int w){
            this.src = s;
            this.dest =d;
            this.wt = w;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[],int v){
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<Edge>();
        }
        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 2, -4));

        graph[2].add(new Edge(2, 3, 2));

        graph[3].add(new Edge(3, 4, 4));

        graph[4].add(new Edge(4, 1, -1));
    }

    public static void bellmanFord(ArrayList<Edge> graph[],int src, int v){
        int dist[] = new int[v];
        for(int i=0;i<v;i++){
            if(i !=src){
                dist[i] = Integer.MAX_VALUE;
            }
        }
        for(int i=0;i<v-1;i++){
            for(int k=0;k<v;k++){
                for(int j=0;j<graph[i].size();j++){
                    Edge e = graph[i].get(j);
                    int u = e.src;
                    int n = e.dest;

                    if(dist[u] != Integer.MAX_VALUE && dist[u] + e.wt  < dist[n]){
                        dist[n] = dist[u] + e.wt;
                    }
                }
            }
        }
        for(int i=0;i<dist.length;i++){
            System.out.print(dist[i]+ " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int v =5;
        ArrayList<Edge> graph[] = new ArrayList[v];
        bellmanFord(graph, 0, v);
    }
}

import java.util.ArrayList;
import java.util.Arrays;

public class floydwarshal {
    static int INF = 99999;

    public  void floydWarshall(ArrayList<ArrayList<Integer>> graph){
        int var = graph.size();
        int[][] dist = new int[var][var];

        for(int i=0;i<var;i++){
            for(int j=0;j<var;j++){
                dist[i][j] = graph.get(i).get(i);
            }
        }

        for(int k=0;k<var;k++){
            for(int i=0;i<var;i++){
                for(int j=0;j<var;j++){
                    if(dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        printFoydWarshall(dist);

    }

     public void printFoydWarshall(int[][] dist){
        for(int i=0;i<dist.length;i++){
            for(int j=0;j<dist.length;j++){
                if(dist[i][j] == INF){
                    System.out.print("INF");
                }
                else{
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
        
    public static void main(String[] args) {
        int v = 4;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(v);

        graph.add(new ArrayList<>(Arrays.asList(0, 3, INF, 7 )));
        graph.add(new ArrayList<>(Arrays.asList(8, 0, 2, INF )));
        graph.add(new ArrayList<>(Arrays.asList(5, INF, 0, 1 )));
        graph.add(new ArrayList<>(Arrays.asList(2, INF, INF, 0 )));

        floydWarshall fw = new floydWarshall();
        fw.floydWarshall(graph);
    }
}


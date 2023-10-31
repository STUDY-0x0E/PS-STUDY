import java.util.*;
import java.io.*;

public class Main {
    static int V;
    static ArrayList<Integer>[] graph;
    static int[] color;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k++) {
            String[] VE = br.readLine().split(" ");
            V = Integer.parseInt(VE[0]);
            int E = Integer.parseInt(VE[1]);

            graph = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                String[] edge = br.readLine().split(" ");
                int u = Integer.parseInt(edge[0]) - 1;
                int v = Integer.parseInt(edge[1]) - 1;
                graph[u].add(v);
                graph[v].add(u);
            }

            color = new int[V];
            boolean isBipartite = true;

            for (int i = 0; i < V; i++) {
                if (color[i] == 0) {
                    if (!dfs(i, 1)) {
                        isBipartite = false;
                        break;
                    }
                }
            }

            System.out.println(isBipartite ? "YES" : "NO");
        }
    }

    static boolean dfs(int node, int c) {
        color[node] = c;
        for (int neighbor : graph[node]) {
            if (color[neighbor] == c) {
                return false;
            }
            if (color[neighbor] == 0 && !dfs(neighbor, 3 - c)) {
                return false;
            }
        }
        return true;
    }
}

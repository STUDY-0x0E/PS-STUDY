import java.io.*;
import java.util.*;

class Edge {
    int node;
    int weight;

    public Edge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

public class Main {
    static ArrayList<Edge>[] tree;
    static boolean[] visited;
    static int maxDistance;
    static int farthestNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(0);
            return;
        }
        tree = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[parent].add(new Edge(child, weight));
            tree[child].add(new Edge(parent, weight));
        }

        //가장 먼 노드 탐색
        DFS(1, 0);

        //가장 먼 노드에서 다시 DFS를 수행하여 지름을 찾음
        visited = new boolean[n + 1];
        DFS(farthestNode, 0);

        System.out.println(maxDistance);
    }

    private static void DFS(int node, int distance) {
        visited[node] = true;

        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        for (Edge edge : tree[node]) {
            if (!visited[edge.node]) {
                DFS(edge.node, distance + edge.weight);
            }
        }
    }
}
import java.io.*;
import java.util.*;
 
class Node {
    int to;
    int weight;

    public Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
 
public class Main {
    static ArrayList<Node>[] tree;
    static boolean[] visited;
    static int farthestNode;
    static int maxDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());
        tree = new ArrayList[V + 1];
        visited = new boolean[V + 1];

        for (int i = 1; i <= V; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i <= V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to, weight;

            while (true) {
                to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                weight = Integer.parseInt(st.nextToken());
                tree[from].add(new Node(to, weight));
            }
        }

        // 임의의 정점에서 가장 먼 정점 탐색
        dfs(1, 0);
        maxDistance = 0;
        visited = new boolean[V + 1];

        // 가장 먼 정점에서 가장 먼 정점까지의 거리 계산
        dfs(farthestNode, 0);

        System.out.println(maxDistance);
    }

    static void dfs(int node, int distance) {
        visited[node] = true;

        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        for (Node n : tree[node]) {
            if (!visited[n.to]) {
                dfs(n.to, distance + n.weight);
            }
        }
    }
}

import java.util.*;

class Edge {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static List<Edge>[] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 섬의 개수
        int M = sc.nextInt(); // 다리의 개수
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int maxWeight = 0; // 가능한 최대 중량

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt(); // 출발 섬 번호
            int to = sc.nextInt(); // 도착 섬 번호
            int weight = sc.nextInt(); // 중량 제한
            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
            maxWeight = Math.max(maxWeight, weight);
        }

        int start = sc.nextInt(); // 공장이 있는 섬 번호
        int end = sc.nextInt(); // 목적지 섬 번호

        int result = binarySearch(start, end, maxWeight);
        System.out.println(result);
    }

    static int binarySearch(int start, int end, int maxWeight) {// 이분 탐색
        int left = 1;
        int right = maxWeight;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(start, end, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }
    
    static boolean isPossible(int start, int end, int maxWeight) {// 다리를 건널 수 있는지 확인
        boolean[] visited = new boolean[graph.length];
        visited[start] = true;

        List<Integer> queue = new ArrayList<>();
        queue.add(start);

        while (!queue.isEmpty()) {//bfs
            int currentCity = queue.remove(0);

            if (currentCity == end) return true;

            for (Edge neighbor : graph[currentCity]) {// 방문하지 않았고, 현재 중량 이상의 다리를 통해 이동 가능한 경우
                if (!visited[neighbor.to] && neighbor.weight >= maxWeight) {
                    visited[neighbor.to] = true;
                    queue.add(neighbor.to);
                }
            }
        }

        return false;
    }
}

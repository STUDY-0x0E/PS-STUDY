import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static List<Edge>[] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  // 도시의 개수
        int M = sc.nextInt();  // 버스의 개수
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();   // 출발 도시 번호
            int to = sc.nextInt();     // 도착 도시 번호
            int cost = sc.nextInt();   // 버스 비용
            graph[from].add(new Edge(to, cost));
        }

        int start = sc.nextInt();  // 출발점 도시 번호
        int end = sc.nextInt();    // 도착점 도시 번호

        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {//다익스트라
            Edge current = pq.poll();
            int currentCity = current.to;
            int currentCost = current.cost;

            if (distance[currentCity] < currentCost) continue;

            for (Edge neighbor : graph[currentCity]) {
                int nextCity = neighbor.to;
                int nextCost = currentCost + neighbor.cost;

                if (nextCost < distance[nextCity]) {
                    distance[nextCity] = nextCost;
                    pq.add(new Edge(nextCity, nextCost));
                }
            }
        }

        System.out.println(distance[end]);  // 출발 도시에서 도착 도시까지의 최소 비용 출력
    }
}

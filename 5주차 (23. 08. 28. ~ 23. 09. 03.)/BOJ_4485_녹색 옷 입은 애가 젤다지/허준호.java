import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int x, y, cost;

    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());//동굴의 크기
            if (n == 0) {
                break;
            }

            int[][] cave = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(0, 0, cave[0][0]));
            dp[0][0] = cave[0][0];

            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            while (!pq.isEmpty()) {//다익스트라
                Node current = pq.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = current.x + dx[i];
                    int ny = current.y + dy[i];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {//범위 내에서
                        int newCost = current.cost + cave[nx][ny];//계산한 값이
                        if (dp[nx][ny] > newCost) {//더 작다면 갱신
                            dp[nx][ny] = newCost;
                            pq.offer(new Node(nx, ny, newCost));//이어서 진행
                        }
                    }
                }
            }

            System.out.println("Problem " + tc + ": " + dp[n - 1][n - 1]);
            tc++;
        }
    }
}

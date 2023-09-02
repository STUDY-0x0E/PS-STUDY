import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] dir = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int idx = 1;
        int n;
        while ((n = Integer.parseInt(br.readLine())) != 0) {

            int[][] cave = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            boolean[][] visited = new boolean[n][n];
            visited[0][0] = true;

            int[][] distance = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(distance[i], Integer.MAX_VALUE);
            }
            distance[0][0] = cave[0][0];

            Queue<Point> pq = new PriorityQueue<>((o1, o2) -> distance[o1.y][o1.x] - distance[o2.y][o2.x]);
            pq.offer(new Point(0, 0));

            while (!pq.isEmpty()) {

                Point curr = pq.poll();

                if (curr.x == n - 1 && curr.y == n - 1) break;
                visited[curr.y][curr.x] = true;

                // 거리 갱신
                for (int i = 0; i < 4; i++) {
                    int nx = curr.x + dir[i][0], ny = curr.y + dir[i][1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    distance[ny][nx] = Math.min(distance[ny][nx], + distance[curr.y][curr.x] + cave[ny][nx]);
                    if (!visited[ny][nx]) pq.offer(new Point(nx, ny));
                }
            }

            sb.append("Problem ").append(idx++).append(": ").append(distance[n - 1][n - 1]).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
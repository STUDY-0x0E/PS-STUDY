import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static boolean[][] cheese;
    static int n, m;
    static int time = 0;

    static int[][] dir = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        cheese = new boolean[n][m];
        for (int i = 0; i < n; i++) {

            input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                cheese[i][j] = input[j].equals("1");
            }
        }


        int prev = Integer.MAX_VALUE;
        int cnt = prev;
        while ((cnt = bfs()) > 0) {
            ++time;
            prev = cnt;
        }

        bw.write(time + "\n");
        bw.write(prev + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));

        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        int cnt = 0;

        while (!queue.isEmpty()) {

            Point curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0], ny = curr.y + dir[i][1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[ny][nx]) continue;

                visited[ny][nx] = true;
                if (cheese[ny][nx]) {
                    cheese[ny][nx] = false;
                    ++cnt;
                }

                else queue.offer(new Point(nx, ny));
            }
        }

        return cnt;
    }
}
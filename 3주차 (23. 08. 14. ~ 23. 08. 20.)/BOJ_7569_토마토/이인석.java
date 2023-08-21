import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    static class Point {
        int x;
        int y;
        int z;
        int cnt;

        public Point(int x, int y, int z, int cnt) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.cnt = cnt;
        }
    }

    static int[][] dir = {
            {0, 1, 0}, {0, -1, 0}, {-1, 0, 0}, {1, 0, 0}, {0, 0, 1}, {0, 0, -1}
    };

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int cnt = n * m * h;
        int result = 0;
        int[][][] tomato = new int[h][n][m];
        Queue<Point> queue = new LinkedList<>();

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if (tmp == -1) {
                        --cnt;
                        tomato[k][i][j] = tmp;
                    }
                    if (tmp == 1) {
                        queue.offer(new Point(j, i, k, 0));
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            if (tomato[curr.z][curr.y][curr.x] != 0) continue;
            tomato[curr.z][curr.y][curr.x] = 1;
            result = Math.max(result, curr.cnt);
            --cnt;

            for (int i = 0; i < 6; i++) {
                int nx = curr.x + dir[i][0], ny = curr.y + dir[i][1], nz = curr.z + dir[i][2];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || nz < 0 || nz >= h) continue;

                queue.offer(new Point(nx, ny, nz, curr.cnt + 1));
            }
        }

        bw.write(cnt != 0 ? "-1" : result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
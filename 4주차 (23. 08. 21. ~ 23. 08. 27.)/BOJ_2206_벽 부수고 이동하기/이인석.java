import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. summary
 *
 * 노드를 연결하는 간선이 주어졌을 때
 * BFS와 DFS로 탐색한 결과를 출력하라
 *
 * 말이 이동할 수 있는 최대 칸수 수
 *
 * 2. strategy
 *
 * 간선 리스트를 만들어서 DFS와 BFS 수행
 *
 * 3. note
 */

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    static String[] map;
    static boolean[][] visited;
    static boolean[][] breakVisited;
    static int n;
    static int m;

    static int[][] dir = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    static class Point {
        int x;
        int y;
        int cnt;
        boolean isBreak;

        public Point(int x, int y, int cnt, boolean isBreak) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.isBreak = isBreak;
        }
    }

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new String[n];
        visited = new boolean[n][m];
        breakVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().trim();
        }

        Point result = bfs();

        bw.write((result == null) ? "-1" : result.cnt + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static Point bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 1, false));

        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            if (curr.x == m - 1 && curr.y == n - 1) return curr;

            boolean isBreak = curr.isBreak;

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0], ny = curr.y + dir[i][1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

                if (!isBreak) {
                    if (visited[ny][nx]) continue;

                    if (map[ny].charAt(nx) == '1') {
                        breakVisited[ny][nx] = true;
                        queue.offer(new Point(nx, ny, curr.cnt + 1, true));
                    }
                    else {
                        visited[ny][nx] = true;
                        queue.offer(new Point(nx, ny, curr.cnt + 1, false));
                    }
                }
                else {
                    if (breakVisited[ny][nx] || map[ny].charAt(nx) == '1') continue;

                    breakVisited[ny][nx] = true;
                    queue.offer(new Point(nx, ny, curr.cnt + 1, true));
                }
            }
        }

        return null;
    }

}
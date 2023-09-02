import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 처음 아기 상어의 크기는 2
 * 자신보다 큰 물고기 : 지나갈 수 없음
 * 자신과 같은 크기의 물고기 : 지나갈 수만 있음
 * 자신보다 작은 물고기 : 먹을 수 있음
 *
 * 몸 크기만큼 먹으면 상어 크기 1 증가
 *
 * 거리가 가까운 물고기가 많으면 제일 위의 물고기
 * 그 중에서 가장 왼쪽의 물고기
 */

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    static int[][] map;

    static class Shark {
        int x;
        int y;
        int size;
        int time;
        int eat = 0;

        public Shark(int x, int y, int size, int time, int eat) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.time = time;
            this.eat = eat;
        }
    }

    // 제일 위의 물고기 / 왼쪽부터 먹음
    static int[][] dir = {
            {0, -1}, {-1, 0}, {1, 0}, {0, 1}
    };

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        Shark shark = new Shark(0, 0, 2, 0, 0);

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 9) {
                    shark.x = j;
                    shark.y = i;
                }
                else {
                    map[i][j] = tmp;
                }
            }
        }

        int time = 0;
        while ((shark = bfs(shark, n)) != null) {
            time = shark.time;
        }

        bw.write(time + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static Shark bfs(Shark shark, int n) {
        boolean[][] visited = new boolean[n][n];
        Queue<Shark> queue = new PriorityQueue<>((o1, o2) -> {
            int time = o1.time - o2.time;
            if (time == 0) {
                int height = o1.y - o2.y;
                if (height == 0) return o1.x - o2.x;
                return height;
            }

            return time;
        });

        queue.offer(shark);
        visited[shark.y][shark.x] = true;

        while (!queue.isEmpty()) {
            Shark curr = queue.poll();

            if (map[curr.y][curr.x] > 0 && map[curr.y][curr.x] < curr.size) {
                ++curr.eat;
                map[curr.y][curr.x] = 0;

                if (curr.eat == curr.size) {
                    curr.eat = 0;
                    ++curr.size;
                }

                return curr;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0], ny = curr.y + dir[i][1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[ny][nx] || map[ny][nx] > curr.size) continue;

                visited[ny][nx] = true;
                queue.offer(new Shark(nx, ny, curr.size, curr.time + 1, curr.eat));
            }
        }

        return null;
    }
}
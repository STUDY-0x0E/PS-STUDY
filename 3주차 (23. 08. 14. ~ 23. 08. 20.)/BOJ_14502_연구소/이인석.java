import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class 이인석 {

    static BufferedReader br;
    static BufferedWriter bw;

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return x + " " + y;
        }
    }

    static int[][] dir = {
            {0, 1}, {0, -1}, {-1, 0}, {1, 0}
    };

    static int n = 0;
    static int m = 0;
    static int result;

    static List<Point> viruses = new LinkedList<>(); // 바이러스 시작점
    static int[][] map; // 전체 영역 형태
    static int totalNum; // 전체 공간 갯수
    static int areaNum; // 초기 빈 공간 갯수

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        totalNum = areaNum = n * m;
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int tmp = Integer.parseInt(st.nextToken());

                if (tmp == 1) {
                    map[i][j] = 1;
                    --areaNum;
                }
                if (tmp == 2) {
                	map[i][j] = -1; 
                	viruses.add(new Point(j, i));
                }
            }
        }

        // 벽은 무조건 3개를 둬야 한다
        areaNum -= 3;
        comb(0, 0);

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void comb(int cnt, int range) {
        // 벽 3개 세우면 바이러스 전파 검사
        if (cnt >= 3) {
            result = Math.max(result, calcVirus());
            return;
        }

        for (int i = range; i < totalNum; i++) {
            int y = i / m, x = i % m;
            if (map[y][x] != 0) continue;

            map[y][x] = 1;
            comb(cnt + 1, i + 1);
            map[y][x] = 0;
        }
    }

    public static int calcVirus() {
        Queue<Point> queue = new LinkedList<>();
        for (Point virus : viruses) {
            queue.add(virus);
        }

        // 이번 시뮬레이션 맵 복사
        int[][] copyMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        int cnt = areaNum;

        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            if (copyMap[curr.y][curr.x] > 0) continue;

            copyMap[curr.y][curr.x] = 2;
            --cnt;
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0], ny = curr.y + dir[i][1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

                queue.offer(new Point(nx, ny));
            }
        }

        return cnt;
    }
}
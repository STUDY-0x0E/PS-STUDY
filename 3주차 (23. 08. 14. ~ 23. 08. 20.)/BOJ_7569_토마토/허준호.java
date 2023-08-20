import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// input
        StringTokenizer st = new StringTokenizer(br.readLine());

        int time = 0;
        int tomato = 0;
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] box = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Deque<Point> ripe = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                box[i][j] = tmp;// 1:익은 토마토, 0:익지 않은 토마토, -1:빈칸
                if (tmp == 0)
                    tomato++;
                else if (tmp == 1) {
                    ripe.add(new Point(i, j));
                    visited[i][j] = true;
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (tomato > 0) {
            Deque<Point> next = new LinkedList<>();
            while (!ripe.isEmpty()) {
                Point np = ripe.poll();
                box[np.x][np.y] = 1;

                for (int d = 0; d < 4; d++) {
                    int nx = np.x + dx[d];
                    int ny = np.y + dy[d];

                    if (nx >= 0 && nx < m && ny >= 0 && ny < n // 상자 범위 내에서
                            && !visited[nx][ny] && box[nx][ny] == 0) {// 다음 토마토 고르기
                        next.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                        tomato--;
                    }
                }
            }
            ripe = next;
            time++;
            if (tomato == 0 || next.isEmpty())
                break;
        }
        System.out.println(tomato == 0 ? time : -1);// 모두 익을 때까지의 최소 날짜
    }
}
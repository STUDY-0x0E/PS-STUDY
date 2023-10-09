import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int remainingCheese = 0;

        while (true) {
            int meltedCheese = meltCheese();
            if (meltedCheese == 0)// 치즈가 모두 녹아 없어질 때까지 반복
                break;

            remainingCheese = meltedCheese; // 한 시간 전에 남아있는 치즈 개수
            time++;
        }

        System.out.println(time);
        System.out.println(remainingCheese);
    }

    static int meltCheese() {
        visited = new boolean[N][M];
        Queue<Point> queue = new ArrayDeque<>();
        int meltedCount = 0;
        
        queue.offer(new Point(0, 0));
        visited[0][0] = true;

        // bfs로 가장자리 치즈 제거
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;

                    if (board[nx][ny] == 1) {
                        board[nx][ny] = 0;
                        meltedCount++;
                    } else {
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }

        return meltedCount;
    }
}
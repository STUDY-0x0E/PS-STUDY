import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
    static int N, M;
    static int[][] A;
    static ArrayList<Point> clouds = new ArrayList<>();
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds.add(new Point(N, 1));
        clouds.add(new Point(N, 2));
        clouds.add(new Point(N - 1, 1));
        clouds.add(new Point(N - 1, 2));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            moveClouds(d, s);//1.
            rain();//2.
            copyWater();//4.
            createClouds();//5.
        }

        int result = calculateTotalWater();
        System.out.println(result);
    }

    static void moveClouds(int d, int s) {
        ArrayList<Point> newClouds = new ArrayList<>();
        for (Point cloud : clouds) {
            int nr = (cloud.x + dr[d - 1] * s) % N;
            int nc = (cloud.y + dc[d - 1] * s) % N;
            if (nr <= 0) nr += N;
            if (nc <= 0) nc += N;
            newClouds.add(new Point(nr, nc));
        }
        clouds = newClouds;
    }

    static void rain() {
        for (Point cloud : clouds) {
            A[cloud.x][cloud.y]++;
        }
    }

    static void copyWater() {
        for (Point cloud : clouds) {
            int cnt = 0;
            for (int d = 1; d <= 7; d += 2) {
                int nr = cloud.x + dr[d];
                int nc = cloud.y + dc[d];
                if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && A[nr][nc] > 0) {
                    cnt++;
                }
            }
            A[cloud.x][cloud.y] += cnt;
        }
    }

    static void createClouds() {
        boolean[][] isCloud = new boolean[N + 1][N + 1];
        for (Point cloud : clouds) {
            isCloud[cloud.x][cloud.y] = true;
        }

        ArrayList<Point> newClouds = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (A[i][j] >= 2 && !isCloud[i][j]) {
                    newClouds.add(new Point(i, j));
                    A[i][j] -= 2;
                }
            }
        }
        clouds = newClouds; //3.
    }

    static int calculateTotalWater() {
        int totalWater = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                totalWater += A[i][j];
            }
        }
        return totalWater;
    }
}

import java.io.*;
import java.util.*;

class Point {
    int x, y, wallBreak, distance;

    public Point(int x, int y, int wallBreak, int distance) {
        this.x = x;
        this.y = y;
        this.wallBreak = wallBreak;
        this.distance = distance;
    }
}

public class Main {
    static int[] dx = {-1, 1, 0, 0};//상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] size = br.readLine().split(" ");
        int N = Integer.parseInt(size[0]);
        int M = Integer.parseInt(size[1]);

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }

        int[][][] distance = new int[N][M][2];// 최단 거리 
        for (int i = 0; i < N; i++) {//초기화
            for (int j = 0; j < M; j++) {
                distance[i][j][0] = -1; // 벽을 부수지 않은 경우
                distance[i][j][1] = -1; // 벽을 부순 경우
            }
        }

        Queue<Point> queue = new LinkedList<>();//bfs
        queue.offer(new Point(0, 0, 0, 0));
        distance[0][0][0] = 1; // 시작 위치

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // 목적지에 도달한 경우 최단 거리 출력
            if (current.x == N - 1 && current.y == M - 1) {
                System.out.println(distance[N - 1][M - 1][current.wallBreak]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // 범위를 벗어나거나 이미 방문한 경우 무시
                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;

                // 벽을 부수지 않은 경우
                if (map[nx][ny] == 0 && distance[nx][ny][current.wallBreak] == -1) {
                    queue.offer(new Point(nx, ny, current.wallBreak, distance[current.x][current.y][current.wallBreak] + 1));
                    distance[nx][ny][current.wallBreak] = distance[current.x][current.y][current.wallBreak] + 1;
                }

                // 벽을 부술 수 있는 경우
                if (map[nx][ny] == 1 && current.wallBreak == 0 && distance[nx][ny][1] == -1) {
                    queue.offer(new Point(nx, ny, 1, distance[current.x][current.y][current.wallBreak] + 1));
                    distance[nx][ny][1] = distance[current.x][current.y][current.wallBreak] + 1;
                }
            }
        }
        
        System.out.println("-1");// 도달할 수 없는 경우
    }
}

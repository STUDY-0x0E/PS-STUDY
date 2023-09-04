import java.io.*;
import java.util.*;

class Shark {
    int x, y, size, eat, time;

    public Shark(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.eat = 0;
        this.time = 0;
    }
}

class Fish {
    int x, y, dist;

    public Fish(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Main {
    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 순서
    static int[] dy = {0, 0, -1, 1}; // 상하좌우 순서

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 공간의 크기 N 입력
        map = new int[N][N];
        Shark shark = null;

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]); // 공간의 상태 입력
                if (map[i][j] == 9) {
                    shark = new Shark(i, j, 2); // 아기 상어의 초기 위치와 크기 설정
                    map[i][j] = 0; // 아기 상어가 있는 칸은 빈 칸으로 표시
                }
            }
        }

        int result = 0;
        while (true) {
            Fish target = findFish(shark); // 가장 가까운 물고기 찾기
            if (target == null) break; // 먹을 수 있는 물고기가 없으면 종료

            shark.x = target.x;
            shark.y = target.y;
            shark.eat++;
            map[target.x][target.y] = 0; // 물고기 먹기

            if (shark.eat == shark.size) {
                shark.size++;
                shark.eat = 0;
            }

            result += target.dist; // 이동 거리를 결과에 추가
        }

        System.out.println(result); // 결과 출력
    }

    static Fish findFish(Shark shark) {
        boolean[][] visited = new boolean[N][N];
        Queue<Fish> queue = new LinkedList<>();
        queue.add(new Fish(shark.x, shark.y, 0));
        visited[shark.x][shark.y] = true;
        Fish target = null;
        int minDist = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {//bfs
            Fish cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] <= shark.size) {
                    visited[nx][ny] = true;
                    queue.add(new Fish(nx, ny, cur.dist + 1));

                    if (map[nx][ny] > 0 && map[nx][ny] < shark.size) {//물고기 크기가 상어보다 작은경우
                        if (cur.dist + 1 < minDist) {//최소거리 갱신
                            minDist = cur.dist + 1;
                            target = new Fish(nx, ny, cur.dist + 1);
                        } else if (cur.dist + 1 == minDist) {//맨위 또는 맨왼쪽 가장 가까운 물고기 선택
                            if (nx < target.x || (nx == target.x && ny < target.y)) {
                                target = new Fish(nx, ny, cur.dist + 1);
                            }
                        }
                    }
                }
            }
        }

        return target;
    }
}

import java.io.*;
import java.util.*;

class Point{
	int x;
	int y;
	int z;
	
	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// input
        StringTokenizer st = new StringTokenizer(br.readLine());

        int time = 0;
        int tomato = 0;
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][][] box = new int[h][m][n];
        boolean[][][] visited = new boolean[h][m][n];
        Deque<Point> ripe = new LinkedList<>();

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    box[k][i][j] = tmp;// 1:익은 토마토, 0:익지 않은 토마토, -1:빈칸
                    if (tmp == 0)
                        tomato++;
                    else if (tmp == 1) {
                        ripe.add(new Point(k, i, j));
                        visited[k][i][j] = true;
                    }
                }
            }
        }

        int[] dz = {-1, 1, 0, 0, 0, 0};
        int[] dx = {0, 0, -1, 1, 0, 0};
        int[] dy = {0, 0, 0, 0, -1, 1};

        while (tomato > 0) {
            Deque<Point> next = new LinkedList<>();
            while (!ripe.isEmpty()) {
                Point np = ripe.poll();
                box[np.x][np.y][np.z] = 1;

                for (int d = 0; d < 6; d++) {
                    int nz = np.x + dz[d];
                    int nx = np.y + dx[d];
                    int ny = np.z + dy[d];

                    if (nz >= 0 && nz < h && nx >= 0 && nx < m && ny >= 0 && ny < n // 상자 범위 내에서
                            && !visited[nz][nx][ny] && box[nz][nx][ny] == 0) {// 다음 토마토 고르기
                        next.add(new Point(nz, nx, ny));
                        visited[nz][nx][ny] = true;
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
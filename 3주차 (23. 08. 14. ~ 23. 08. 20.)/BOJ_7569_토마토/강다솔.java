package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_7569 {

	private static final int[][] direct = { { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 }, { 0, -1, 0 },
			{ 0, 1, 0 } };
	private static int N, M, H, board[][][], visited[][][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		board = new int[N][M][H];
		visited = new int[N][M][H];
		Queue<int[]> queue = new ArrayDeque<>();
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					board[i][j][k] = Integer.parseInt(st.nextToken());
					if (board[i][j][k] == 1) {
						queue.add(new int[] { i, j, k });
						visited[i][j][k] = 1;
					}
				}
			}
		}

		tomato(queue);
		int answer = getDay();
		System.out.println(answer);
	}

	private static void tomato(Queue<int[]> queue) {
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0], y = temp[1], z = temp[2];
			for (int d = 0; d < 6; d++) {
				int nx = x + direct[d][0], ny = y + direct[d][1], nz = z + direct[d][2];
				if (inRange(nx, ny, nz) && visited[nx][ny][nz] == 0 && board[nx][ny][nz] == 0) {
					visited[nx][ny][nz] = visited[x][y][z] + 1;
					board[nx][ny][nz] = 1;
					queue.offer(new int[] { nx, ny, nz });
				}
			}
		}
	}

	private static int getDay() {
		int day = 0;
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j][h] == 0)
						return -1;
					if (board[i][j][h] == 1) {
						day = Math.max(day, visited[i][j][h] - 1);
					}
				}
			}
		}
		return day;
	}

	private static boolean inRange(int x, int y, int z) {
		return 0 <= x && x < N && 0 <= y && y < M && 0 <= z && z < H;
	}

}

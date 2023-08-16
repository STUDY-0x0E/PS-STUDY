package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_7576 {

	private static final int[] dxs = { -1, 0, 1, 0 };
	private static final int[] dys = { 0, 1, 0, -1 };
	private static int N, M, board[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		int[][] visited = new int[N][M];
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) {
					queue.offer(new int[] { i, j });
					visited[i][j] = 1;
				}
			}
		}

		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0], y = temp[1];
			for (int d = 0; d < 4; d++) {
				int nx = x + dxs[d], ny = y + dys[d];
				if (inRange(nx, ny) && board[nx][ny] == 0 && visited[nx][ny] == 0) {
					visited[nx][ny] = visited[x][y] + 1;
					board[nx][ny] = 1;
					queue.offer(new int[] { nx, ny });
				}
			}
		}
		System.out.println(getAnswer(visited));

	}

	private static int getAnswer(int[][] visited) {
		int finishDay = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 0)
					return -1;
				finishDay = Math.max(finishDay, visited[i][j]);
			}
		}
		return finishDay-1;
	}

	private static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

}

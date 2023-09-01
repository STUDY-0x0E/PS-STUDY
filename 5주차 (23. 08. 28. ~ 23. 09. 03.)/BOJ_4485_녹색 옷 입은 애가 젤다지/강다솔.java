package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_4485 {

	private static int N;
	private static final int[] dxs = { -1, 0, 1, 0 };
	private static final int[] dys = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = 0;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			int[][] board = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			Queue<int[]> queue = new ArrayDeque<>();
			queue.offer(new int[] { 0, 0 });
			int[][] minLose = new int[N][N];
			for (int i = 0; i < N; i++)
				Arrays.fill(minLose[i], Integer.MAX_VALUE);
			minLose[0][0] = board[0][0];
			while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nx = cur[0] + dxs[d], ny = cur[1] + dys[d];
					if (inRange(nx, ny) && minLose[nx][ny] > minLose[cur[0]][cur[1]] + board[nx][ny]) {
						minLose[nx][ny] = minLose[cur[0]][cur[1]] + board[nx][ny];
						queue.offer(new int[] { nx, ny });
					}
				}
			}
			System.out.println("Problem " + (++test_case) + ": " + minLose[N - 1][N - 1]);
		}
	}

	public static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

}

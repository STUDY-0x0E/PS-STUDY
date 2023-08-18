package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_14502 {

	private static int N, M, board[][], answer, blankCnt;
	private static final int[] dxs = { -1, 0, 1, 0 };
	private static final int[] dys = { 0, 1, 0, -1 };
	private static int[][] walls = new int[3][2];
	private static List<int[]> blank = new ArrayList<>();

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
				if (board[i][j] == 0) {
					blank.add(new int[] { i, j });
					blankCnt++;
				}
			}
		}
		setWall(0, 0);
		System.out.println(answer);
	}

	private static void setWall(int index, int start) {
		if (index == 3) {
			// 벽을 모두 세웠을 때 안전영역의 수 구한다.
			for (int[] wall : walls) {
				board[wall[0]][wall[1]] = 1;
			}
			boolean[][] afterVirus = spreadVirus();
			answer = Math.max(answer, getSafeArea(afterVirus));
			for (int[] wall : walls) {
				board[wall[0]][wall[1]] = 0;
			}
			return;
		}

		for (int i = start; i < blankCnt; i++) {
			walls[index] = blank.get(i);
			setWall(index + 1, i + 1);
		}
	}

	private static boolean[][] spreadVirus() {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 2) {
					queue.offer(new int[] { i, j });
					visited[i][j] = true;
				}
			}
		}
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dxs[d];
				int ny = temp[1] + dys[d];
				if (inRange(nx, ny) && !visited[nx][ny] && board[nx][ny] == 0) {
					visited[nx][ny] = true;
					queue.offer(new int[] { nx, ny });
				}
			}
		}
		return visited;
	}

	private static int getSafeArea(boolean[][] visited) {
		int safe = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && board[i][j] == 0) {
					safe++;
				}
			}
		}
		return safe;
	}

	private static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

}

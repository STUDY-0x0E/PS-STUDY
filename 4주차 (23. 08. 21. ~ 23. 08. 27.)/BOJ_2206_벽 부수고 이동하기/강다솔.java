package baekjoon;

import java.util.*;
import java.io.*;

public class Baekjoon_2206 {

	private static int N, M, board[][];
	private static int[] dxs = { -1, 0, 1, 0 };
	private static int[] dys = { 0, 1, 0, -1 };

	static class MapInfo {
		int x, y, isBroken;

		MapInfo(int x, int y, int isBroken) {
			this.x = x;
			this.y = y;
			this.isBroken = isBroken;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		Queue<MapInfo> queue = new ArrayDeque<>();
		queue.offer(new MapInfo(0, 0, 0));
		int[][][] visited = new int[N][M][2];
		visited[0][0][0] = 1;
		int answer = -1;
		while (!queue.isEmpty()) {
			MapInfo cur = queue.poll();
			if (cur.x == N - 1 && cur.y == M - 1) {
				answer = visited[cur.x][cur.y][cur.isBroken];
				break;
			}
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dxs[d], ny = cur.y + dys[d];
				if (inRange(nx, ny)) {
					if (board[nx][ny] == 0 && visited[nx][ny][cur.isBroken]==0) {
						queue.offer(new MapInfo(nx, ny, cur.isBroken));
						visited[nx][ny][cur.isBroken] = visited[cur.x][cur.y][cur.isBroken] + 1;
					} else if (board[nx][ny] == 1 && cur.isBroken == 0) {
						queue.offer(new MapInfo(nx, ny, 1));
						visited[nx][ny][1] = visited[cur.x][cur.y][cur.isBroken] + 1;
					}
				}
			}
		}
		System.out.println(answer);
	}

	private static boolean inRange(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < M;
	}

	private static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				board[i][j] = temp[j] - '0';
			}
		}
	}

}

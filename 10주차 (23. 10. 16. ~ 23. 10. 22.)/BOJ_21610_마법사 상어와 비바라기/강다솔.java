package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_21610 {

	private static int N, M, board[][];
	private static Deque<int[]> cloud;
	private static Deque<int[]> rain;
	private static boolean[][] doNotMake;
	private static final int[] dxs = { 0, -1, -1, -1, 0, 1, 1, 1 };
	private static final int[] dys = { -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			rain = new ArrayDeque<>();
			moveCloudAndRain(d, s);
			copyBug();
			makeCloud();
		}
		int answer = getTotalWater();
		System.out.println(answer);
	}

	private static void moveCloudAndRain(int d, int s) {
		// 모든 구름이 d방향으로 s칸 이동
		doNotMake = new boolean[N][N];
		while (!cloud.isEmpty()) {
			int[] locate = cloud.poll();
			// 1행과 N번행, 1열과 N열 연결
			int nx = (locate[0] + s * dxs[d] + 25 * N) % N;
			int ny = (locate[1] + s * dys[d] + 25 * N) % N;

			// 이동한 곳에 비내리고 구름 사라짐
			board[nx][ny]++;
			doNotMake[nx][ny] = true;
			rain.add(new int[] { nx, ny });
		}
	}

	private static void copyBug() {
		// 물이 증가한 칸에 물복사 버그 -> 대각선 4방향에서 물이 있는 바구니 수만큼
		// 경계 벗어나면 증가 X
		while (!rain.isEmpty()) {
			int[] magic = rain.poll();
			int x = magic[0], y = magic[1];
			int count = 0;
			for (int i = 1; i < 8; i += 2) {
				// 대각선 방향의 인덱스 >> 1, 3, 5, 7
				int nx = x + dxs[i];
				int ny = y + dys[i];
				if (inRange(nx, ny) && board[nx][ny] > 0) {
					count++;
				}
			}
			board[x][y] += count;
		}
	}

	private static void makeCloud() {
		// 물의 양이 2 이상인 모든 칸에 구름 생성 (물의 양 2 감소)
		// 비가 내리고 구름이 사라진 칸은 제외
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] >= 2 && !doNotMake[i][j]) {
					cloud.add(new int[] { i, j });
					board[i][j] -= 2;
				}
			}
		}
	}

	private static int getTotalWater() {
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				total += board[i][j];
			}
		}
		return total;
	}

	private static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

	private static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cloud = new ArrayDeque<>();
		cloud.add(new int[] { N - 1, 0 });
		cloud.add(new int[] { N - 1, 1 });
		cloud.add(new int[] { N - 2, 0 });
		cloud.add(new int[] { N - 2, 1 });
	}

}

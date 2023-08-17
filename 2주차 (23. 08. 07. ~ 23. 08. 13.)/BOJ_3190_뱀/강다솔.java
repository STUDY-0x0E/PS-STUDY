package baekjoon;

import java.util.*;
import java.util.Deque;
import java.io.*;

public class baekjoon_3190 {

	private static final int[] dxs = { 0, 1, 0, -1 };
	private static final int[] dys = { 1, 0, -1, 0 };
	private static int N, board[][];
	private static Map<Integer, String> turnInfo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x - 1][y - 1] = 2;
		}

		int L = Integer.parseInt(br.readLine());
		turnInfo = new HashMap<>();
		for (int i = 0; i < L; i++) {
			String[] temp = br.readLine().split(" ");
			turnInfo.put(Integer.parseInt(temp[0]), temp[1]);
		}
		System.out.println(getPlayTime());
	}

	private static int getPlayTime() {
		Deque<int[]> deque = new ArrayDeque<>();
		deque.offerLast(new int[] { 0, 0 });
		board[0][0] = 1;
		int dir = 0, time = 0;
		while (true) {
			time++;
			if (turnInfo.containsKey(time - 1)) {
				dir = changeDirection(turnInfo.get(time - 1), dir);
			}
			int[] head = deque.peekLast();
			int nx = head[0] + dxs[dir], ny = head[1] + dys[dir];
			if (!inRange(nx, ny) || board[nx][ny] == 1) {
				return time;
			}
			if (board[nx][ny] == 0) {
				// 사과 먹지 않았다면 몸길이 줄이기
				int[] reduce = deque.pollFirst();
				board[reduce[0]][reduce[1]] = 0;
			}
			deque.offerLast(new int[] { nx, ny });
			board[nx][ny] = 1;
		}
	}

	private static int changeDirection(String c, int dir) {
		if (c.equals("L")) {
			return (dir + 4 - 1) % 4;
		} else
			return (dir + 1) % 4;
	}

	private static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

}

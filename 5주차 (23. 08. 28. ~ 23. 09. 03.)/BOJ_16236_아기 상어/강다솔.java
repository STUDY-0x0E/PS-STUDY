package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_16236 {

	private static int N, M, board[][];
	private static int shark, sharkX, sharkY, eat, answer;
	private static final int[] dxs = { -1, 0, 1, 0 };
	private static final int[] dys = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		answer = 0;
		eat = 0;

		while (true) {
			int[] fish = getFish();
			if (fish[0] == -1)
				break;
			eatFish(fish);
		}
		System.out.println(answer);
	}

	private static void eatFish(int[] fish) {
		sharkX = fish[0];
		sharkY = fish[1];
		board[fish[0]][fish[1]] = 0; // 상어 이동 후 먹기
		eat++;
		answer += fish[2];
		if (eat == shark) { // 자신의 크기만큼 물고기를 먹었을 때
			shark++;
			eat = 0;
		}
	}

	private static int[] getFish() {
		List<int[]> able = new ArrayList<>();
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { sharkX, sharkY, 0 });
		boolean[][] visited = new boolean[N][N];
		boolean flag = false;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (flag)
				break; // 먹을 물고기 존재하면 멈춤
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dxs[d], ny = cur[1] + dys[d];
				if (inRange(nx, ny) && board[nx][ny] <= shark && !visited[nx][ny]) {
					if (board[nx][ny] != 0 && board[nx][ny] < shark) {
						if (!able.isEmpty() && cur[2]+1>able.get(0)[2]) {
							// 먹을 물고기 있으며 이 다음부터는 해당 물고기보다 멀리 있을 때
							flag=true;
							break;
						}
						able.add(new int[] { nx, ny, cur[2] + 1 });
					}
					visited[nx][ny] = true;
					queue.offer(new int[] { nx, ny, cur[2] + 1 });
				}
			}
		}
		if (able.isEmpty())
			return new int[] { -1, -1 };
		return getFirstFish(able);
	}

	private static int[] getFirstFish(List<int[]> able) {
		int[] result = { Integer.MAX_VALUE, Integer.MAX_VALUE, 0 };
		for (int[] fish : able) {
			if (fish[0] < result[0]) {
				// 가장 위쪽에 있는 물고기
				result[0] = fish[0];
				result[1] = fish[1];
				result[2] = fish[2];
			} else if (fish[0] == result[0] && fish[1] < result[1]) {
				// 같은 행이면 왼쪽에 있는 물고기
				result[0] = fish[0];
				result[1] = fish[1];
				result[2] = fish[2];
			}
		}
		return result;
	}

	private static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		shark = 2; // 처음 상어 크기 2
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 9) { // 상어 일 때
					sharkX = i;
					sharkY = j;
					board[i][j] = 0;
				} else { // 물고기 일 때
					board[i][j] = temp;
				}
			}
		}
	}

}

import java.util.*;
import java.io.*;

public class Main {

	static class Tree implements Comparable<Tree> {
		int age;

		Tree(int age) {
			this.age = age;
		}

		public int compareTo(Tree other) {
			return this.age - other.age;
		}

		public boolean isFive() {
			return age % 5 == 0;
		}
	}

	private static int N, M, K, A[][];
	private static int[][] feed;
	private static final int[] dxs = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static final int[] dys = { -1, 0, 1, -1, 1, -1, 0, 1 };
	private static PriorityQueue<Tree>[][] board;
	private static ArrayList<int[]> five;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		for (int i = 0; i < K; i++) {
			spring();
			fall();
			winter();
		}
		System.out.println(getAliveNum());
	}

	private static void spring() {
		// 나무가 자신의 나이만큼 양분을 먹고 나이가 1 증가 (나이 어린 나무부터)
		// 자신의 나이만큼 양분을 먹을 수 없는 나무는 죽고 2로 나눈 값 양분으로 변환
		five = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j].isEmpty())
					continue;
				int die = 0;
				PriorityQueue<Tree> alive = new PriorityQueue<>();
				while (!board[i][j].isEmpty()) {
					Tree t = board[i][j].poll();
					if (feed[i][j] >= t.age) {
						feed[i][j] -= t.age;
						alive.offer(new Tree(t.age + 1));
						if ((t.age + 1) % 5 == 0) {
							five.add(new int[] { i, j });
						}
					} else {
						die += (t.age / 2);
					}
				}
				board[i][j] = alive;
				feed[i][j] += die;
			}
		}
	}

	private static void fall() {
		// 나이가 5의 배수인 나무 번식 -> 인접한 8칸에 나이가 1인 나무 생성
		for (int[] spread : five) {
			int x = spread[0], y = spread[1];
			for (int d = 0; d < 8; d++) {
				int nx = x + dxs[d];
				int ny = y + dys[d];
				if (inRange(nx, ny)) {
					board[nx][ny].offer(new Tree(1));
				}
			}
		}
	}

	private static void winter() {
		// 모든 땅에 A배열에 저장된 만큼의 양분 추가
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				feed[i][j] += A[i][j];
			}
		}
	}

	private static int getAliveNum() {
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result += board[i][j].size();
			}
		}
		return result;
	}

	private static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

	private static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new PriorityQueue[N][N];
		A = new int[N][N];
		feed = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				board[i][j] = new PriorityQueue<>();
				feed[i][j] = 5;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			board[x][y].offer(new Tree(z));
		}
	}

}

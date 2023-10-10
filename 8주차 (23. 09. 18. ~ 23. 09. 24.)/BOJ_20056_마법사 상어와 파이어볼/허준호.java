import java.io.*;
import java.util.*;

class FireBall {
	int m, s, d;

	public FireBall(int m, int s, int d) {
		super();
		this.m = m;
		this.s = s;
		this.d = d;
	}

}

public class Main {
	public static int N, M, K;
	public static ArrayList<FireBall>[][] map;
	public static final int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	public static final int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자 N x N
		M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		K = Integer.parseInt(st.nextToken()); // 이동 명령 횟수

		map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new FireBall(m, s, d));
		}

		for (int i = 0; i < K; i++) {
			moveFireBalls();
			splitFireBall();
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (FireBall fb : map[i][j]) {
					answer += fb.m;
				}
			}
		}
		System.out.println(answer);
	}

	private static void moveFireBalls() {
		ArrayList<FireBall>[][] newMap = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() > 0) {
					for (FireBall fb : map[i][j]) {
						int nd = fb.s % N;
						int nx = i + dx[fb.d] * nd;
						int ny = j + dy[fb.d] * nd;
						if (nx >= N) {
							nx -= N;
						} else if (nx < 0) {
							nx += N;
						}
						if (ny >= N) {
							ny -= N;
						} else if (ny < 0) {
							ny += N;
						}
						newMap[nx][ny].add(new FireBall(fb.m, fb.s, fb.d));
					}
				}
			}
		}
		map = newMap;
	}

	private static void splitFireBall() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() >= 2) {
					int massSum = 0;
					int speedSum = 0;
					boolean even = true, odd = true;

					for (FireBall fb : map[i][j]) {
						massSum += fb.m;
						speedSum += fb.s;
						if (fb.d % 2 == 0) {
							odd = false;
						} else {
							even = false;
						}
					}
					int m = massSum / 5;
					int s = speedSum / map[i][j].size();
					map[i][j].clear();
					if (m > 0) {
						for (int k = 0; k < 4; k++) {
							if (odd || even) {
								map[i][j].add(new FireBall(m, s, 0 + 2 * k));
							} else {
								map[i][j].add(new FireBall(m, s, 1 + 2 * k));
							}
						}
					}
				}
			}
		}
	}

}
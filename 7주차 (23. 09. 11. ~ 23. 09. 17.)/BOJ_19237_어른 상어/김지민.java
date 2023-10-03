import java.io.*;
import java.util.*;

public class Main {

	private static class Shark {
		int x, y; // 위치
		int d; // 현재 방향
		int[][] dirs; // 우선 순위 방향 인덱스

		Shark(int x, int y) {
			this.x = x;
			this.y = y;
			dirs = new int[5][4];
		}
	}

	private static class Map {
		int[][] area; // 상어 위치
		int[][][] smells; // 잔향 정보 [상어 번호, 냄새를 남긴 시각]

		Map(int N) {
			area = new int[N][N];
			smells = new int[N][N][2];
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 맵 크기
		int M = Integer.parseInt(st.nextToken()); // 상어 수
		int k = Integer.parseInt(st.nextToken()); // 잔향 거리

		Map map = new Map(N);
		Shark[] sharks = new Shark[M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int si = Integer.parseInt(st.nextToken());
				if (si > 0) {
					sharks[si] = new Shark(i, j);
					map.area[i][j] = si;
					map.smells[i][j][0] = si; // 냄새를 남긴 상어
					map.smells[i][j][1] = k; // 냄새가 사라지는 시각
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharks[i].d = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= M; i++) {
			for (int di = 1; di <= 4; di++) {
				st = new StringTokenizer(br.readLine());
				for (int dj = 0; dj < 4; dj++) {
					sharks[i].dirs[di][dj] = Integer.parseInt(st.nextToken());
				}
			}
		}

		int ans = simulate(map, sharks, N, M, k);

		System.out.println(ans);

	}

	private static boolean isIn(int x, int y, int N) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static int simulate(Map map, Shark[] sharks, int N, int M, int k) {
		int[][] deltas = { {}, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		int sec = 0;
		int nSharks = M;

		while (nSharks > 1) {

			if (++sec > 1000)
				return -1;

			// 각 상어 다음 위치 결정
			for (int si = 1; si <= M; si++) {
				Shark s = sharks[si];
				if (s == null)
					continue;

				// 다음 위치 결정
				int nd = -1;
				int x = s.x, y = s.y;
				for (int di : s.dirs[s.d]) {
					int[] delta = deltas[di];
					int nx = s.x + delta[0];
					int ny = s.y + delta[1];
					if (!isIn(nx, ny, N))
						continue;
					if (map.smells[nx][ny][1] < sec) {
						nd = di;
						x = nx;
						y = ny;
						break;
					} else if (x == s.x && y == s.y && map.smells[nx][ny][0] == si) {
						nd = di;
						x = nx;
						y = ny;
					}
				}

				// 기존 위치 제거
				map.area[s.x][s.y] = 0;

				// 상어 정보 갱신
				s.x = x;
				s.y = y;
				s.d = nd;
			}

			// 결정한 위치로 이동, 자리 경쟁
			for (int si = 1; si <= M; si++) {
				Shark s = sharks[si];
				if (s == null)
					continue;
				int x = s.x;
				int y = s.y;
				if (map.area[x][y] == 0) {
					map.smells[x][y][0] = si;
					map.smells[x][y][1] = sec + k;
					map.area[x][y] = si;
				} else {
					nSharks--;
					sharks[si] = null;
				}
			}

		}

		return sec;
	}

}
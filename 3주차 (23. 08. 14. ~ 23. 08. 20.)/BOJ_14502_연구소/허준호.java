import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
	static int n, m, maxSafe;
	static int[][] map;
	static List<Point> emptySpaces = new ArrayList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());// 세로 크기 N
		m = Integer.parseInt(st.nextToken());// 가로 크기 M
		maxSafe = 0;// 최대 안전 영역 크기
		map = new int[n][m];// 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치

		for (int i = 0; i < n; i++) {//
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					emptySpaces.add(new Point(i, j));
				}
			}
		}

		int[] selected = new int[3];
		chooseWall(0, 0, selected);// 빈 칸 중 3개의 벽을 선택

		System.out.println(maxSafe);
	}

	static void chooseWall(int start, int count, int[] selected) {
		if (count == 3) {
			maxSafe = Math.max(maxSafe, calcSafe(selected));
			return;
		}

		for (int i = start; i < emptySpaces.size(); i++) {
			selected[count] = i;
			chooseWall(i + 1, count + 1, selected);
		}
	}

	// 선택된 벽들을 설치하고, 바이러스 확산을 계산하여 안전 영역 크기 반환
	static int calcSafe(int[] selected) {
		int[][] tempMap = new int[n][m];
		for (int i = 0; i < n; i++) {
			tempMap[i] = Arrays.copyOf(map[i], m);
		}

		for (int idx : selected) {
			Point wall = emptySpaces.get(idx);
			tempMap[wall.x][wall.y] = 1;
		}

		Queue<Point> virusQueue = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (tempMap[i][j] == 2) {
					virusQueue.add(new Point(i, j));
				}
			}
		}

		while (!virusQueue.isEmpty()) {
			Point current = virusQueue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];

				if (nx >= 0 && nx < n && ny >= 0 && ny < m && tempMap[nx][ny] == 0) {
					tempMap[nx][ny] = 2;
					virusQueue.add(new Point(nx, ny));
				}
			}
		}

		int safeArea = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (tempMap[i][j] == 0) {
					safeArea++;
				}
			}
		}

		return safeArea;
	}
}
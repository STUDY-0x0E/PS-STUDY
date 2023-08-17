package baekjoon;

import java.util.*;
import java.util.Deque;
import java.io.*;

public class baekjoon_20055 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<int[]> belt = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			belt.add(new int[] { Integer.parseInt(st.nextToken()), 0 });
		}

		int level = 0;
		while (true) {
			level++;
			// 1. 벨트가 로봇과 함께 한 칸 회전
			belt.add(0, belt.get(2 * N - 1));
			belt.remove(2 * N);
			belt.set(N - 1, new int[] { belt.get(N - 1)[0], 0 });

			// 2. 로봇 한 칸 이동
			for (int i = N - 2; i >= 0; i--) {
				if (belt.get(i)[1] == 1 && belt.get(i + 1)[1] == 0 && belt.get(i + 1)[0] > 0) {
					belt.set(i + 1, new int[] { belt.get(i + 1)[0] - 1, 1 });
					belt.set(i, new int[] { belt.get(i)[0], 0 });
				}
			}
			belt.set(N - 1, new int[] { belt.get(N - 1)[0], 0 });

			// 3. 올리는 위치 내구도 남아있다면 로봇 올리기
			if (belt.get(0)[0] > 0) {
				belt.set(0, new int[] { belt.get(0)[0] - 1, 1 });
			}

			// 4. 내구도 0인 칸의 개수 K개 이상이면 과정 종료
			int count = 0;
			for (int[] b : belt) {
				if (b[0] == 0)
					count++;
			}
			if (count >= K)
				break;
		}
		System.out.println(level);
	}

}

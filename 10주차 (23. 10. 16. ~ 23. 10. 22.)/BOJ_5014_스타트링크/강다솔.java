package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_5014 {

	private static int F, S, G, U, D;
	private static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		int answer = getSolution();
		System.out.println(answer != -1 ? answer : "use the stairs");
	}

	private static int getSolution() {
		// 총 F층인 건물의 S층에서 G층으로 이동 (0층, F층 초과로 이동 불가)
		// 위로 U층, 아래로 D층 이동 가능
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { S, 0 });
		boolean[] visited = new boolean[F + 1];
		visited[S] = true;
		int[] dr = { U, -D };
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (cur[0] == G) return cur[1];
			for (int i = 0; i < 2; i++) {
				int next = cur[0] + dr[i];
				if (0 < next && next <= F && !visited[next]) {
					visited[next] = true;
					queue.offer(new int[] { next, cur[1] + 1 });
				}
			}
		}
		return -1;
	}

	private static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		answer = Integer.MAX_VALUE;
	}

}

package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_1260 {

	private static int N, M, V, graph[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		graph = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		boolean[] visited = new boolean[N + 1];
		visited[V] = true;
		dfs(0, V, visited);
		System.out.println();
		bfs();
	}

	private static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		boolean[] visited = new boolean[N + 1];
		visited[V] = true;
		queue.offer(V);
		while (!queue.isEmpty()) {
			int node = queue.poll();
			sb.append(node + " ");
			for (int i = 1; i <= N; i++) {
				if (graph[node][i] == 1 && !visited[i]) {
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int depth, int node, boolean[] visited) {
		if (depth == N) {
			return;
		}
		System.out.print(node + " ");
		for (int i = 1; i <= N; i++) {
			if (graph[node][i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs(depth + 1, i, visited);
			}
		}

	}

}

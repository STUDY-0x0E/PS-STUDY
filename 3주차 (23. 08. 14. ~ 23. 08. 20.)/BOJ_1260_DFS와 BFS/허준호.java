import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// input
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));// output
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());// 정점의 개수 N(1 ≤ N ≤ 1,000),
		int m = Integer.parseInt(st.nextToken());// 간선의 개수 M(1 ≤ M ≤ 10,000),
		int v = Integer.parseInt(st.nextToken());// 탐색을 시작할 정점의 번호 V

		List<Integer>[] list = new ArrayList[n + 1];// 인접 리스트으로 구현
		for (int i = 0; i <= n; i++)
			list[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());// 정점p
			int q = Integer.parseInt(st.nextToken());// 정점q
			list[p].add(q);
			list[q].add(p);
		}

		for (List<Integer> l : list) {
			Collections.sort(l, Collections.reverseOrder());
		}

		// dfs
		boolean[] visited = new boolean[n + 1];
		Deque<Integer> deque = new LinkedList<>();
		deque.add(v);

		while (!deque.isEmpty()) {
			int cur = deque.pollLast();
			if (!visited[cur]) {
				sb.append(cur + " ");
				visited[cur] = true;

				for (int i = 0; i < list[cur].size(); i++) {
					if (!visited[list[cur].get(i)]) {
						deque.addLast(list[cur].get(i));
					}
				}
			}
		}
		sb.append("\n");

		for (List<Integer> l : list) {
			Collections.sort(l);
		}

		// bfs
		visited = new boolean[n + 1];
		deque = new LinkedList<>();
		deque.add(v);

		while (!deque.isEmpty()) {
			int cur = deque.poll();
			if (!visited[cur]) {
				sb.append(cur + " ");
				visited[cur] = true;

				for (int i = 0; i < list[cur].size(); i++) {
					if (!visited[list[cur].get(i)]) {
						deque.add(list[cur].get(i));
					}
				}
			}
		}

		bw.append(sb.toString());
		bw.close();
	}
}
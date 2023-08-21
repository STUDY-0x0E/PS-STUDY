import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 1. summary
 *
 * 노드를 연결하는 간선이 주어졌을 때
 * BFS와 DFS로 탐색한 결과를 출력하라
 *
 * 말이 이동할 수 있는 최대 칸수 수
 *
 * 2. strategy
 *
 * 간선 리스트를 만들어서 DFS와 BFS 수행
 *
 * 3. note
 */

public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	
	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			Integer key = Integer.parseInt(st.nextToken()) - 1;
			Integer value = Integer.parseInt(st.nextToken()) - 1;
			
			// 양방향 연결
			List<Integer> list = map.getOrDefault(key, new LinkedList<Integer>());
			list.add(value);
			list.sort((o1, o2) -> o1 - o2);
			map.put(key, list);
			
			list = map.getOrDefault(value, new LinkedList<Integer>());
			list.add(key);
			list.sort((o1, o2) -> o1 - o2);
			map.put(value, list);
		}
		
		dfs(map, n, v - 1);
		bfs(map, n, v - 1);
			
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(Map<Integer, List<Integer>> map, int n, int v) throws Exception {
		boolean[] visited = new boolean[n];
		
		Stack<Integer> stack = new Stack<>();
		stack.add(v);
		
		while (!stack.isEmpty()) {
			Integer curr = stack.pop();
			if (visited[curr]) continue;
			
			visited[curr] = true;
			bw.write((curr + 1) + " ");
			
			List<Integer> next = map.getOrDefault(curr, new LinkedList<Integer>());
			for (int i = next.size() - 1; i >= 0; i--) {
				stack.add(next.get(i));
			}
		}
		
		bw.write("\n");
	}
	
	public static void bfs(Map<Integer, List<Integer>> map, int n, int v) throws Exception {
		boolean[] visited = new boolean[n];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(v);
		
		while (!queue.isEmpty()) {
			Integer curr = queue.poll();
			if (visited[curr]) continue;
			
			visited[curr] = true;
			bw.write((curr + 1) + " ");
			
			List<Integer> next = map.getOrDefault(curr, new LinkedList<Integer>());
			for (int i = 0; i < next.size(); i++) {
				queue.offer(next.get(i));
			}
		}
		
		bw.write("\n");
	}
}
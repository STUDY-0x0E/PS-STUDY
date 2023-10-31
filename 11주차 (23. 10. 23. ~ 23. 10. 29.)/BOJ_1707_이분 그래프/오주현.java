import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static List<Integer>[] link;
	static int visited[];
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			link = new List[V+1];
			for (int j = 1; j <= V; j++) link[j] = new ArrayList<>();
			
			for (int j = 0; j < E; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				link[from].add(to);
				link[to].add(from);
			}
			
			visited = new int[V+1];
			boolean flag = true;	
			for (int j = 1; j <= V; j++) {
				if (visited[j] == 0) flag = BFS(j);
				if (!flag) break;
			}
			
			if (flag) sb.append("YES").append("\n");
			else sb.append("NO").append("\n");
		}
		
		System.out.println(sb);
	}

	private static boolean BFS(int node) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(node);
		
		int separator = 1;	
		visited[node] = separator;
		
		separator *= -1;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			while (size-- > 0) {
				int cur = queue.poll();
				
				for (int next : link[cur]) {
					if (visited[next] == visited[cur])	return false;
					else if (visited[next] == 0) {
						visited[next] = separator;
						queue.add(next);
					}
				}
			}
			
			separator *= -1;
		}
		
		return true;
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int alone, students[], visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int test = 0; test < T; test++) {
			int n = Integer.parseInt(br.readLine());
			
			alone = n;
			students = new int[n+1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= n; i++) {
				students[i] = Integer.parseInt(st.nextToken());
			}
			
			visited = new int[n+1];
			solve(students, n);	
			sb.append(alone).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void solve(int[] students, int len) {
		for (int i = 1; i <= len; i++) {
			if (visited[i] != 0) continue;
			
			int result = dfs(i);
			if (result != 0) isTeam(result, result);
		}
	}
	
	private static int isTeam(int a, int b) {
		alone--;
		if (students[a] == b) return a;
		return isTeam(students[a], b);
	}

	public static int dfs(int a) {
		int cur = a;
		while (true) {
			if (visited[cur] != 0) return 0;
			visited[cur] = a;
			
			if (visited[students[cur]] == a) return students[cur];
			else cur = students[cur];
		}
	}
}
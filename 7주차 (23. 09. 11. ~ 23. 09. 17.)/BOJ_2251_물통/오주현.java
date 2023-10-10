import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static boolean visited[][];
	static int A, B, C;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		visited = new boolean[201][201];
		dfs(0,0,C);
		
		StringBuilder sb = new StringBuilder();
		for (int i = C; i >= 0; i--) {
			if (visited[0][i]) sb.append(C-i + " ");
		}
		
		System.out.println(sb);
	}

	public static void dfs(int a, int b, int c) {
		if (visited[a][b]) return;
		
		visited[a][b] = true;
		
		if (a + b > B) {
			dfs((a+b)-B, B, c);
		}else {
			dfs(0, a+b, c);
		}
		
		if (a + b > A) {
			dfs(A, a+b-A, c);
		}else {
			dfs(a+b, 0, c);
		}
		
		if (a + c > A) {
			dfs(A, b, a+c-A);
		}else {
			dfs(a+c, b, 0);
		}
		
		if (b + c > B) {
			dfs(a, B, b+c-B);
		}else {
			dfs(a, b+c, 0);
		}
		
		dfs(a, 0, b+c);
		dfs(0, b, a+c);
	}
}
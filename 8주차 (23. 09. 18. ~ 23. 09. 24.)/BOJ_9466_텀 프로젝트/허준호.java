import java.io.*;
import java.util.*;

public class Main {
	static int T, N, res;
	static int[] arr;
	static boolean[] visited, done;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			res = 0;
			arr = new int[N + 1];
			done = new boolean[N + 1];
			visited = new boolean[N + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++)
				arr[n] = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= N; i++) {
				if (done[i])
					continue;
				dfs(i);
			}

			System.out.println(N - res);
		}
	}

	public static void dfs(int idx) {
		if (done[idx])
			return;

		if (visited[idx]) {
			done[idx] = true;
			res++;
		}

		visited[idx] = true;
		dfs(arr[idx]);
		done[idx] = true;
		visited[idx] = false;
	}
}
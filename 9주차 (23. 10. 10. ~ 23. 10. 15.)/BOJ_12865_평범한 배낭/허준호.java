import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[] weight = new int[n + 1];
		int[] val = new int[n + 1];

		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			val[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[W + 1];

		for (int i = 1; i < n + 1; i++) {
			for (int w = W; w >= weight[i]; w--) {
				dp[w] = Math.max(dp[w], val[i] + dp[w - weight[i]]);

			}
		}

		System.out.println(dp[W]);
	}

}

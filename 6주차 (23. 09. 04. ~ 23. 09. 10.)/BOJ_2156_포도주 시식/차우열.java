package BOJ0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Wine {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[][] dp = new int[n+1][3];
		
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2])); 
			dp[i][1] = dp[i-1][0] + v;
			dp[i][2] = dp[i-1][1] + v;
		}
		
		System.out.println(Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2])));
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] triangle = new int[N+1][N+1];
		int[][] dp = new int[N+1][N+1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= i; j++) {
				triangle[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i][j]= Math.max(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j];
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			max= Math.max(max, dp[N][i]);
		}
		
		System.out.println(max);
	}

}
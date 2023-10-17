import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int list[][] = new int[N][2];
		int max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cost = Integer.parseInt(st.nextToken());
			int customer = Integer.parseInt(st.nextToken());
			
			list[i][0] = cost;
			list[i][1] = customer;
			max = Math.max(max, customer);
		}
		
		int dp[] = new int[C+max];
		Arrays.fill(dp, 10000000);
		dp[0] = 0;
		
		for (int i = 0; i < C+max; i++) {
			for (int j = 0; j < N; j++) {
				if (i-list[j][1] < 0) continue;
				dp[i] = Math.min(dp[i-list[j][1]]+list[j][0], dp[i]);
			}
		}
		
		int min = Integer.MAX_VALUE;
		for (int i = C; i < C+max; i++) {
			min = Math.min(min, dp[i]);
		}
		System.out.println(min);
	}

}
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());//잔의 개수
		int[] wine = new int[n + 1];
		int[] dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}

		if (n >= 1) {
			dp[1] = wine[1];
		}
		if (n >= 2) {
			dp[2] = wine[1] + wine[2];
		}

		// i번째 잔을 선택하지 않은 경우, i-1번째 잔을 선택한 경우, i-2번째와 i번째 잔을 연속으로 선택한 경우 중 최댓값을 선택
		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
		}

		System.out.println(dp[n]);// 최댓값 출력
	}
}
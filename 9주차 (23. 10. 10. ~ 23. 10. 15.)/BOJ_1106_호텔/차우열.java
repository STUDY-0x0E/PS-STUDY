package week9;

/*
1.summary:
C와 도시의 수 N을 입력받는다. 다음 N줄에 걸쳐 각 도시의 홍보 비용과 홍보시 고객 증가 수를 입력받는다.
같은 도시에서 여러번 홍보 할 수 있다.
최소 C명의 고객을 확보하기 위한 최소 홍보 비용을 구한다.

2.strategy:
dp를 이용한다.

3.note:
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1106 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[C+100];
		
		int INF = 1_000_000;
		
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			for (int j=b; j<C+b; j++) {
				dp[j]=Math.min(dp[j], dp[j-b]+a);
			}
		}
		
		int min = INF;
		for (int j=C; j<dp.length; j++) {
			min = Math.min(min, dp[j]);
		}
		System.out.println(min);
	}
}
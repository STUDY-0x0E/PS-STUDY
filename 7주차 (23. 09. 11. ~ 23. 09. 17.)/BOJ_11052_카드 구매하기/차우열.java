package week7;

/*
1.summary:
숫자 N을 입력받고 다음줄에 1~N개의 카드가 들어있는 카드팩의 가격N개를 입력받는다.
카드개수합이 N이 되도록 카드팩을 구매했을때의 최대 가격을 구하여라.

2.strategy:
i번째 카드를 입력받을 때 계산한 i-1개 이하의 카드구매시 최댓값을 이용해서 i개의 최댓값을 구한다.

3.note:
같은 카드팩을 중복해서 구입할 수 있다.
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11052 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] dp = new int[N];
		st = new StringTokenizer(br.readLine());
		dp[0] = Integer.parseInt(st.nextToken());
		for(int i=1; i<N; i++) {
			dp[i] = Integer.parseInt(st.nextToken());
			for (int j=0; j<=i/2; j++) {
				dp[i] = Math.max(dp[i-j-1]+dp[j], dp[i]);
			}
		}
		System.out.println(dp[N-1]);
		
	}
}
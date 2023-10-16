package week9;

/*
1.summary:
물품의 수 N과 배낭에 넣을 수 있는 최대 무게 K를 입력받는다. 다음 N줄에 걸쳐 각 물품의 무게와 가치를 입력받는다.
배낭에 넣은 물건의 최대 가치 합을 출력한다.

2.strategy:
dp를 사용한다.

3.note:
각 물품은 하나씩 배낭에 넣을 수 있다.
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][2];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr,(x,y)->y[0]-x[0]);
		
		int[] dp = new int[K+1];
		
		for (int i=0; i<N; i++) {
			if (arr[i][0]>K) continue;
			for (int j=K; j>=arr[i][0]; j--) {
				dp[j] = Math.max(dp[j], dp[j-arr[i][0]]+arr[i][1]);
			}
		}
		
		System.out.println(dp[K]);
		
	}
}
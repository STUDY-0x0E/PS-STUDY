import java.util.*;
import java.io.*;

/*
1. summary : 파일 합치기
	두개의 파일은 하나로 합친다. 이때 발생하는 비용은 각 파일의 크기의 합이다.
	(단, 파일은 앞 뒤로 붙어있어야 한다.)
	파일 여러장을 모두 하나로 합칠때 발생하는 최소 비용 구하기
2. strategy : DP
	모든 경우의 수를 직접 계산하면 시간초과 발생 DP 사용
3. note
	3 ≤ K(파일 수) ≤ 500, 파일의 크기 < 10,000 (long 사용)
*/
public class BOJ11066 {

	static int file[];
	static int DP[][];
	static int sum[];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc =1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());
			
			file = new int[K+1];
			sum = new int[K+1];
			DP = new int[K+1][K+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= K; i++) {
				file[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + file[i];
			}
			
			for(int i = 1; i <= K; i++) {
				for(int j = 1; j <= K-i; j++) {
					DP[j][i+j] = Integer.MAX_VALUE;
					for(int k = j; k < i+j; k++) {
						DP[j][i+j] = Math.min(DP[j][i+j], DP[j][k] + DP[k+1][i+j] + sum[i+j] - sum[j-1]);
					}
				}
				
			}
			
			sb.append(DP[1][K]).append("\n");
		}
		
		System.out.println(sb);
	}
}
package week9;

/*
1.summary:
물건의 수 N을 입력받고, 물건간의 무게 비교 횟수 M을 입력받는다. 다음M줄에 걸쳐 비교 결과를 입력받는다.
각 물건에 대해 비교 결과를 알 수 없는 물건의 수를 출력한다.

2.strategy:
플로이드-워셜을 이용한다.

3.note:
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10159 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean[][] adjM = new boolean[N+1][N+1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			adjM[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}
		
		for (int k=1; k<=N; k++) {
			for (int i=1; i<=N; i++) {
				if (k==i) continue;
				for (int j=1; j<=N; j++) {
					if (k==j || i==j) continue;
					if (adjM[i][k]&&adjM[k][j]) adjM[i][j] = true;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			int cnt = N-1;
			for (int j=1; j<=N; j++) {
				if (adjM[i][j] || adjM[j][i]) cnt--;
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
		
	}
}
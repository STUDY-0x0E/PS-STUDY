package week8;

/*
1.summary:
삼각형의 크기N을 입력하고 N줄에 걸쳐서 N(N+1)/2개의 값을 입력받는다.
각 숫자는 바로 아래있는 숫자와 아래에서 오른쪽에 있는 숫자와 인접해있다.
위에서 아래로 인접한 숫자를 이동하는동안의 합의 최대값을 출력한다.

2.strategy:
각각의 위치까지 경로에서의 최댓값을 계산하면 빠르게 문제를 해결할 수 있다.

3.note:
아래에서 위로 최댓값을 계산하면 하나의 위치에서 수렴하므로 좀 더 편하다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[][] tri = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<=i; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=N-2; i>=0; i--) {
			for (int j=0; j<=i; j++) {
				tri[i][j]+= Math.max(tri[i+1][j], tri[i+1][j+1]);
			}
		}
		System.out.println(tri[0][0]);
	}
}
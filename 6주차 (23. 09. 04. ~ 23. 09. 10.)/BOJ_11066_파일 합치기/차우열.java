package BOJ0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MergeFile {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int[][][] files = new int[K][K][2];

			for (int i = 0; i < K; i++) {
				files[0][i][0] = Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i < K; i++) {
				for (int j = 0; j < K-i; j++) {
					int min = Integer.MAX_VALUE;
					for (int k=0; k < i; k++) {
						min = Math.min(files[i-1-k][j][1]+files[k][j+i-k][1], min);
					}
					files[i][j][0] = files[i-1][j][0] + files[0][j+i][0];
					files[i][j][1] = min + files[i][j][0];
				}
			}
			sb.append(files[K-1][0][1]).append('\n');
		}
		System.out.println(sb);
	}
}
/*
0 i 1개 i번째
k i k+1개 i번째
i j i+1개 j번째 -> i개 j번째 + 1개 j+i번째(i-1, j + 0, j+i), i-1개 j번째+ 1+1개 j+i-1번째
*/
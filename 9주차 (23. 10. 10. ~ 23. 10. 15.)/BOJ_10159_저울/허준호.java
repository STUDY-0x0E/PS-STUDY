import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());// 물건의 개수
        int M = Integer.parseInt(br.readLine());// 미리 측정된 물건 쌍의 개수
		boolean[][] isHeavier = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			isHeavier[a][b] = true;
		}

		for (int k = 1; k <= N; k++) {//F.W
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (isHeavier[i][k] && isHeavier[k][j]) {
						isHeavier[i][j] = true;
					}
				}
			}
		}

		// 각 물건에 대해 비교 결과를 알 수 없는 물건의 개수 출력
		for (int i = 1; i <= N; i++) {
			int unknownCount = 0;
			for (int j = 1; j <= N; j++) {
				if (i!=j&&!isHeavier[i][j] && !isHeavier[j][i]) {
					unknownCount++;
				}
			}
			System.out.println(unknownCount);
		}
	}
}

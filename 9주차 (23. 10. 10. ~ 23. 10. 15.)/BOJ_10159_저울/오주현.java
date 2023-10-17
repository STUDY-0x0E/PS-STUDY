import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int link[][] = new int[N+1][N+1]; 
		StringTokenizer st;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int heavy = Integer.parseInt(st.nextToken());
			int light = Integer.parseInt(st.nextToken());
			
			link[heavy][light] = 1;
			link[light][heavy] = -1;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					if (link[j][i] == 1 && link[i][k] == 1) link[j][k] = 1;
					else if (link[j][i] == -1 && link[i][k] == -1) link[j][k] = -1;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int answer = N-1;
			for (int j = 1; j <= N; j++) {
				if (link[i][j] != 0) answer--;
			}
			sb.append(answer+ "\n");
		}
		
		System.out.println(sb);
	}
}

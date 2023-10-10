import java.util.*;
import java.io.*;

/*
1. summary : 팀에 속하지 않은 사람 수 구하기
2. strategy : BFS	
3. note	:
	2 ≤ n ≤ 100,000 -> O(N^2) X
*/

public class BOJ9466 {
	
	static final int NOT_VISITED = 0;
	static final int CYCLE_IN = -1;
	
	static int N;
	static int board[], visite[];
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			
			board = new int[N+1];
			visite = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < N+1; i++) {
				board[i] = Integer.parseInt(st.nextToken()); //
			}
			
			for(int i = 1; i <= N; i++) { // 각 학생(1~N) 체크 
				if(visite[i] == NOT_VISITED) // 아직 방문 처리를 하지 않은 학생인 경우
					check(i); 
			}
			
			int cnt = 0;
			for(int i = 1; i <= N; i++) {
				if(visite[i] != CYCLE_IN) cnt++;
			} 
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	public static void check(int x) {
		
		int cur = x;
		while(true) {
			visite[cur] = x;
			cur = board[cur];
			
			if(visite[cur] == x) { // 이번 방문에서 지나간 학생에 도달한 경우 (사이클 완성 가능)
				while(visite[cur] != CYCLE_IN) { // 사이클(-1) 표시
					visite[cur] = CYCLE_IN;
					cur = board[cur];
				}
				return;
			}
			// 이전 방문에서 지나간 학생에 도달한 경우 (이미 결과를 앎)
			else if(visite[cur] != NOT_VISITED) return;
		}
	}
}
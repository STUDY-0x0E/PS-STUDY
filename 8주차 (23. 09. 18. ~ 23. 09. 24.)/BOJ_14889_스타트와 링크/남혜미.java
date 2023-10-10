import java.util.*;
import java.io.*;

/*
1. summary : 두 팀의 능력치 차이 최소화
2. strategy : 조합
	각 팀의 능력치는 팀 인원끼리의 능력치들의 합이다.
3. note : 
	4 ≤ N(인원) ≤ 20 (짝수) 
	1 ≤ 능력치 ≤ 100
*/

public class BOJ14889  {

	static int N;
	static int board[][];
	static boolean check[];
	
	static long minSub = Long.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		check = new boolean[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Comb(0, 0);
		
		System.out.println(minSub);
	}
	
	// 팀 조합
	public static void Comb(int cnt, int start) {
		
		if(cnt == N/2) {
			minSub = Math.min(minSub, subtractTeam());
			return ;
		}
		
		for(int i = start; i < N; i++) {			
			check[i] = true; // 스타트 팀 선택
			Comb(cnt + 1, i + 1);
			check[i] = false;
		}
	}
	
	public static int subtractTeam() {

		int sub = 0;
		
		int sumTeamStart = 0; // 스타트 팀의 능력치 합
		for(int i = 0; i < N; i++) { 
			if(!check[i]) continue;
			for(int j = 0; j < N; j++) {
				if(!check[j]) continue;
				sumTeamStart += board[i][j];
			}
		}
		
		int sumTeamLink = 0; // 링크 팀의 능력치 합
		for(int i = 0; i < N; i++) {
			if(check[i]) continue;
			for(int j = 0; j < N; j++) {
				if(check[j]) continue;
				sumTeamLink += board[i][j];
			}
		}
		
		sub = Math.abs(sumTeamLink - sumTeamStart);
		
		return sub;
	}
}
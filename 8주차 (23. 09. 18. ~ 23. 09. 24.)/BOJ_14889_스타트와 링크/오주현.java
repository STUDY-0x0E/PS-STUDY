package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, min = Integer.MAX_VALUE, ability[][];
	static boolean team[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		ability = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				ability[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		team = new boolean[N];
		
		teamDivide(0,0);
		System.out.println(min);
	}
	
	public static void teamDivide(int cnt, int select) {
		if (cnt == N) {
			if (select < N/2) return;
			
			int[] start = new int[N/2];
			int[] link = new int[N/2];
			
			int sidx = 0;
			int lidx = 0;
			for (int i = 0; i < N; i++) {
				if (team[i]) start[sidx++]= i;
				else link[lidx++]= i;
			}
			int diff = synergy(start,link);
			min = Math.min(diff, min);
			
			return;
		}
		if (select >= N/2) return;
		
		team[cnt] = true;
		teamDivide(cnt+1,select+1);
		team[cnt] = false;
		teamDivide(cnt+1,select);
	}
	
	public static int synergy(int[] start, int[] link) {
		int score1 = 0;
		int score2 = 0;

		for (int i = 0; i < N/2; i++) {
			for (int j = i; j < N/2; j++) {
				int player1 = start[i];
				int player2 = start[j];				
				score1+= ability[player1][player2] + ability[player2][player1];
				
				player1 = link[i];
				player2 = link[j];
				score2+= ability[player1][player2] + ability[player2][player1];
			}
		}
		
		return Math.abs(score1 - score2);
	}

}

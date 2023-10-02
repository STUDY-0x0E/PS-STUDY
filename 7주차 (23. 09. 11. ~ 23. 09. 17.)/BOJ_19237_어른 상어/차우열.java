package week7;

/*
1.summary:
격자의 크기 N과 상어의 수 M, 냄새의 잔류시간k를 입력받는다.
다음 N줄에는 N*N의 격자에서 상어의 시작위치를 입력받는다.
다음 줄에는 M마리의 상어의 시작 방향을 입력받는다.
다음 4*M줄에는 각 상어의 방향에 따른 방향 우선순위를 입력받는다.
상어의 이동위치는 냄새가 없는 격자에 현재 방향에 따른 우선순위를 고려해서 결정된다.  
상어가 같은 격자에 위치하게 된다면, 번호가 높은 상어는 사라진다.
상어가 하나가 남을때까지 걸린시간을 출력하거나, 1000시간동안 상어가 둘 이상 존재한다면 -1을 출력한다.

2.strategy:
낮은번호의 상어부터 이동시켜서 높은 번호의 상어가 그 위치로 이동하면 제거한다.
모든상어의 이동위치를 결정한 후 상어의 냄새를 만들어야한다.

3.note:
냄새의 잔류시간이 0이 되어 사라지는경우를 잘 처리하자.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19237 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][][] board = new int[N][N][2];
		int[][] shark = new int[M+1][3];
		int[][][] dir = new int[M+1][5][5];
		boolean[] nisShark = new boolean[M+1];
		
		dir[0] = new int[][] {{},{-1,0},{1,0},{0,-1},{0,1}};
		
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				int tar = Integer.parseInt(st.nextToken());
				if (tar != 0) {
					board[i][j][0] = tar;
					board[i][j][1] = k;
					shark[tar][0] = i;
					shark[tar][1] = j;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=M; i++) {
			shark[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=1; i<=M; i++) {
			for (int j=1; j<=4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int l=1; l<=4; l++) {
					dir[i][j][l] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		int time = 0;
		int cnt = M;
		while (time<=1000) {
			if(cnt==1) break;
			time++;
			
			for (int i=1; i<=M; i++) {
				if (nisShark[i]) continue;
				int[] d = null;
				int[] ds = dir[i][shark[i][2]];
				for (int j=1; j<=4; j++) {
					int dx = shark[i][0] + dir[0][ds[j]][0];
					int dy = shark[i][1] + dir[0][ds[j]][1];
					if (dx>=0 && dx<N && dy>=0 && dy<N && board[dx][dy][0]==0) {
						d = new int[] {dx, dy, ds[j]};
						break;
					}
				}
				if (d==null) {
					for (int j=1; j<=4; j++) {
						int dx = shark[i][0] + dir[0][ds[j]][0];
						int dy = shark[i][1] + dir[0][ds[j]][1];
						if (dx>=0 && dx<N && dy>=0 && dy<N && board[dx][dy][0]==i) {
							d = new int[] {dx, dy, ds[j]};
							break;
						}
					}
				}
				shark[i] = d;
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (board[i][j][1]>0) {
						if (--board[i][j][1]==0) board[i][j][0] = 0;
					}
				}
			}
			
			for (int i=1; i<=M; i++) {
				if (nisShark[i]) continue;
				int dx = shark[i][0];
				int dy = shark[i][1];
				if (board[dx][dy][0]==0) {
					board[dx][dy][0] = i;
					board[dx][dy][1] = k;
				} else if (board[dx][dy][0]==i){
					board[dx][dy][1] = k;
				} else {
					cnt--;
					nisShark[i] = true;
				}
			}
		}
		if (time<=1000) {
			System.out.println(time);
		} else {
			System.out.println(-1);
		}
	}
}

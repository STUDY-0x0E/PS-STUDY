package week8;
/*
1.summary:
숫자 N과 M을 입력받고, N*M개의 정보를 N줄에 걸쳐 입력받는다.
치즈에서 외부 공기와 닿아있는 부분은 한 시간 뒤 사라진다.
치즈가 전부 사라지기까지 걸리는 시간을 출력하고, 전부 사라지기 한 시간전에 치즈의 갯수를 출력한다.

2.strategy:
치즈의 외부로부터 BFS를 시작한다. 각 시간에서 사라지는 치즈의 수를 세고, 모든 치즈가 사라지면 답을 출력한다.
	
3.note:
경계값에 유의한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] board = new boolean[N][M];
		int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				if (st.nextToken().equals("1"))
					board[i][j] = true;
			}
		}
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> airq = new ArrayDeque<int[]>();
		Queue<int[]> cheq = new ArrayDeque<int[]>();
		for (int i=1; i<N-1; i++) {
			visited[i][0] = true;
			visited[i][M-1] = true;
			visited[i][1] = true;
			visited[i][M-2] = true;
			if (!board[i][1]) {
				airq.offer(new int[] {i,1});
			} else {
				cheq.offer(new int[] {i,1});
			}
			if (!board[i][M-2]) {
				airq.offer(new int[] {i,M-2});
			} else {
				cheq.offer(new int[] {i,M-2});
			}
		}
		Arrays.fill(visited[0], true);
		Arrays.fill(visited[N-1], true);
		for (int i=2; i<M-2; i++) {
			visited[1][i] = true;
			visited[N-2][i] = true;
			if (!board[1][i]) {
				airq.offer(new int[] {1,i});
			} else {
				cheq.offer(new int[] {1,i});
			}
			if (!board[N-2][i]) {
				airq.offer(new int[] {N-2,i});
			} else {
				cheq.offer(new int[] {N-2,i});
			}
		}
		int cnt = airq.size();
		int time = 0;
		int csize = 0;
		while (cnt<(N-2)*(M-2)) {
			while(!airq.isEmpty()) {
				int[] cur = airq.poll();
				
				for (int i=0; i<dir.length;i++) {
					int x = cur[0] + dir[i][0];
					int y = cur[1] + dir[i][1];
					if (visited[x][y]) continue;
					visited[x][y] = true;
					if (board[x][y]) {
						cheq.offer(new int[] {x,y});
					}else {
						airq.offer(new int[] {x,y});
					}
				}
			}
			if (cheq.size()==0) break;
			time++;
			csize = cheq.size();
			while (!cheq.isEmpty()) {
				int[] cur = cheq.poll();
				airq.offer(cur);
				board[cur[0]][cur[1]] = false;
			}
			
		}
		System.out.println(time);
		System.out.println(csize);
	}
}
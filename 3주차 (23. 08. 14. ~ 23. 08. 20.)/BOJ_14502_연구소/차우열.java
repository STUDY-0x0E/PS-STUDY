package algo0821;

/*
1.summary:
행N, 열M을 입력받고, N*M배열에 연구소 정보를 입력받는다.
바이러스는 벽을 통과하지 못하고, 대각선으로 확산될 수 없다.
벽을 3개 세웠을때 바이러스가 확산되지 못하는 안전영역의 최대 크기를 출력한다.

2.strategy:
조합으로 벽이 세워질 수 있는 모든 위치를 찾는다. 
벽의 각 위치에서 BFS로 바이러스의 확산시 안전영역의 크기를 구한다.

3.note:
방문 배열을 사용한다면 계속 초기화해야한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		int ecnt = 0;
		int vcnt = 0;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j]==0) ecnt++;
				else if (board[i][j]==2) vcnt++;
			}
		}
		
		int[][] ept = new int[ecnt][2];
		int[][] vpt = new int[vcnt][2];
		
		for (int i=0, e=0, v=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (board[i][j]==0) {
					ept[e][0] = i;
					ept[e++][1] = j;
				} else if (board[i][j]==2) {
					vpt[v][0] = i;
					vpt[v++][1] = j;
				}
			}
		}
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		int[][] direc = {{1,0},{0,1},{-1,0},{0,-1}};
		boolean[][] visited = new boolean[N][M];

		int max = 0;
		for (int i=0; i<ecnt-2; i++) {
			for (int j=i+1; j<ecnt-1; j++) {
				for (int k=j+1; k<ecnt; k++) {
					
					for (int l=0; l<N; l++) {
						for (int m=0; m<M; m++) {
							if (board[l][m]!=0) visited[l][m] = true;
							else visited[l][m] = false;
						}
					}
					
					visited[ept[i][0]][ept[i][1]] = true;
					visited[ept[j][0]][ept[j][1]] = true;
					visited[ept[k][0]][ept[k][1]] = true;
					
					for (int[] v:vpt) {
						q.add(v);
					}
					
					int safe = ecnt-3;
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						
						for (int l=0; l<direc.length; l++) {
							int x = cur[0] + direc[l][0];
							int y = cur[1] + direc[l][1];
							if (x>=0 && x<N && y>=0 && y<M && !visited[x][y]) {
								visited[x][y] = true;
								q.add(new int[]{x, y});
								safe--;
							}
						}
						if (safe<=max) {
							q.clear();
							break;
						}
					}
					if (safe>max) max = safe;
				}
			}
		}
		System.out.println(max);
		
	}
}

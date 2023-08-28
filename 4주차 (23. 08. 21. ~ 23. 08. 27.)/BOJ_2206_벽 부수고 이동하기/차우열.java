package algo0828;

/*
1.summary:
행N과 열M을 입력받고, 다음 M줄에 걸쳐 미로 정보를 입력받는다.
미로의 시작은 0,0이고, 미로의 끝은 N-1,M-1이다.
미로를 이동하는동안 벽을 한 번만 부수고 이동 할 수 있다.
미로를 탈출하기 위해 필요한 최소 이동횟수를 출력한다.

2.strategy:
BFS를 사용한다. 벽을 부쉈을 경우와 부수지 않았을 경우에 대해서 다른 visit배열을 적용한다.

3.note:
벽이 있던 자리에 대한 배열을 visit배열로 대체해도 해결 될 것 같지만 더 많은 경으의 수를 가지게 되므로 비효율적이다.
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		boolean[][][] visited = new boolean[2][N][M];
		boolean[][] board = new boolean[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String S = st.nextToken();
			for (int j=0; j<M; j++) {
				if (S.charAt(j)=='1') {
					board[i][j] = visited[0][i][j] = true;
				}
			}
		}
		int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};

		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {0,0,0,1});
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[1]==N-1 && cur[2]==M-1) {
				System.out.println(cur[3]);
				return;
			}
			
			for (int i=0; i<dir.length; i++) {
				int x = cur[1] + dir[i][0];
				int y = cur[2] + dir[i][1];
				if (x>=0 && x<N && y>=0 && y<M) {
					if (cur[0]==0) {
						if (!visited[0][x][y]) {
							visited[0][x][y] = true;
							visited[1][x][y] = true;
							q.offer(new int[] {0, x, y, cur[3]+1});
						} else if (!visited[1][x][y] && board[x][y]) {
							visited[1][x][y] = true;
							q.offer(new int[] {1, x, y, cur[3]+1});
						}
					} else {
						if (!visited[1][x][y] && !board[x][y]) {
							visited[1][x][y] = true;
							q.offer(new int[] {1, x, y, cur[3]+1});
						}
					}
				}
			}
		}
		System.out.println(-1);
	}

}

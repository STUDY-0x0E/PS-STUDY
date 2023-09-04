package algo0904;

/*
1.summary:
테스트케이스 T를 입력받고 각 테스트케이스에서 문제를 해결한다.
이차원 배열의 크기 N을 입력받고, 다음 N줄에 걸쳐 N*N지점의 비용을 입력받는다.
0,0 지점에서 N-1,N-1 지점까지 지나가는 비용이 최소가 되는 경우의 비용합을 출력한다.

2.strategy:
우선순위큐를 이용하여 비용이 최소인 경우에 대한 bfs로 답을 구한다.

3.note:
출력에 주의한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4485 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while (true) {
			cnt++;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if (N==0) break;
			final int inf = (int)1e9;
			
			int[][] board = new int[N+2][N+2];
			
			Arrays.fill(board[0], inf);
			Arrays.fill(board[N+1], inf);
			
			for (int i=1; i<=N; i++) {
				board[i][0] = board[i][N+1] = inf;
			}
			
			for (int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=1; j<=N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] cost = new int[N+2][N+2];
			for (int i=0; i<N+2; i++) {
				Arrays.fill(cost[i], Integer.MAX_VALUE);
			}
			cost[1][1] = board[1][1];
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>((x,y)->(x[2]-y[2]));
			pq.add(new int[] {1,1,0});
			
			int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
			
			while(!pq.isEmpty()) {
				int[] cur = pq.poll();
				
				if (cur[0]==N && cur[1]==N) break;
				
				for (int i=0; i<dir.length; i++) {
					int x = cur[0]+dir[i][0];
					int y = cur[1]+dir[i][1];
					if (cost[x][y]>board[x][y]+cost[cur[0]][cur[1]]) {
						cost[x][y] = board[x][y]+cost[cur[0]][cur[1]];
						pq.offer(new int[]{x, y, cost[x][y]});
					}
				}
			}
			
			sb.append("Problem ").append(cnt).append(": ").append(cost[N][N]).append("\n");
		}
		System.out.print(sb);
	}
}
package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN4485_Zelda {
	
	static int N, cave[][], cost[][];
	static boolean visited[][];
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc= 0; 		StringTokenizer st;
		
		while ((N = Integer.parseInt(br.readLine()))!= 0){
			
			cave= new int[N][N];	
	
			visited= new boolean[N][N];	
			for (int i = 0; i < N; i++) {
				st= new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					cave[i][j]= Integer.parseInt(st.nextToken());
				}
			}
			
			cost= new int[N][N];
			for (int i = 0; i < N; i++) Arrays.fill(cost[i], Integer.MAX_VALUE);
			dijkstra(0,0);
			
			sb.append("Problem ").append(++tc).append(": ").append(cost[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra(int i, int j) {
		cost[i][j]= cave[i][j];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[2]-o2[2]);
		pq.offer(new int[] {i,j,cost[i][j]});
		
		while (!pq.isEmpty()) {
			int[] curnode = pq.poll();
			int r = curnode[0];
			int c = curnode[1];
			int ccost = curnode[2];

			if (cost[r][c] < ccost) continue;
			
			for (int k = 0; k < 4; k++) {
				int nx = r + dx[k];
				int ny = c + dy[k];
				
				int dist;
				if (inRange(nx,ny) && (cost[nx][ny] > (dist= ccost+cave[nx][ny]))) {
					cost[nx][ny]= dist;
					pq.offer(new int[] {nx,ny,dist});	
				}
			}		
		}
		
	}
	
	private static boolean inRange(int nx, int ny) {
		if (0<=nx && nx<N && 0<=ny && ny<N) return true;
		return false;
	}

}
package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Drill {
	int i, j, cnt, drill;
	
	public Drill(int i, int j, int cnt, int drill) {
		this.i= i;
		this.j= j;
		this.cnt= cnt;
		this.drill= drill;
	}
}

public class JUN2206_BreakWall {

	static int N, M;
	static boolean map[][];
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,-1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map= new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String s[] = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				if (s[j].equals("1")) map[i][j]= true;
			}
		}
		
		int answer = search(0,0,1,1);
		
		System.out.println(answer);
	}

	private static int search(int i, int j, int cnt, int drill) {
		Queue<Drill> queue = new ArrayDeque<>();
		boolean visited[][][] = new boolean[N][M][2];
		
		queue.offer(new Drill(i,j,cnt,drill));
		visited[i][j][1]= true;
		
		int answer = -1;
		while (!queue.isEmpty()) {
			Drill next = queue.poll();	
			if (next.i==N-1 && next.j==M-1) {
				answer= next.cnt;
				break;
			}		
			
			for (int k = 0; k < 4; k++) {
				int nx = next.i + dx[k];
				int ny = next.j + dy[k];
				
				if (!inRange(nx, ny)) continue;
				
				if (next.drill==1 && map[nx][ny]) {
					if (visited[nx][ny][0]) continue;
					
					queue.offer(new Drill(nx,ny,next.cnt+1,0));
					visited[nx][ny][0]= true;
					
				}else if (!map[nx][ny]) {
					if (visited[nx][ny][next.drill]) continue;
					
					queue.offer(new Drill(nx,ny,next.cnt+1,next.drill));
					visited[nx][ny][next.drill]= true;
				}
			}
		}
		return answer;
	}
	
	private static boolean inRange(int nx, int ny) {
		if (0<=nx && nx<N && 0<=ny && ny<M) return true;
		return false;
	}

}

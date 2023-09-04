package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN16236_BabyShark {
	
	static int dx[] = {-1,0,0,1};
	static int dy[] = {0,-1,1,0};
	static int N, board[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[] pos = new int[2];
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				board[i][j]= Integer.parseInt(st.nextToken());
				if (board[i][j] == 9) {
					pos[0]= i;
					pos[1]= j;
					board[i][j]= 0;
				}
			}
		}
		
		int survive = eat(pos[0],pos[1],2,0,0);
		System.out.println(survive);
	}

	private static int eat(int i, int j, int size, int cnt, int time) { 
		if (cnt == size) {
			size++;
			cnt= 0;
		}
		
		int[] prey = find(i,j,size);
		if (prey[0]==-1 && prey[1]==-1) 
			return time;
		
		board[prey[0]][prey[1]]= 0;
		return eat(prey[0],prey[1],size,cnt+1,time+prey[2]);
	}
	
	private static int[] find(int x, int y, int size) {
		boolean visited[][] = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		ArrayList<int[]> candidate = new ArrayList<int[]>();

		queue.offer(new int[] {x,y,0});
		visited[x][y]= true;
		
		while(!queue.isEmpty()) {
			int[] next = queue.poll();
			int cx = next[0];
			int cy = next[1];
			
			if (0<board[cx][cy] && board[cx][cy]<size) 
				candidate.add(new int[] {cx,cy,next[2]});
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if (inRange(nx, ny) && board[nx][ny]<=size && !visited[nx][ny]) {
					queue.offer(new int[] {nx,ny,next[2]+1});
					visited[nx][ny]= true;
				}
					
			}	
		}
		
		if (candidate.isEmpty()) return new int[] {-1,-1,0};
		else {
			Collections.sort(candidate, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[2] == o2[2]) {
						if (o1[0] == o2[0]) return o1[1] - o2[1];
						return o1[0] - o2[0];
					}
	 				return o1[2]-o2[2];
				}
			});
			
			int prey[] = candidate.get(0);
			return new int[] {prey[0],prey[1],prey[2]};
		}
	}
	
	private static boolean inRange(int nx, int ny) {
		if (0<=nx && nx<N && 0<=ny && ny<N) return true;
		return false;
	}

}

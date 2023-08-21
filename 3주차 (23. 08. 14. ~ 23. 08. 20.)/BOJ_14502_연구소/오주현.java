package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 오주현 {

	static int N, M;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		ArrayList<int[]> virus = new ArrayList<>();
		ArrayList<int[]> candidate = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) virus.add(new int[] {i,j});
				if (map[i][j] == 0) candidate.add(new int[] {i,j});
			}
		}
		
		int max = 0;
		int[][] blocked = new int[N][M];

		for (int i = 0; i < candidate.size()-2; i++) {
			for (int j = i+1; j < candidate.size()-1; j++) {
				for (int k = j+1; k < candidate.size(); k++) {
					
					for (int x = 0; x < N; x++) {
						for (int y = 0; y < M; y++) {
							blocked[x][y]= map[x][y];
						}
					}
					
					blocked[candidate.get(i)[0]][candidate.get(i)[1]]= 1;
					blocked[candidate.get(j)[0]][candidate.get(j)[1]]= 1;
					blocked[candidate.get(k)[0]][candidate.get(k)[1]]= 1;

					for (int[] v : virus) {
						BFS(v[0],v[1], blocked);
					}	
					int area = check(blocked);	
					if (area > max) max= area;				
				}
			}
		}

		System.out.println(max);
	}
	
	public static void BFS(int x, int y, int[][] blocked) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x,y});
		
		while (!queue.isEmpty()) {
			int[] next = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = next[0] + dx[i];
				int ny = next[1] + dy[i];
				
				if (0<=nx && nx<N && 0<=ny && ny<M && blocked[nx][ny]==0) {
					blocked[nx][ny]= 2;
					queue.add(new int[] {nx,ny});
				}
			}
		}
	}
	
	public static int check(int[][] blocked) {
		int area = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (blocked[i][j] == 0) area++;
			}
		}
		return area;
	}
}

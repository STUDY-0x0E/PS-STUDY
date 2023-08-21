package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair3 {
	int x, y, z;
	
	public Pair3(int x, int y, int z) {
		this.x= x;
		this.y= y;
		this.z= z;
	}
}

public class JUN7569_Tomato2 {

	static int M, N, H, box[][][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		box = new int[H][N][M];
		
		ArrayList<Pair3> tomato = new ArrayList<>();
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				st= new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					box[k][i][j]= Integer.parseInt(st.nextToken());
					if (box[k][i][j] == 1) tomato.add(new Pair3(k,i,j));
				}
			}			
		}

		Queue<Pair3> queue = new LinkedList<>();
		Queue<Pair3> buffer = new LinkedList<>();
		for (Pair3 pair : tomato) {
			buffer.add(pair);
		}
		
		int[] dx = {0,0,-1,1,0,0};
		int[] dy = {-1,1,0,0,0,0};
		int[] dz = {0,0,0,0,-1,1};
		
		int cnt = 0;
		while(!buffer.isEmpty()) {
			for (Pair3 pair : buffer) {
				queue.add(new Pair3(pair.x, pair.y, pair.z));
			}
			buffer.clear();
			
			while(!queue.isEmpty()) {
				Pair3 node = queue.poll();
				
				for (int i = 0; i < 6; i++) {
					int nx = node.x + dx[i];
					int ny = node.y + dy[i];
					int nz = node.z + dz[i];
					
					if (0<=nx && nx<H && 0<=ny && ny<N && 0<=nz && nz<M && box[nx][ny][nz]==0) {
						box[nx][ny][nz]= 1;
						buffer.add(new Pair3(nx,ny,nz));
					}				
				}
			}
			cnt++;
		}
		if (check()) System.out.println(cnt-1);
		else System.out.println(-1);
	}

	private static boolean check() {
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (box[k][i][j] == 0) return false;
				}
			}			
		}
		return true;
	}	
}

package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int x, y;
	
	public Pair(int x, int y) {
		this.x= x;
		this.y= y;
	}
}

public class JUN7576_Tomato {

	static int M, N, box[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N][M];
		
		ArrayList<Pair> tomato = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				box[i][j]= Integer.parseInt(st.nextToken());
				if (box[i][j] == 1) tomato.add(new Pair(i,j));
			}
		}
		
		Queue<Pair> queue = new LinkedList<>();
		Queue<Pair> buffer = new LinkedList<>();
		for (Pair pair : tomato) {
			buffer.add(pair);
		}
		
		int[] dx = {0,0,-1,1};
		int[] dy = {-1,1,0,0};		
		
		int cnt = 0;
		while(!buffer.isEmpty()) {
			for (Pair pair : buffer) {
				queue.add(new Pair(pair.x, pair.y));
			}
			buffer.clear();
			
			while(!queue.isEmpty()) {
				Pair node = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int nx = node.x + dx[i];
					int ny = node.y + dy[i];
					
					if (0<=nx && nx<N && 0<=ny && ny<M && box[nx][ny]==0) {
						box[nx][ny]= 1;
						buffer.add(new Pair(nx,ny));
					}				
				}
			}
			cnt++;
		}
		if (check()) System.out.println(cnt-1);
		else System.out.println(-1);
	}

	private static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 0) return false;
			}
		}
		return true;
	}	
}
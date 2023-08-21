import java.util.*;
import java.io.*;

public class BOJ7576 {

	static public class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 방향 벡터
	static int dx[] = {1, 0, -1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	static int R, C;
	static int board[][];	
	static Queue<Pair> tomatoList = new ArrayDeque<>();
	
	static int result = 0; 
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken()); 
		R = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) tomatoList.offer(new Pair(i,j));
			}
		}
		
		bfs();
		

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(board[i][j] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		result = (result == 0) ? 0 : result-1;
		
		System.out.println(result);
	
	}
	
	public static void bfs() {

		while(!tomatoList.isEmpty()) {
			Pair tomato = tomatoList.poll();

			for(int i = 0; i < 4; i++) {
				int mx = tomato.x + dx[i];
				int my = tomato.y + dy[i];
				
				if(mx < 0 || mx >= R || my < 0 || my >= C) continue; // 박스 밖에 있으면 패스
				if(board[mx][my] != 0) continue; // 이미 익었거나 토마토가 없으면 패스
				
				board[mx][my] = board[tomato.x][tomato.y] + 1; // 옮은 토마토 숫자 저장
				tomatoList.offer(new Pair(mx, my));
				
				result = board[mx][my];
			}
		}
	}
}
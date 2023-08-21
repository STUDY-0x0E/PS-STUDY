import java.io.*;
import java.util.*;

public class BOJ7569 {
	
	static class Position{
		int x;
		int y;
		int z;
		
		public Position(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	// 방향벡터(위, 아래, 왼쪽, 오른쪽, 앞, 뒤)
	static int dx[] = {0,0,-1,1,0,0};
	static int dy[] = {0,0,0,0,1,-1};
	static int dz[] = {1,-1,0,0,0,0};
	
	static int height, width, depth;
	static int board[][][];
	
	static Queue<Position> tomatoList = new ArrayDeque<>(); 
	
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		depth = Integer.parseInt(st.nextToken());
		
		board = new int[depth][height][width];
		
		for(int k = 0; k < depth; k++) {
			for(int i = 0; i < height; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < width; j++) {
					board[k][i][j] = Integer.parseInt(st.nextToken());
					if(board[k][i][j] == 1) tomatoList.offer(new Position(i,j,k)); // 익은 토마토는 큐에 담기
				}
			}
		}
		
		bfs();
		
		for(int k = 0; k < depth; k++) {
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					if(board[k][i][j] == 0) { 
						System.out.println(-1);
						return;
					}
				}
			}
		}

		System.out.println(result > 0 ? result-1 : 0); // 결과가 0이상이면 1빼고 출력(원점이 1부터 시작해서)
		
		br.close();
	}
	
	public static void bfs() {

		while(!tomatoList.isEmpty()) {
			Position tomato = tomatoList.poll();
			
			for(int i = 0; i < 6; i++) {
				int mx = tomato.x + dx[i];
				int my = tomato.y + dy[i];
				int mz = tomato.z + dz[i];
				
				if(mx < 0 || mx >= height || my < 0 || my >= width || mz < 0 || mz >= depth) continue;
				if(board[mz][mx][my] != 0) continue;
				
				tomatoList.offer(new Position(mx, my, mz));
				board[mz][mx][my] = board[tomato.z][tomato.x][tomato.y] + 1;
				
				result = board[mz][mx][my];
			}
			
		}
	}
}
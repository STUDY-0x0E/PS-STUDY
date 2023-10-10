import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, board[][], min = Integer.MAX_VALUE;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		int total = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) total++;
			}
		}
		
		melt(0, total, 0);

	}
	
	private static void melt(int cnt, int total, int prevHole) {
		total -= prevHole;
		
		if (total == 0) {
			System.out.println(cnt);
			System.out.println(prevHole);
			return;
		}
				
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] == 2) board[i][j] = 0;
			}
		}
		prevHole = findHole();		
		melt(cnt+1, total, prevHole);
	}

	private static int findHole() {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];
		int hole = 0;
		
		queue.add(new int[] {0,0});
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (inRange(nx,ny) && !visited[nx][ny]) {
					if (board[nx][ny] == 0) {
						queue.add(new int[] {nx,ny});
						visited[nx][ny] = true;
					}
					else if (board[nx][ny] == 1) {
						board[nx][ny] = 2;
						hole++;
					}
				}
			}
		}
		
		return hole;
	}

	private static boolean inRange(int nx, int ny) {
		if (0<=nx && nx<R && 0<=ny && ny<C) return true;
		return false;
	}

}
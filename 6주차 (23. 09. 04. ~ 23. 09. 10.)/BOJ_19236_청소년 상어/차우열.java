import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int next(int[][] b, int[][] f, int[][] d, int[] s) {
		for (int i=1; i<f.length; i++) {
			if (f[i][2]>0) {
				while(true) {
					int x = f[i][0] + d[f[i][2]][0];
					int y = f[i][1] + d[f[i][2]][1];
					if (x>=0 && x<b.length && y>=0 && y<b.length && !(x==s[0] && y==s[1])) {
						int idx = b[x][y];
						f[idx][0] = f[i][0];
						f[idx][1] = f[i][1];
						b[f[i][0]][f[i][1]] = idx;
						
						f[i][0] = x;
						f[i][1] = y;
						b[x][y] = i;
						break;
					}
					if (f[i][2]==8) f[i][2] = 1;
					else f[i][2]++;
				}
			}
		}
		int max = -f[0][0];
		int dx = s[0] + d[s[2]][0];
		int dy = s[1] + d[s[2]][1];
		while (dx>=0 && dx<b.length && dy>=0 &&dy<b.length) {
			if (f[b[dx][dy]][2]!=0) {
				int[][] bc = new int[b.length][b.length];
				int[][] fc = new int[f.length][f[0].length];
				for (int i=0; i<b.length; i++) {
					for (int j=0; j<b.length; j++) {
						bc[i][j] = b[i][j];
					}
				}
				for (int i=0; i<f.length; i++) {
					for (int j=0; j<f[i].length; j++) {
						fc[i][j] = f[i][j];
					}
				}
				int dir = f[bc[dx][dy]][2];
				fc[bc[dx][dy]][2] =0;
				fc[0][0] -= bc[dx][dy];
				max = Math.max(max, next(bc, fc, d, new int[] {dx, dy, dir}));
			}
			dx += d[s[2]][0];
			dy += d[s[2]][1];
		}
		return max;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int SIZE = 4;
		
		int[][] board = new int[SIZE][SIZE];
		int[][] fishes = new int[SIZE*SIZE+1][3];
		int[][] dir = {{},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
		
		
		for (int i=0; i<SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<SIZE; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				fishes[board[i][j]][0] = i;
				fishes[board[i][j]][1] = j;
				fishes[board[i][j]][2] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int[] shark = {0, 0, fishes[board[0][0]][2]};
		fishes[board[0][0]][2] = 0;
		fishes[0][0] = -board[0][0];
		
		System.out.println(next(board, fishes, dir, shark));
	}
}

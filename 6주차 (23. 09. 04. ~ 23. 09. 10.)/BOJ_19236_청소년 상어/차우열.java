package BOJ0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TeenagerShark {

	static void next(int[][] b, int[][] f, int[][] d, int[] s) {

		for (int i=1; i<f.length; i++) {
			while (true) {
				int x = f[i][0] + d[f[i][2]][0];
				int y = f[i][1] + d[f[i][2]][1];
				if (x>=0 && x<b.length && y>=0 && y<b.length) {
					if (b[x][y]!=0 && b[x][y]==0 && !(x==s[0]&&y==s[1])) {
						int[] tf = f[b[x][y]];
						f[b[x][y]] = f[i];
						f[i] = tf;
						             
						b[f[i][0]][f[i][1]] = b[x][y];
						b[x][y] = i;
						break;
					}
				}
				if (f[i][2]<d.length-1) f[i][2]++;
				else f[i][2] = 0;
			}
		}
		
		int dx = s[0] + d[s[2]][0];
		int dy = s[1] + d[s[2]][1];
		
		while (dx>=0 && dx<b.length && dy>=0 && dy<b.length) {
			if (b[dx][dy]!=0) {
				int idx = b[dx][dy];
				b[dx][dy] = 0;
				f[idx][0] = -1;
				next(b, f, d, new int[] {dx, dy, f[idx][2]});
				b[dx][dy] = idx;
				f[idx][0] = dx;
			}
			dx += d[s[2]][0];
			dy += d[s[2]][1];
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		final int SIZE = 4;
		int[][] board = new int[SIZE][SIZE];
		int[][] fishes = new int[SIZE*SIZE+1][3];
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
		fishes[0][0] = board[0][0];
		fishes[board[0][0]][0] = -1;
		board[0][0] = 0;
		int[][] dir = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	}
}

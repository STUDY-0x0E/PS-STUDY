package algo0828;

/*
1.summary:
9*9크기의 스도쿠 보드를 9줄에 걸쳐서 입력받는다.
보드에서 0이 써있는 부분에 적절한 숫자를 넣고 출력한다.

2.strategy:
보드 숫자, 0의 위치를 저장한다.
보드에서 가로, 세로, 3*3영역에서 각 숫자가 사용되었는지 확인하는 배열을 사용한다.
모든 0의 위치에서 가능한 숫자를 넣고, 해당 숫자를 사용했음을 저장하는 재귀를 사용하여 유망한 경우의 수를 탐색한다.

3.note:
0의 위치를 저장하지 않고 모든 보드를 탐색해도 시간이 충분하다.
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2580 {
	
	static boolean next(int[][] b, boolean[][][] is, ArrayList<int[]> p, int depth) {
		if (depth == p.size()) {
			StringBuilder sb = new StringBuilder();
			
			for (int i=0; i<b.length; i++) {
				for (int j=0; j<b[i].length; j++) {
					sb.append(b[i][j]).append(" ");
				}
				sb.append("\n");
			}
			
			System.out.println(sb);
			return true;
		}
		
		int x = p.get(depth)[0];
		int y = p.get(depth)[1];
		
		for (int i=0; i<b.length; i++) {
			
			if (!(is[0][x][i]||is[1][y][i]||is[2][x/3*3+y/3][i])) {
				is[0][x][i] = is[1][y][i] = is[2][x/3*3+y/3][i] = true;
				b[x][y] = i+1;
				if (next(b, is, p, depth+1)) {
					return true;
				}
				is[0][x][i] = is[1][y][i] = is[2][x/3*3+y/3][i] = false;
			}
		}
		
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int SIZE = 9;
		
		int[][] board = new int[SIZE][SIZE];
		boolean[][][] isSelected = new boolean[3][SIZE][SIZE];
		ArrayList<int[]> pos = new ArrayList<int[]>();
		for (int i=0; i<SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<SIZE; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j]!=0) {
					isSelected[0][i][board[i][j]-1] = true;
					isSelected[1][j][board[i][j]-1] = true;
					isSelected[2][i/3*3+j/3][board[i][j]-1] = true;
				} else {
					pos.add(new int[] {i, j});
				}
			}
		}
		
		next(board, isSelected, pos, 0);
		
	}

}

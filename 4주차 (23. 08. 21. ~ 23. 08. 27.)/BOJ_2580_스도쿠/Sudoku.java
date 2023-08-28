package random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sudoku {

	static int[][] board = new int[9][9];
	static ArrayList<Integer[]> blank = new ArrayList<>();
	static boolean done = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < 9; j++) {
				board[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == 0) {
					Integer[] buffer = {i,j};
					blank.add(buffer);
				}
			}
		}
		fillIn(0);
	}
	
	public static void fillIn(int index) {		
		if (index == blank.size()) {
			if (done == true)
				return;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(board[i][j]).append(" ");
				}
				sb.append("\n");
			}
			System.out.println(sb);
			done= true;
			return;
		}
		
		int x = (blank.get(index))[0];
		int y = (blank.get(index))[1];
		for (int i = 0; i < 9; i++) {
			board[x][y]= i+1;
			if (rowCheck(x,y) && colCheck(x,y) && groupCheck(x,y)) {
				fillIn(index+1);
			}
			board[x][y]= 0;
		}
	}
	

	public static boolean rowCheck(int x, int y) {
		for (int i = 0; i < 9; i++) {
			if (i == y)
				continue;
			if (board[x][i] == board[x][y])
				return false;
		}
		return true;
	}
	
	public static boolean colCheck(int x, int y) {
		for (int i = 0; i < 9; i++) {
			if (i == x)
				continue;
			if (board[i][y] == board[x][y])
				return false;
		}
		return true;		
	}
	
	public static boolean groupCheck(int x, int y) {
		int group_x = x/3;
		int group_y = y/3;
		
		for (int i = 3*group_x; i < 3*group_x+3; i++) {
			for (int j = 3*group_y; j < 3*group_y+3; j++) {
				if (i==x && j==y)
					continue;
				if (board[i][j] == board[x][y])
					return false;
			}
		}
		return true;
	}	
}

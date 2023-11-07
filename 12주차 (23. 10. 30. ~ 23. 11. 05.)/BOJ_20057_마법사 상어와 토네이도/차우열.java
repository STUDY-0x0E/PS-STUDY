package week12;

//시뮬레이션 문제
//토네이도가 언제 어디로 이동하는지, 이동시 모래가 어떻게 흩날리는지 잘 설정한다.
//더 스마트한 방법이 있을까?

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20057 {
	
	static boolean inBoard(int x, int y, int N) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
	
	static int sumSands(int[][] arr) {
		int sum = 0;
		for (int i=0; i<arr.length; i++) {
			for (int j=0; j<arr[0].length; j++) {
				sum+=arr[i][j];
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		int[][] dir = {{0,-1},{1,0},{0,1},{-1,0}};
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int f = sumSands(arr);
		int[][] dirs = new int[(N-1)*2+1][2];
		for (int i=0; i<(N-1)/2; i++) {
			for (int j=0; j<2; j++) {
				dirs[i*4+j][0] = i*2+1;
				dirs[i*4+j][1] = j;
			}
			for (int j=2; j<4; j++) {
				dirs[i*4+j][0] = i*2+2;
				dirs[i*4+j][1] = j;
			}
		}
		dirs[dirs.length-1][0] = dirs.length/2;
		dirs[dirs.length-1][1] = 0;
		
		float[][][] vals = new float[4][10][3];
		vals[0] = new float[][] {{0,-1, 0},{-1,-1,0.1f},{1,-1,0.1f}, {-1,0,0.07f},{1,0,0.07f},{0,-2,0.05f},
			{-2,0,0.02f},{2,0,0.02f},{-1,1,0.01f},{1,1,0.01f}};
			
		for (int i=0; i<vals[0].length; i++) {
			vals[1][i][0] = -vals[0][i][1];
			vals[1][i][1] = vals[0][i][0];
			vals[2][i][0] = vals[0][i][0];
			vals[2][i][1] = -vals[0][i][1];
			vals[3][i][0] = vals[0][i][1];
			vals[3][i][1] = vals[0][i][0];
			
			for (int j=1; j<4; j++) {
				vals[j][i][2] = vals[0][i][2];
			}
		}
//		System.out.println(Arrays.deepToString(vals));	
		int x = N/2;
		int y = N/2;
		for (int i=0; i<dirs.length; i++) {
			int d = dirs[i][1];
			for (int j=0; j<dirs[i][0]; j++) {
				x += dir[d][0];
				y += dir[d][1];
//				System.out.println(d+" "+x+" "+y);
				int sand = arr[x][y];
				arr[x][y] = 0;
				int sum = 0;
				for (int k=1; k<vals[d].length; k++) {
					int dx = x+(int)vals[d][k][0];
					int dy = y+(int)vals[d][k][1];
					int moved = (int)(vals[d][k][2]*sand);
					sum += moved;
					if (inBoard(dx, dy, N)) {
						arr[dx][dy]+=moved;
					}
				}
				
				int X = x+dir[d][0];
				int Y = y+dir[d][1];
				if (inBoard(X, Y, N)) {
					arr[X][Y]+=sand-sum;
				}
			}
		}
		
		int l = sumSands(arr);
		
//		for (int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		
		System.out.println(f-l);
 	}
}
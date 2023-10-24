package week10;

//구현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ21610 {
	static int idx(int N, int i) {
		if (i<0) return (i%N+N)%N;
		return i%N;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][N];
		int[][] dir = {{},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {//바구니 초기값
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//cloud[0] 현재 구름, cloud[1] 임시 구름
		boolean[][][] cloud = new boolean[2][N][N];
		
		cloud[0][N-2][0] = cloud[0][N-2][1] = cloud[0][N-1][0] = cloud[0][N-1][1] = true; //첫 구름 설정
				
		for (int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (cloud[0][i][j]) {//구름 이동
						cloud[0][i][j] = false;
						int x = idx(N,i+s*dir[d][0]);
						int y = idx(N,j+s*dir[d][1]);
						cloud[1][x][y] = true;
						arr[x][y]++; //물복사 버그전에 바구니에 물을 추가한다.
//						System.out.println(x+" "+y);
					}
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (cloud[1][i][j]) {//구름이 있던 자리, 물복사버그
						for (int k=2; k<dir.length; k+=2) {
							int x = i + dir[k][0];
							int y = j + dir[k][1];
							if (x>=0 && x<N && y>=0 && y<N && arr[x][y]>0) {
								arr[i][j]++;
							}
						}
						cloud[1][i][j] = false;
					} else {
						if (arr[i][j]>=2) cloud[0][i][j] = true;//다음 시행시 구름, 구름이 없던 자리
					}
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {//구름이 생겨난 자리는 물이 줄어든다. 미리 적용한다면 물복사를 방해할 수 있다.
					if (cloud[0][i][j]) arr[i][j]-=2;
				}
			}
//			for (int i=0; i<N; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
//			System.out.println();
			
		}
		
		int sum = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				sum += arr[i][j];
			}
		}
		System.out.println(sum);
	}
}
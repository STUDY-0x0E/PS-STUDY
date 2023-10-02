package week8;

/*
1.summary:
격자의 크기 N을 입력받고, 파이어볼의 수 M, 이동횟수 K를 입력받는다.
다음 M줄에 걸쳐 각각의 파이어볼의 행, 열, 질량, 속도, 방향을 입력받는다.
이동 후 파이어볼들이 같은칸에 있다면, 합쳐진 후 4개로 분열되면서 질량이 감소한다.
K번 이동후 남은 파이어볼의 질량을 출력한다.

2.strategy:
각 격자에 두개의 큐를 이용하여 파이어볼을 이동시키고 합칠떄 다른 큐로 이동시킨다.

3.note:
배열을 잘못 쓰지 않도록 주의한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20056 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][N];
		Queue<int[]>[][][] qs = new ArrayDeque[N][N][2];
		int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				for (int k=0; k<2; k++) {
					qs[i][j][k] = new ArrayDeque<int[]>();
				}
			}
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			qs[r][c][0].offer(new int[] {m, s, d});
		}
		
		
		for (int k=0; k<K; k++) {
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					Queue<int[]> q = qs[i][j][0];
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						int x = i + cur[1]*dir[cur[2]][0];
						int y = j + cur[1]*dir[cur[2]][1];
						if (x<0) x = (x%N)+N;
						if (x>=N) x %= N;
						if (y<0) y = (y%N)+N;
						if (y>=N) y%=N;
						qs[x][y][1].offer(cur);
					}
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					Queue<int[]> q = qs[i][j][1];
					if (q.isEmpty()) continue;
					if (q.size()==1) {
						qs[i][j][1] = qs[i][j][0];
						qs[i][j][0] = q;
						continue;
					}
					int size = q.size();
					int[] first = q.poll();
					int m = first[0];
					int s = first[1];
					int d = first[2];
					boolean diff = false;
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						m += cur[0];
						s += cur[1];
						if (cur[2]%2 != d%2) {
							diff = true;
						}
					}
					m/=5;
					if (m==0) continue;
					s/=size;
					if (diff) {
						for (int l=1; l<8; l+=2) {
							qs[i][j][0].offer(new int[] {m, s, l});
						}
					} else {
						for (int l=0; l<8; l+=2) {
							qs[i][j][0].offer(new int[] {m, s, l});
						}
					}
				}
			}
		}
		int result = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				Queue<int[]> q = qs[i][j][0];
				while (!q.isEmpty()) {
					int[] cur = q.poll();
					result += cur[0];
				}
			}
		}
		System.out.println(result);
	}

}


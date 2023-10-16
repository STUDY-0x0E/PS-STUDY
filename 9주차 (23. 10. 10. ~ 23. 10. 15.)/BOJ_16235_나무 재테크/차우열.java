package week9;

/*
1.summary:
땅의 크기 N, 처음 나무의 수 M, 시간 K를 입력받는다.
다음 N줄에 걸쳐 N*N배열에 매 년 추가할 양분을 입력받는다.
매년 다음 과정을 반복한다.
1) 모든 나무가 자신의 나이 만큼 양분을 먹고, 나이가 1 증가한다. 같은칸에 여러 나무가 있다면 어린 나무부터 양분을 먹는다.
2) 양분을 먹지 못한 나무가 해당 칸에 (나이/2)만큼 양분을 추가한다.
3) 나이가 5의 배수인 나무에 인접한 8칸에 나이가 1인 나무를 추가한다. 땅 밖에는 추가하지 않는다.
4) 입력받는 값만큼 각 위치에 양분을 추가한다.
K년 이후 남은 나무의 수를 출력한다.

2.strategy:
각 위치에 대해 우선순위큐를 만들고 시간에 따라 각 단계를 수행한다.

3.note:
각 격자에 여러 그루의 나무가 있을 수 있다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16235 {
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] nutrients = new int[N][N];
		int[][] addNut = new int[N][N];
		int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
		
		PriorityQueue<Integer>[][] pqs = new PriorityQueue[N][N];
		Queue<Integer>[][] tmpq = new ArrayDeque[N][N];
		
		
		for (int i=0; i<N; i++) {
			Arrays.fill(nutrients[i], 5);
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				addNut[i][j] = Integer.parseInt(st.nextToken());
				pqs[i][j] = new PriorityQueue<Integer>();
				tmpq[i][j] = new ArrayDeque<Integer>();
 			}
		}
		
		for (int j=0; j<M; j++) {
			st = new StringTokenizer(br.readLine());
			pqs[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1].offer(Integer.parseInt(st.nextToken())); 
		}
		
		for (int k=0; k<K; k++) {
			
			for (int i=0; i<N; i++) {//봄, 여름
				for (int j=0; j<N; j++) {
					int deadNut = 0;
					while (!pqs[i][j].isEmpty()) {
						int cur = pqs[i][j].poll();
						if (nutrients[i][j]>=cur) {
							nutrients[i][j]-=cur;
							tmpq[i][j].offer(cur+1);
						} else {
							deadNut += cur/2;
						}
					}
					nutrients[i][j] += deadNut;
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					while (!tmpq[i][j].isEmpty()) {
						int cur = tmpq[i][j].poll();
						if (cur%5==0) {
							for (int d=0; d<dir.length; d++) {
								int x = i+dir[d][0];
								int y = j+dir[d][1];
								if (x>=0 && x<N && y>=0 && y<N) pqs[x][y].offer(1);
							}
						}
						pqs[i][j].offer(cur);
					}
					nutrients[i][j] += addNut[i][j];
				}
			}
		}
		int sum = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				sum+=pqs[i][j].size();
			}
		}
		System.out.println(sum);
	}
}
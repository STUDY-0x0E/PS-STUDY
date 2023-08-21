package algo0821;

/*
1.summary:
행N, 열M을 입력받고, N*M배열에 토마토 정보를 입력받는다.
매 날짜마다 익은 토마토는 인접한 토마토를 익은 토마토로 만들 때, 모든 토마토가 익은 토마토가 되기 위한 시간을 출력한다.
만약 모든 토마토가 익을 수 없다면 -1을 출력한다.

2.strategy:
토마토의 좌표를 큐에 넣고 bfs를 사용한다.

3.note:
토마토에 인접한 토마토는 행,열 중 하나의 값만 1차이나는 위치에 있는 토마토이다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] tomato = new int[N][M];
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		for (int i=0; i<N; i++) {
			st = new  StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if (tomato[i][j]==1) q.add(new int[] {i, j});
			}
		}
		
		int[][] direc = {{1,0},{0,1},{-1,0},{0,-1}};
		int cnt = -1;
		while (!q.isEmpty()) {
			cnt++;
			Queue<int[]> newq = new ArrayDeque<int[]>();
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				for (int i=0; i<direc.length; i++) {
					int dx = cur[0]+direc[i][0];
					int dy = cur[1]+direc[i][1];
					if (dx>=0 && dx<N && dy>=0 && dy<M && tomato[dx][dy]==0) {
						tomato[dx][dy] = 1;
						newq.add(new int[] {dx, dy});
					}
				}
			}
			q = newq;
		}
		boolean flag = true;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (tomato[i][j]==0) {
					flag = false;
					break;
				}
				if (flag) continue;
				break;
			}
		}
		if (flag) {
			System.out.println(cnt);
		} else {
			System.out.println(-1);
		}
	}

}

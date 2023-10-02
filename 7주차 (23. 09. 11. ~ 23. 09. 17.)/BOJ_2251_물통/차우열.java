package week7;

/*
1.summary:
물통 3개의 최대 용량을 입력받는다.
처음에 세 번째 물통이 가득 차 있다.
각 물통에서 다른쪽 물통으로 물을 옮길 수 있는데, 한쪽물통이 가득차거나, 빌 때까지 옮겨야한다.
첫 번째 물통이 비어있을때 세번째 물통에 들어있는 물의 양을 출력한다.

2.strategy:
방문배열을 사용한 BFS를 통해 가능한 모든 경우의 수를 탐색한다.

3.note:
정답으로 출력하는 값은 오름차순이다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2251 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		final int SIZE = 3;
		int[] WB = new int[SIZE];
		for (int i=0; i<SIZE; i++) {
			WB[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][][] visited = new boolean[WB[0]+1][WB[1]+1][WB[2]+1];
		visited[0][0][WB[2]] = true;
		boolean[] lvisited = new boolean[WB[2]+1];
		lvisited[WB[2]] = true;
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {0, 0, WB[2]});
		
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i=0; i<3; i++) {
				if (cur[i]==0) continue;
				for (int j=0; j<3; j++) {
					if (i==j || cur[j]==WB[j]) continue;
					int amo = cur[i];
					if (cur[i]+cur[j]>WB[j])
						amo = WB[j]-cur[j];
					int[] tmp = new int[cur.length];
					for (int k=0; k<SIZE; k++) {
						tmp[k] = cur[k];
					}
					tmp[i]-=amo;
					tmp[j]+=amo;
					if (!visited[tmp[0]][tmp[1]][tmp[2]]) {
						visited[tmp[0]][tmp[1]][tmp[2]] = true;
						if (tmp[0]==0) {
							lvisited[tmp[2]] = true;
						}
						q.offer(tmp);
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<lvisited.length; i++) {
			if (lvisited[i]) sb.append(i).append(' ');
		}
		System.out.println(sb);
	}
}
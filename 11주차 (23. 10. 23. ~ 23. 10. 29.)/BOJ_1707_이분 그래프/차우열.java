package week11;

/*
1.summary:
각 테스트케이스에 대해 문제를 해결한다.
그래프의 정점수와 V와 간선의 수 E를 입력받는다.
다음 E줄에 걸쳐 간선정보를 입력받는다.
이분그래프는 그래프의 정점 집합을 둘로 분리했을때, 두 집합내에서 직접 연결된 정점이 없는 그래프이다.
주어진 그래프가 이분그래프이면 YES아니면 NO를 출력한다.

2.strategy:
모든 정점에서 BFS를 시도한다.
각 단계의 홀수, 짝수에서 방문배열에 1,2를 저장한다.
각 단계에서 인접한 정점이 같은 수를 가지고 있다면 NO를 출력한다.
아니라면 YES를 출력한다.

3.note:
그래프에 연결되지 않는 부분을 고려했다.
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1707 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			int[] visited = new int[V + 1];
			List<Integer>[] adjL = new ArrayList[V+1];
			for (int i=1; i<=V; i++) {
				adjL[i] = new ArrayList<Integer>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjL[a].add(b);
				adjL[b].add(a);
			}
			
			boolean flag = true;
			for (int i=1; i<=V; i++) {
				if (visited[i]!=0) continue;
				
				Queue<Integer> q = new ArrayDeque<Integer>();
				visited[i] = 1;
				q.offer(i);
				
				while (!q.isEmpty()&&flag) {
					int cur = q.poll();
					
					int val = visited[cur]%2+1;
					for (int no:adjL[cur]) {
						if (visited[no]==0) {
							visited[no] = val;
							q.offer(no);
						} else if (visited[no]!=val) {
							flag = false;
							break;
						}
					}
				}
				if (!flag) continue;
			}
			if (flag) {
				sb.append("YES").append('\n');
			} else {
				sb.append("NO").append('\n');
			}
		}
		System.out.println(sb);
	}
}

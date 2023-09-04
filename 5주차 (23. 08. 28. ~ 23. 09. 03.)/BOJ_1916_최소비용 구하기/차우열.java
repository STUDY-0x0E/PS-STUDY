package algo0904;

/*
1.summary:
정점의 개수 N을 입력받고, 다음줄에 간선의 갯수 M을 입력받는다.
다음 M줄에 걸쳐 가중치와 방향성이 있는 간선 정보가 주어진다.
다음줄에 시작점과 도착점을 입력받는다.
시작점에서 도착점 사이 간선의 최소 가중치 합을 출력한다.

2.strategy:
우선순위 큐를 이용하여 해당 지점까지의 최소 가중치인 경우에 방문한다.

3.note:
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]>[] adjL = new ArrayList[N+1];
		
		for (int i=1; i<=N; i++) {
			adjL[i] = new ArrayList<int[]>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjL[a].add(new int[] {b,c});
		}
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[] cost = new int[N+1];
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((x,y)->x[1]-y[1]);
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[S]=0;
		pq.add(new int[] {S, 0});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (cost[cur[0]]>cur[1]) continue;
			if (cur[0]==E) break;
			for (int i=0; i<adjL[cur[0]].size(); i++) {
				int[] val = adjL[cur[0]].get(i);
				if (cost[val[0]]>cost[cur[0]]+val[1]) {
					cost[val[0]] = cost[cur[0]]+val[1];
					pq.add(new int[] {val[0], cost[val[0]]});
				}
			}
		}
		System.out.println(cost[E]);
		
	}
}
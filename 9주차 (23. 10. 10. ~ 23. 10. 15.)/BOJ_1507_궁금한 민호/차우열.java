package week9;

/*
1.summary:
도시의 수 N을 입력받는다. 다음 N줄에 걸쳐 강호가 구한 각 도시간의 최소 이동시간을 입력받는다.
도로 개수가 최소일 때 모든 도로의 이동시간의 합을 출력한다.
만약 입력에 오류가 있다면 -1을 출력한다.

2.strategy:
크루스칼 알고리즘으로 최소 신장 트리를 만든다.
각 이동시간 정보에 대해서 만들어진 그래프와 일치하지 않을 때,
이동시간 정보가 더 작다면 새 도로를 추가하고, 아니라면 -1을 출력한다.

3.note:
우선순위 큐를 이용해야한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1507 {
	static boolean union(int[] p, int i, int j) {
		int I = find(p, i);
		int J = find(p, j);
		if (I==J) return false;
		p[J] = I;
		return true;
	}
	
	
	static int find(int[] p, int i) {
		if (p[i]==i) return i;
		return p[i] = find(p, p[i]);
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PriorityQueue<int[]> edges = new PriorityQueue<int[]>((x,y)->x[2]-y[2]);
		for (int i=1; i<N; i++) {
			for (int j=0; j<i; j++) {
				edges.offer(new int[] {i, j, arr[i][j]});
			}
		}
		int[] p = new int[N];
		ArrayList<int[]>[] adjL = new ArrayList[N];
		for (int i=0; i<N; i++) {
			p[i] = i;
			adjL[i] = new ArrayList<int[]>();
		}
		
		
		int cnt = 1;
		Queue<int[]> tmpq = new ArrayDeque<int[]>();
		while (!edges.isEmpty() && cnt<N) {
			int[] cur = edges.poll();
			
			if (!union(p, cur[0], cur[1])) {
				tmpq.offer(cur);
				continue;
			}
			
			adjL[cur[0]].add(new int[] {cur[1], cur[2]});
			adjL[cur[1]].add(new int[] {cur[0], cur[2]});
			cnt++;
		}
		while (!tmpq.isEmpty()) edges.offer(tmpq.poll());
		while (!edges.isEmpty()) {
			int[] tar = edges.poll();
//			System.out.println(Arrays.toString(tar));
			
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>((x,y)->x[1]-y[1]);
			pq.offer(new int[] {tar[0], 0});
			boolean[] visit = new boolean[N];
			
			int len = 0;
			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				if (visit[cur[0]]) continue;
				if (cur[0]==tar[1]) {
					len = cur[1];
					break;
				}
				visit[cur[0]] = true;
				
				for (int[] edge: adjL[cur[0]]) {
					if (visit[edge[0]]) continue;
					pq.offer(new int[] {edge[0], cur[1]+edge[1]});
				}
			}
			if (len<tar[2]) {
				System.out.println(-1);
				return;
			}
			if (len>tar[2]) {
				adjL[tar[0]].add(new int[] {tar[1], tar[2]});
				adjL[tar[1]].add(new int[] {tar[0], tar[2]});
			}
		}
		
		int sum = 0;
		for (int i=0; i<N; i++) {
			for (int[] edge: adjL[i]) sum+=edge[1];
		}
		System.out.println(sum/2);
		
	}
}
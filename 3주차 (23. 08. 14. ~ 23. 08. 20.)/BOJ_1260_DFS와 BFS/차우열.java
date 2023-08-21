package algo0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;
import java.util.StringTokenizer;

/*
1.summary:
정점 수 N, 간선 수 M, 시작 정점 V를 입력받는다. 다음 M줄에 걸쳐 간선 정보를 입력받는다.
V에서 시작해서 DFS, BFS로 모든 정점을 순회하는 순서로 정점 번호를 출력한다.

2.strategy:
DFS와 BFS를 잘 구현한다.

3.note:
인접 리스트 사용시, 오름차순으로 정점을 이동할 수 있도록 정렬이 필요하다.
*/


public class BOJ_1260 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
	
		ArrayList<Integer>[] edges = new ArrayList[N+1];
		for (int i=1; i<edges.length; i++) {
			edges[i] = new ArrayList<Integer>();
		}
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}	
		
		for (int i=1; i<edges.length; i++) {
			edges[i].sort(Comparator.naturalOrder());
		}
		
		StringBuilder sb = new StringBuilder();
		boolean[] visited = new boolean[edges.length];
		DFS(edges, V, visited, sb);
		sb.append("\n");
		BFS(edges, V, sb);
		System.out.println(sb);
		
	}
	
	static void BFS(ArrayList<Integer>[] edges, int V, StringBuilder sb) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(V);
		boolean[] visited = new boolean[edges.length];
		visited[V] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");
			for (int n:edges[cur]) {
				if (visited[n]) continue;
				q.add(n);
				visited[n] = true;
			}
			
		}
		
		
	}
	
	static void DFS(ArrayList<Integer>[] edges, int V, boolean[] visited, StringBuilder sb) {
		visited[V] = true;
		sb.append(V).append(" ");
		
		for (int n:edges[V]) {
			if(visited[n]) continue;
			DFS(edges, n, visited, sb);
		}
	}

}

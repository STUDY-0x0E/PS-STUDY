package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 오주현 {
	
	static int N;
	static List<Integer>[] link; 
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		link= new List[N+1];
		for (int i = 1; i <= N; i++) {
			link[i]= new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int node1= Integer.parseInt(st.nextToken());
			int node2= Integer.parseInt(st.nextToken());
			
			link[node1].add(node2);
			link[node2].add(node1);
		}
		
		for (int i = 1; i <= N; i++) {
			Collections.sort(link[i]);
		}

		DFS(V);
		sb.append("\n");
		BFS(V);
		
		System.out.println(sb);
		
	}

	public static void DFS(int node) {
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[N+1];
		
		stack.add(node);
		
		while (!stack.isEmpty()) {
			int next = stack.pop();
			if (!visited[next]) {
				sb.append(next+" ");
				visited[next]= true;
				
				for (int i = link[next].size()-1; i >= 0; i--) {
					int a = link[next].get(i);
					if (!visited[a]) stack.add(a);
				}
			}
		}
	}
	
	public static void BFS(int node) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		queue.offer(node);
		visited[node]= true;
		
		while (!queue.isEmpty()) {
			int next = queue.poll();
			sb.append(next+" ");
			
			for (int i = 0; i < link[next].size(); i++) {
				int a= link[next].get(i);
				if (!visited[a]) {
					visited[a]= true;
					queue.offer(a);
				}
			}
		}
	}
}
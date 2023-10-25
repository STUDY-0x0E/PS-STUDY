import java.util.*;
import java.io.*;

/*
1. summary : 트리의 지름
	트리의 임의의 두 점 사이의 거리 중 가장 긴 것을 선택
	= 가장 거리가 먼 (가중치의 합이 큰) 두 개의 노드를 선택 
2. strategy : DFS
3. note
	2 ≤ V (정점의 개수) ≤ 100,000
	주어진 거리는 모두 10000이하의 자연수
*/

public class BOJ1167 {

	static class Node {
		int vertex;
		int weight;
		
		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	
	static int N;
	static List<Node> nodes[];
	static boolean visited[];
	
	static int maxNode = 0, maxWeight = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 입력 데이터 저장하기
		N = Integer.parseInt(br.readLine());
		nodes = new ArrayList[N+1];
		for(int i = 0; i < N+1; i++) {
			nodes[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N+1; i++) { // 각 정점마다 연결된 정점 확인
			st = new StringTokenizer(br.readLine());
			
			int k = Integer.parseInt(st.nextToken()); // 하나의 정점
			
			int n = Integer.parseInt(st.nextToken()); // 연결된 정점
			while(n != -1) { // -1 값이 들어오면 더이상 연결된 정점이 없음을 의미
				int weight = Integer.parseInt(st.nextToken()); // 가중치
				
				nodes[k].add(new Node(n, weight));
				
				n = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N+1];
        visited[1] = true;
		DFS(1, 0);
		
		visited = new boolean[N+1];
        visited[maxNode] = true;
		DFS(maxNode, 0);
		
		System.out.println(maxWeight);
	}

	public static void DFS(int node, int weight) {
		
		if(maxWeight < weight) {
			maxWeight = weight;
			maxNode = node;
		}
		
		for(Node n : nodes[node]) {
			if(visited[n.vertex]) continue; // 이미 방문
			
			visited[n.vertex] = true;
			DFS(n.vertex, weight + n.weight);
		}
	}
}

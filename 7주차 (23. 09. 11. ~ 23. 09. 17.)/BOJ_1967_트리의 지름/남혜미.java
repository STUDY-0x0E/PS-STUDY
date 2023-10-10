import java.util.*;
import java.io.*;

/*
1. summary : 트리에서 거리 가중치가 가장 큰 두 노드의 거리 구하기
2. strategy : DFS
	루트에서 가장 먼 노드를 선택 후(dfs), 해당 노드에서 가장 먼 노드 선택(dfs)
3. note :
	1 ≤ n ≤ 10,000
*/

public class BOJ1967 {

	static class Node{
		int vertex;
		int weight;
		
		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	
	static int N; // 노드 개수
	static List<Node> nodes[]; // 트리
	
	static boolean visited[]; // 방문배열
	
	static int maxWeight = 0, maxNode = 0;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 데이터 입력 받기
		N = Integer.parseInt(br.readLine());
		nodes = new ArrayList[N+1];
		for(int i = 0; i < N+1; i++) {
			nodes[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			nodes[parent].add(new Node(child, weight));
			nodes[child].add(new Node(parent, weight));
		}
		
		
		visited = new boolean[N+1];
		visited[1] = true;
		dfs(1, 0); // 루트 노드부터 시작
		
		visited = new boolean[N+1];
		visited[maxNode] = true; 
		dfs(maxNode, 0); // 루트부터 가장 먼 노드부터 시작
		
		System.out.println(maxWeight);
	}
	
	public static void dfs(int node, int weight) { // 한 정점에서 가장 먼 정점 선택
		
		if(maxWeight < weight) {
			maxWeight = weight;
			maxNode = node;
		}
		
		for(Node n : nodes[node]) {
			if(visited[n.vertex]) continue; // 이미 방문한 노드
			
			visited[n.vertex] = true;
			dfs(n.vertex, weight + n.weight);
		}
	}
}
package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN1916_MinCost {
	
	static ArrayList<int[]> link[];
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		link = new ArrayList[N+1]; 
		for (int i = 1; i <= N; i++) link[i]= new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st= new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			link[from].add(new int[] {to,cost});
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		System.out.println(dijkstra(start, end));
	}

	private static int dijkstra(int start, int end) {
		int dist[] = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start]= 0;
		
		boolean visited[] = new boolean[N+1]; 
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[1]-o2[1]);
		pq.offer(new int[] {start,0});
		
		while (!pq.isEmpty()) {
			int tmp[] = pq.poll();
			int cur= tmp[0];
			if (visited[cur]) continue;
			
			dist[cur]= tmp[1];
			visited[cur]= true;
			
			for (int[] next : link[cur]) {
				int to = next[0];
				int cost = next[1];
				
				if (dist[to] < dist[cur] + cost) continue;
				dist[to]= dist[cur] + cost;
				pq.offer(new int[] {to,dist[to]});
			}
			
			if (visited[end]) break;
		}
		
		
		return dist[end];
	}

}

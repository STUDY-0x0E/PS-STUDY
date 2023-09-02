import java.util.*;
import java.io.*;

public class Main {
	
	static class Bus {
		int to;
		int cost;
		Bus next;
		public Bus(int to, int cost, Bus next) {
			this.to = to;
			this.cost = cost;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 도시 개수
		int M = Integer.parseInt(br.readLine()); // 버스 개수
		
		Bus[] busList = new Bus[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			busList[from] = new Bus(to, cost, busList[from]);
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] minDist = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			minDist[i] = 100_000_000;
		}
		
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		minDist[start] = 0;
		queue.offer(new int[] {start, minDist[start]});
		
		while(!queue.isEmpty()) {
			
			int[] data = queue.poll();
			int v = data[0];
			int d = data[1];
			
			if (visited[v])
				continue;
			
			visited[v] = true;
			
			for (Bus bus = busList[v]; bus != null; bus = bus.next) {
				if (!visited[bus.to] && minDist[bus.to] > minDist[v] + bus.cost) {
					minDist[bus.to] = minDist[v] + bus.cost;
					queue.offer(new int[] {bus.to, minDist[bus.to]});
				}
			}
		}
		
		System.out.println(minDist[end]);
	}
}
package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class weight implements Comparable<weight>{
	int to;
	int limit;
	
	public weight(int to, int limit) {
		this.to = to;
		this.limit = limit;
	}

	@Override
	public int compareTo(weight o) {
		return o.limit - this.limit;
	}
}

public class JUN1939_WeightLimit {
	
	static List<weight>[] bridge;
	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		bridge= new List[N+1];
		for (int i = 1; i <= N; i++) bridge[i]= new ArrayList<weight>();
		
		for (int i = 0; i < M; i++) {
			st= new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int limit = Integer.parseInt(st.nextToken());
			
			bridge[from].add(new weight(to,limit));
			bridge[to].add(new weight(from,limit));
		}
		
		for (int i = 1; i <= N; i++) Collections.sort(bridge[i]);
		
		st= new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		boolean visited[] = new boolean[N+1];
		
		int answer = Integer.MAX_VALUE;
		PriorityQueue<weight> pq = new PriorityQueue<weight>();
		pq.offer(new weight(start,Integer.MAX_VALUE));
		
		while (!pq.isEmpty()) {
			weight next = pq.poll();
			
			if (visited[end]) break;
			if (visited[next.to]) continue;
			
			visited[next.to]= true;
			answer= Math.min(next.limit, answer);
			
			for (weight cur : bridge[next.to]) {
				if (!visited[cur.to]) pq.offer(cur);
			}
		}
		
		System.out.println(answer);
	}

}

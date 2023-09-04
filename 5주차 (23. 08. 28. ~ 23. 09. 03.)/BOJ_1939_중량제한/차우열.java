package algo0904;
/*
1.summary:
정점 N과 간선의 수M을 입력받는다.
다음 M줄에 걸쳐 양방향이고 중량제한이 있는 간선정보를 입력받는다.
다음줄에 시작점과 도착점을 입력받는다.
시작점과 도착점을 잇는 간선들의 최대 중량제한을 출력한다.

2.strategy:
중량제한이 최대인 경우에 대한 우선순위큐에 대한 bfs를 적용한다.

3.note:
bfs에서 최대중량제한은 현재 중량제한과 간선의 중량제한중 작은값이다.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1939 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]>[] adjL = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			adjL[i] = new ArrayList<int[]>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			int w = Integer.parseInt(st.nextToken()); 
			
			adjL[a].add(new int[] {b,w});
			adjL[b].add(new int[] {a,w});
		}
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((x,y)->y[1]-x[1]);
		int[] weight = new int[N+1];
		weight[S] = Integer.MAX_VALUE;
		pq.offer(new int[] {S, Integer.MAX_VALUE});
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if (cur[0]==E) break;
			
			for (int[] no:adjL[cur[0]]) {
				int w;
				if (no[1]>cur[1]) w = cur[1];
				else w = no[1];
				
				if (w>weight[no[0]]) {
					weight[no[0]] = w;
					pq.offer(new int[] {no[0], w});
				}
			}
			
		}
		System.out.println(weight[E]);
	}

}
/*
5 5
1 2 5
2 3 4
3 1 3
3 4 5
4 5 4
1 5


 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
1. summary
	N개의 도시, 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스
	 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 
2. strategy : MST
	도시 개수 최대 1000, 버스 개수 최대 50만
	주어지는 버스의 개수 최대 10만 -> 간선이 많은 편
3. note 
	 도시의 개수 N(1 ≤ N ≤ 1,000)
	 버스의 개수 M(1 ≤ M ≤ 100,000) 
	 비용(0 < weight < 100,000)
*/
public class Main {

	static int N, M, start, arrive;
	static List<int []> busList[]; 
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 도시 개수
		M = Integer.parseInt(br.readLine()); // 버스 개수
		
		busList = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			busList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken()); // 출발지 도시 번호
			int to = Integer.parseInt(st.nextToken()); // 도착지 도시 번호
			int weight = Integer.parseInt(st.nextToken()); // 버스 비용
			
			busList[from].add(new int[] {to, weight});
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		arrive = Integer.parseInt(st.nextToken());
		
		boolean visit[] = new boolean[N+1]; // 도시 개수 (1~N)
		long minWeight[] = new long[N+1];
		
		Arrays.fill(minWeight, Integer.MAX_VALUE);
		
		minWeight[start] = 0;
		long min = 0;
		int minVertex = 0;
		
		for(int c = 1; c <= N; c++) {
			minVertex = 0;
			min = Integer.MAX_VALUE;
			
			for(int i = 1; i <= N; i++) {
				if(!visit[i] && min > minWeight[i]) {
					min = minWeight[i];
					minVertex = i;
				}
			}
			
			if(minVertex == 0) break;
			visit[minVertex] = true;
			
			for(int i = 0; i < busList[minVertex].size(); i++) {
				int vertex[] = busList[minVertex].get(i);
				if(!visit[vertex[0]] && minWeight[vertex[0]] > min + vertex[1]) {
					minWeight[vertex[0]] = min + vertex[1];
				}
			}
		}
				
		System.out.println(minWeight[arrive]);
	}
}

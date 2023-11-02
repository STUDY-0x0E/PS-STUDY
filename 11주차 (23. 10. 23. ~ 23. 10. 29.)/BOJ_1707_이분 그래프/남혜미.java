import java.util.*;
import java.io.*;

/*
1. summary : 이분 그래프
2. strategy : BFS
	그래프 (1, -1) 을 방문처리를 통해서 체크한다.
3. note : 
	2 ≤ K ≤ 5
	1 ≤ V ≤ 20,000
	1 ≤ E ≤ 200,000
*/

public class BOJ1707 {

	static int N, M;
	static int visited[];
	static List<Integer> list[];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		// 테스트 케이스
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			
			// 입력 데이터 저장
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점 수
			M = Integer.parseInt(st.nextToken()); // 간선 수
			
			list = new ArrayList[N+1]; // 정점 (1~N) 
			for(int i = 1; i <= N; i++) {
				list[i] = new ArrayList<Integer>();
			}
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				list[a].add(b);
				list[b].add(a);
			}

			visited = new int[N+1];

			// 그래프 구하기
			boolean flag = false;
			for(int i = 1; i <= N; i++) { // 정점 1~N
				if(visited[i] != 0) continue; // 이미 방문한 정점
				
				flag = checkVertex(i);
				if(!flag) break;
			}
			
			// 결과 데이터 저장하기
			if(flag) sb.append("YES").append("\n");
			else sb.append("NO").append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean checkVertex(int start) { // BFS
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		
		visited[start] = 1;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(int next : list[cur]) {
				if(visited[cur] == visited[next]) return false; // 인접 정점이 동일한 그래프 번호를 가짐
				
				if(visited[next] == 0) { // 아직 미방문 정점
					queue.add(next);
					visited[next] = -visited[cur]; // 현재 정점과 다른 그래프 번호 저장
				}
			}
		}
		return true;
	}
}

import java.util.*;
import java.io.*;

public class BOJ1260 {

	static int MAX = 1000;
	static boolean[][] board = new boolean[MAX+1][MAX+1];
	static boolean[] visit;
	
	static int N, M, startPoint;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;	
	
	public static void main(String[] args) throws Exception {
	
		// 사용자 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		startPoint = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			board[x][y] = true; board[y][x] = true;
		}
		
		// dfs 실행
		visit = new boolean[MAX+1];
		dfs(startPoint);
		
		sb.append("\n");
		
		// bfs 실행
		visit = new boolean[MAX+1];
		bfs();
		
		
		// 결과 출력
		System.out.println(sb);
	}
	
	// bfs()
	public static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		
		// 입력 받은 시작 노드 부터 시작
		q.offer(startPoint);
		visit[startPoint] = true;
		
		while(!q.isEmpty()) {
			int node = q.poll();
			sb.append(node + " ");
			
			for(int i = 1; i <= N; i++) {
				if(!board[node][i]) continue; // 연결되어 있지 않으면 패스
				if(visit[i]) continue; // 이미 방문한 노드는 패스
		
				visit[i] = true;
				q.offer(i); // 조건에 충족한 정점을 방문처리 후 큐에 담기
			}
		}
	}
	
	// dfs()
	public static void dfs(int node) {
		
		visit[node] = true;
		sb.append(node + " ");
		
		for(int i = 1; i <= N; i++) {
			if(!board[node][i]) continue; // 연결되어 있지 않으면 패스
			if(visit[i]) continue; // 이미 방문한 노드는 패스
			
			dfs(i);
		}
	}
}
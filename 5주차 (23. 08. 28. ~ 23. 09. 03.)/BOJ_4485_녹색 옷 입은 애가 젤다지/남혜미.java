import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
1. summary
	각 배열공간 방문할때마다 점수가 깍인다.
	입구(0,0) 에서 출구(N-1, N-1) 이동할때, 최대한 적게 깍이게 하도록 하자
2. strategy : Dijkstra
3. note 
	2 ≤ N(동굴의 크기) ≤ 125
	0 ≤ k(루피) ≤ 9
*/
public class Main {

	static int dir[][] = {{0,1},{0,-1},{1,0},{-1,0}};
	
	static int N, board[][], distance[][];
	static boolean visit[][];
	
	static int tc = 1;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine()); // 동굴 크기
		
		while(N != 0) { // 입력값이 0이면 테스트 종료
			
			board = new int[N][N]; // 동굴
			
			// 동굴 정보 입력받기
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			distance = new int[N][N]; // 시작점(0,0) 각 위치까지의 최소 거리 정보 담는 배열 
			visit = new boolean[N][N]; // 각 위치 방문했는지 정보 담는 배열
			
			for(int i = 0; i < N; i++) {
				Arrays.fill(distance[i], Integer.MAX_VALUE);
			}
			
			distance[0][0] = board[0][0];
			
			int min = 0;
			Point minVertex = new Point(0,0);
			// 모든 종류 돌아다녀보기
			for(int v = 0; v < N*N; v++) {
				min = Integer.MAX_VALUE;
				minVertex = new Point(-1,-1);
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(!visit[i][j] && min > distance[i][j]) {
							minVertex.x = i;
							minVertex.y = j;
							min = distance[i][j];
						}
					}
				}
				
				if(minVertex.x == N-1 && minVertex.y == N-1) break;
				visit[minVertex.x][minVertex.y] = true;
				
				for(int i = 0; i < 4; i++) {
					int mx = minVertex.x + dir[i][0];
					int my = minVertex.y + dir[i][1];
					
					if(mx < 0 || mx >= N || my < 0 || my >= N) continue;
					if(visit[mx][my]) continue;
					
					if(distance[mx][my] > distance[minVertex.x][minVertex.y] + board[mx][my]) {
						distance[mx][my] = distance[minVertex.x][minVertex.y] + board[mx][my];
					}
				}
			}
			sb.append("Problem " + (tc++) + ": " + distance[N-1][N-1] + "\n");
			
			N = Integer.parseInt(br.readLine()); // 동굴 크기
		}
		System.out.println(sb);
	}
}

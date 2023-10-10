import java.util.*;
import java.io.*;
import java.awt.Point;

/*
1. summary : 치즈 녹는 시간 구하기
	치즈(1) 는 공기(0) 와 맞닿으면 1시간 후에 녹는다. 단, 구멍(1로 둘러쌓여있는 0)은 녹지 않는다.
2. strategy : BFS
    1. (0,0) 시작으로 BFS 탐색 (공기 부분)
        - 1(치즈) 만나면, 치즈 개수 체크 후 0으로 교체 후 방문 처리
    2. 탐색이 종료된 후, 치즈 개수가 0이 아니라면 BFS 탐색 반복
3. note	:
	0 <= 가로와 세로 길이 <= 100 (10000) -> O(N^2) 이 1초를 넘기지 않는다.
*/

public class BOJ2636{

	static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 방향벡터(상,하,좌,우) 
	
	static int N, M; // 가로, 세로 길이
	static int board[][]; // 공간 배열
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		int c = 0;
		int time = 0; // 걸린 시간
		int finalC = 0; // 마지막 직전 치즈 개수
		
		while(c != -1) { // c 가 -1 이면 치즈는 이미 다 녹은거
			time++;
			finalC = c;
			c = countC();
		}
		
		System.out.println((time-1) + "\n" + finalC);
	}
	
	// BFS
	public static int countC() {
		
		int cnt = 0; // 한시간 동안 녹은 치즈 개수
		
		Queue<Point> q = new ArrayDeque<>(); // BFS 큐
		boolean visit[][] = new boolean[N][M]; // 방문배열
	
		q.offer(new Point(0,0)); // (0,0) 은 항상 0이다. (판의 가장자리)
		visit[0][0] = true;
		
		while(!q.isEmpty()) {
			
			Point current = q.poll();
			
			for(int d = 0; d < dir.length; d++) {
				int mx = current.x + dir[d][0];
				int my = current.y + dir[d][1];
				
				if(mx < 0 | mx >= N | my < 0 | my >= M) continue; // 영역 벗어간 경우
				if(visit[mx][my]) continue; // 이미 방문
				
				if(board[mx][my] == 1) { // 공기와 만난 치즈
					board[mx][my] = 0; // 치즈 녹음
					visit[mx][my] = true; // 방문 처리 (중복 제거)
					
					cnt++; 
				}
				else if(board[mx][my] == 0) {
					visit[mx][my] = true;
					q.offer(new Point(mx, my));
				}			
			}
		}
		
		if(cnt == 0) return -1;
		else return cnt;
	}
}
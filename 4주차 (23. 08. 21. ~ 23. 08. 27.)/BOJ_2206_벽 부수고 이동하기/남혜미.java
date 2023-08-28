package com.algorithm;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {

	public static class Pos{
		int x;
		int y;
		int z;
		
		public Pos(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	static int dir[][] = {{0,1},{0,-1},{1,0},{-1,0}}; // 우좌하상
	
	static int N, M, board[][][];
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 데이터 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		
		board = new int[N][M][2]; // 공간 배열 행*열*2
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				int tmp = str.charAt(j) - '0';
				if(tmp == 1) { // 벽은 -1로 저장
					board[i][j][0] = -1; // 벽 안 부순 상태
					board[i][j][1] = -1; // 벽 부순 상태
				}
				else{ 
					board[i][j][0] = 0; // 벽 안 부순 상태
					board[i][j][1] = 0; // 벽 부순 상태 
				}
			}
		}
		
		findRoute();

		if(result == 0) {
			System.out.println(-1);
		}
		else System.out.println(result);
	}
	
	public static void findRoute() {
		
		Queue<Pos> bfs = new ArrayDeque<>();
		bfs.offer(new Pos(0,0,0)); // 시작 위치 (0,0)
		board[0][0][0] = 1; // 공간 배열겸 방문 배열
		
		while(!bfs.isEmpty()) {
			Pos current = bfs.poll();
			
			if(current.x == N-1 && current.y == M-1) {
				result = board[current.x][current.y][current.z];
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int mx = current.x + dir[i][0];
				int my = current.y + dir[i][1];
				int mz = current.z;
				
				if(mx < 0 || mx >= N || my < 0 || my >= M) continue; // 공간 벗어나면 패스
				
				if(board[mx][my][mz] == 0) { // 이동 가능한 공간(0)
					board[mx][my][mz] = board[current.x][current.y][current.z] + 1;
					bfs.offer(new Pos(mx, my, mz));
				}
				else if(board[mx][my][mz] == -1){ // 벽인 경우(-1)
					if(mz == 0) { // 아직 벽 안 부순 상태
						// 부순 상태로 이동 후 큐에 담긴
						board[mx][my][1] = board[current.x][current.y][current.z] + 1;
						bfs.offer(new Pos(mx, my, 1));
					}
					else ; // 이미 벽 부순 상태는 아무것도 안하고 패스
				}
				else ; // 나머지 숫자(이미 지나간 공간)은 패스
			}
		}
	}
}
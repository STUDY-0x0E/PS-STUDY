package com.w2;

import java.io.*;
import java.util.*;

class Move {
	int time;
	char command;
	
	public Move(int time, char command) {
		this.time = time;
		this.command = command;
	}
}

class Pair { // 2차원 배열에서 위치 담을 클래스 (머리, 꼬리)
	int x;
	int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class 남혜미 {

	// 방향 벡터 (-, 오른쪽, 아래쪽, 왼쪽, 위쪽)
	static int dx[] = {0, 0, 1, 0, -1};  
	static int dy[] = {0, 1, 0, -1, 0};  

	static int N, appleCnt, moveCnt; // 배열 크기, 사과 개수, 움직이는 횟수
	static int board[][]; // N*N 공간 배열
	
	static Queue<Move> moveCommand = new ArrayDeque<>(); // 이동에 대한 정보 저장
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 데이터 입력 받기
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];

		appleCnt = Integer.parseInt(br.readLine()); // 사과 개수
		for(int i = 0; i < appleCnt; i++) { // 사과 개수 저장
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			board[x-1][y-1] = -1; // 해당 위치에 사과 있음 
		}
		
		moveCnt = Integer.parseInt(br.readLine()); // 움직이는 정보 리스트
		for(int i = 0; i < moveCnt; i++) { // 움직이는 정보 저장
			st = new StringTokenizer(br.readLine());
			int second = Integer.parseInt(st.nextToken());
			char command = st.nextToken().charAt(0);			
			moveCommand.offer(new Move(second, command));
		}
				
		int result = GameStart();

		System.out.println(result+1);
	}
	
	public static int GameStart() {
		
		// 초기값
		Pair head = new Pair(0,0); 
		Pair tail = new Pair(0,0); 
		
		int currentTime = -1, currentDir = 1; // 현재 정보를 담고 있는 변수(현재시간 : 0, 현재 방향: 오른쪽) 
		int moveTime = 1, moveDir = 1; // (입력 정보 받을 변수)
		board[head.x][head.y] = 1;
		
		while(true) {
			// 시간 증가
			currentTime++;
			
			// 입력값 체크(방향 변경)
			if(currentTime == moveTime) { // 입력값 시간 도달 시
				currentDir = moveDir; //  입력값에 해당하는 방향값 변경
				
				if(!moveCommand.isEmpty()) { // 입력값이 있는지 확인
					Move move = moveCommand.poll();
					moveTime = move.time; // 다음 액션을 취할 시간 입력받아두기
					char c = move.command;; // 다음 액션 입력받아두기
					
					// 다음 방향 변경
					if(c == 'D') moveDir++;
					else if(c == 'L') moveDir--;
					
					if(moveDir == 0) moveDir = 4; // 방향벡터 인덱스가 0이 되면 인덱스 4번(위쪽) 으로 변경
					else if(moveDir == 5) moveDir = 1; // 방향벡터 인덱스가 5이 되면 인덱스 1번(아래쪽)으로 변경
				}
			}
			
			// 뱀 이동
			board[head.x][head.y] = currentDir; 
			head.x += dx[currentDir]; head.y += dy[currentDir]; // 머리 이동(현재 방향으로 이동)
			
			if(head.x < 0 || head.x >= N || head.y < 0 || head.y >= N) {break;} // 벽에 부딪히면 종료
			if(board[head.x][head.y] != 0 && board[head.x][head.y] != -1) {break;} // 몸이랑 충돌하면 종료(빈공간, 사과 아니면)

			if(board[head.x][head.y] == -1) {}  // 사과가 있는 경우(꼬리 이동X)
			else if(board[head.x][head.y] == 0) { // 사과가 없는 경우(꼬리 이동)
				int tmpDir = board[tail.x][tail.y]; // 꼬리가 이동해야할 방향
				board[tail.x][tail.y] = 0; // 0으로 초기화 
				
				tail.x += dx[tmpDir]; tail.y += dy[tmpDir]; // 꼬리 이동 (충동 여부 체크 할 필요 없다)
			}
			board[head.x][head.y] = currentDir; // 머리 위치에 현재 방향 값 저장	
		}
		
		return currentTime;
	}
	
	public static void checkBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}
	}
}

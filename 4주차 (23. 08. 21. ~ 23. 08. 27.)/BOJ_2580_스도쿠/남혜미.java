package com.algorithm;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2580 {

	static final int MAX = 9;
	
	static int board[][] = new int[MAX][MAX]; // 스도쿠 저장
	static List<Point> checkArea = new ArrayList<>();
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 스도쿠 입력 받기
		for(int i = 0; i < MAX; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < MAX; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 0) checkArea.add(new Point(i,j));
			}
		}
		
		// 구현
		completeSudoku(0);
		
		// 결과 출력
		System.out.println(sb);
	}
	
	public static boolean completeSudoku(int cnt) {
		if(cnt == checkArea.size()) {
			for(int i = 0 ; i < MAX; i++){
				for(int j = 0; j < MAX; j++) {
					sb.append(board[i][j] + " ");
				}
				sb.append("\n");
			}
			return true;
		}
		
		Point current = checkArea.get(cnt);
		for(int i = 1; i < 10; i++) {
			// System.out.println(current + " " + i);
			board[current.x][current.y] = i; // 1~9 숫자 대입해보기
			if(checkNum(current)) { // 스도쿠 조건에 맞는지 확인
				board[current.x][current.y] = 0; // 조건에 맞지 않으면 다시 0으로 초기화
				continue; 
			}
			if(completeSudoku(cnt + 1)) return true;
			
			board[current.x][current.y] = 0;
		}
		return false;
	}
	
	public static boolean checkNum(Point current) {
		
		boolean visit[];
				
		visit = new boolean[11];
		for(int i = 0; i < MAX; i++) {
			if(board[current.x][i] == 0) continue; // 0은 패스
			if(i != current.y && board[current.x][i] == board[current.x][current.y]) return true;
			// if(visit[board[current.x][i]]) return true; // 숫자 중복
			visit[board[current.x][i]] = true;
		}

		visit = new boolean[11];
		for(int i = 0; i < MAX; i++) {
			if(board[i][current.y] == 0) continue; // 0은 패스 
			if(visit[board[i][current.y]]) return true; // 숫자 중복
			visit[board[i][current.y]] = true;
		}
		
		visit = new boolean[11];
		int x = (current.x/3) * 3, y = (current.y/3) * 3;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[x + i][y + j] == 0) continue; // 0은 패스 
				if(visit[board[x + i][y + j]]) return true; // 숫자 중복
				visit[board[x + i][y + j]] = true;
			}
		}
		return false;
	}
}
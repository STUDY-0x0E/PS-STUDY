package com.w2;

import java.io.*;
import java.util.*;

public class 남혜미 {

	static int N, K; // N : 컨테이너 길이, K : 종료 조건 내구도 0 갯수
	static int[][] container; // 컨테이너[0] : 내구도, 컨테이너[1] : 로봇 존재여부
	
	static int result = 0; 
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 데이터 입력 받기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		container = new int[2][2*N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 2*N; i++) {
			container[0][i] = Integer.parseInt(st.nextToken()); // 내구도 기록
		}

		
		int startIdx = 0; int endIdx = N-1; // 로봇을 올리는 위치, 내리는 위치
		int zeroContainerCnt = 0; // 내구도 0 컨테이너 갯수
		
		// 회전
		while(K > zeroContainerCnt) {
			result++; // 단계 증가
			
			// container 회전 (컨베이어 벨트의 회전을 올리고 내리는 인덱스를 회전으로 구현) 
			startIdx = (startIdx-1 < 0) ? (2*N)-1: startIdx-1;  // startIdx가 0보다 작아지면 (2*N-1)로 이동
			endIdx = (endIdx-1 < 0) ? (2*N)-1: endIdx-1; // endIdx 가 0보다 작아지면 (2*N-1)로 이동
			container[1][endIdx] = 0; // 로봇 내리기
			
			// robot 이동 (가장 먼저 올라간 로봇부터 이동)			
			int checkIdx = (endIdx-1 < 0) ? (2*N)-1 : endIdx-1; // endIdx 는 항상 비워져 있기 때문에, endIdx-1 부터 로봇 존재여부 확인 후 다음칸으로 이동 
			while(checkIdx != endIdx) { // checkIdx : 로봇 이동시킬 칸
				if(container[1][checkIdx] == 1) { // 해당칸에 로봇 있으면 이동시키기
					int tmpIdx = (checkIdx + 1 >= 2*N) ? 0 : checkIdx+1; // 이동시킬 간 위치
					
					if(container[1][tmpIdx] == 0 && container[0][tmpIdx] > 0) { // 이동시킬 칸에 로봇 있으면 이동 X, 내구도 없으면 이동 X
						container[1][checkIdx] = 0; // 해당칸은 로봇 제거
						if(tmpIdx != endIdx) { // 이동시킬 칸이 끝칸이 아니면 옮기기(끝칸이면 내리기)
							container[1][tmpIdx] = 1;
						}
						container[0][tmpIdx]--; // 내구도 감소
						if(container[0][tmpIdx] == 0) zeroContainerCnt++; // 내구도 감소 후 0이 됐으면 기록
					}
				}
				checkIdx = (checkIdx-1 < 0) ? (2*N)-1 : checkIdx-1; // 다음 이동시킬 순서 로봇 체크
			}
			
			// robot 새로 올리기
			if(container[0][startIdx] > 0) { // 올릴 칸 내구도 있는지 확인하고 감소 (시작칸은 끝간을 넘겨받기 때문에 무조건 비워져 있음)
				container[0][startIdx]--;
				container[1][startIdx] = 1; // 로봇 올리기
				if(container[0][startIdx] == 0) zeroContainerCnt++; // 내구도 감소 후 0이 됐으면 기록
			}
			
			// 내구도 확인
//			zeroContainerCnt = 0;
//			for(int i = 0; i < 2*N; i++) {
//				System.out.print(container[0][i] + " ");
//				// if(container[0][i] == 0) zeroContainerCnt++; // 내구도 0인칸 확인
//			}
//			System.out.println("zeroContainerCnt : " + zeroContainerCnt);
		}
		
		System.out.println(result);
	}
}
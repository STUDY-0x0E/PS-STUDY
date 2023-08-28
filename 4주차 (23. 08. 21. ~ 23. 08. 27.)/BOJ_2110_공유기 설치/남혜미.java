package com.algorithm;

import java.util.*;
import java.io.*;


public class BOJ2110 {

	static int N, M, houseList[];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 데이터 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		houseList = new int[N];
		
		for(int i = 0; i < N; i++) {
			houseList[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(houseList);
		
		int minDis = 1;
		int maxDis = houseList[N-1] - houseList[0] + 1;
		
		while(minDis < maxDis) { 
			
			int mid = (minDis + maxDis)/2; // 설치 가능한 최소 거리 (출력값)
			
			// mid 거리마다 설치 했을때, 
			if(canInstall(mid) < M) { // M보다 적게 설치되면 설치 간격을 줄이기 위해
				maxDis = mid; // max 에 mid 값 지정
			}
			else { // M보다 많게 설치되면 설치 간격을 늘리기 위해
				minDis = mid + 1; // min 에 mid+1 값 지정
			}
		}
		
		// Upper Bound는 탐색 값을 초과하는 첫 번째 값을 가리키므로, 1을 빼줘야 한다.
		System.out.println(minDis - 1);
	}
	
	public static int canInstall(int distance) {
		
		int lastLocate = houseList[0]; // 첫번째 집
		int count = 1; // 첫번째 집 무조건 설치
		
		for (int i = 0; i < houseList.length; i++) { // 집마다 체크
			int locate = houseList[i]; // 집 위치
			
			if(locate - lastLocate >= distance) { // 이전에 설치된 집과 현재 집의 거리가 기준 거리보다 멀면 조건 충족
				count++; // 공유기 설치 완료
				lastLocate = locate; // 이전 집 위치를 현재 집으로 업데이트
			}
		}
		
		return count;
	}
}
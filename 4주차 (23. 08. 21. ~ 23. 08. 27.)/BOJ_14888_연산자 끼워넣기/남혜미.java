package com.algorithm;

import java.util.*;
import java.io.*;

public class BOJ14888 {
	
	static int N, numList[], operatorList[];
	static int fourArithm[] = new int[4];
	
	static long MinResult = Long.MAX_VALUE; 
	static long MaxResult = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		numList = new int[N];
		operatorList = new int[N];
		
		// 숫자 배열
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) numList[i] = Integer.parseInt(st.nextToken());
		
		// 각 연산자마다 사용가능한 개수 배열
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) fourArithm[i] = Integer.parseInt(st.nextToken());

		Perm(1); // operatorList에 1부터 N-1개를 담을 거라서 1부터 시작
	
		System.out.println(MaxResult + "\n" + MinResult);
	}
	
	public static void Perm(int cnt) {
		
		if(cnt == N) {
			Cal();
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(fourArithm[i] == 0) continue;
			
			fourArithm[i]--;
			operatorList[cnt] = i;
			Perm(cnt+1);
			fourArithm[i]++;
		}
	}
	
	public static void Cal() {
		long result = numList[0];
		for(int i = 1; i < N; i++) {
			if(operatorList[i] == 0) { // 덧쌤
				result += numList[i];
			}
			else if(operatorList[i] == 1) {
				result -= numList[i];
			}
			else if(operatorList[i] == 2) {
				result *= numList[i];
			}
			else {
				result /= numList[i];
			}
		}
		
		MinResult = Math.min(MinResult, result);
		MaxResult = Math.max(MaxResult, result);
	}
}
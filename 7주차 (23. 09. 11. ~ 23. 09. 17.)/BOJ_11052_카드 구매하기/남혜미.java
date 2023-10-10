package com.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. summary : 최대비용으로 카드팩 지불하기
	카드팩 종류는 N 개이고 가격이 다르다. (카드1개 ~ N개)
	N개의 카드를 갖는 경우 카드팩을 적절하게 골라 최대비용 지불
2. strategy : DP
	카드팩 1개짜리만 사용하는 경우
	카드팩 1,2개짜리만 사용하는 경우
	카드팩 1,2,..., n개짜리만 사용하는 경우
3. note : 
	1 ≤ N (카드 수) ≤ 1,000
*/

public class BOJ11052 {

	static int N;
	static int board[];
	static int D[];
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N+1];
		D = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) { // 카드팩 종류
			board[i] = Integer.parseInt(st.nextToken());
			
			for(int j = i; j < N+1; j++) { // 카드 수
				D[j] = Math.max(D[j], D[j-i] + board[i]);  
			}
		}

		System.out.println(D[N]);
		
	}
}
package com.algorithm;

import java.util.*;
import java.io.*;

public class BOJ2805{

	static int N, M;
	static int board[];
	
	static long total = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 입력 받을 나무 개수
		M = Integer.parseInt(st.nextToken()); // 필요한 나무 길이
		board = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			board[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(board); // 정렬 

		for(int i = 1; i <= N; i++) {
			// 그 다음 큰 나무길이 만큼 자르게 됐을때 얻을 수 있는 나무 길이
			long gap = (board[N+1-i] - board[N-i]); 
			
			if((gap * i) < M) { // 다음 나무까지 잘라야 한다.
					total += gap; // 다음 높이 까지 자르기
					M -= (gap * i); // 추가로 필요한 길이
			}
			else { 
				total += (M%i == 0) ? M/i : M/i+1;
				break;
			}
		}
		System.out.println(board[N-1] - total);
	}
}
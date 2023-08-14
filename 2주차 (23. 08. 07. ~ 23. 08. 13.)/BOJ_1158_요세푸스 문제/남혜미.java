package com.w2;

import java.io.*;
import java.util.*;

public class 남혜미 {
	
	public static List<Integer> result = new ArrayList<>();
	public static Deque<Integer> que = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception{

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 입력 받기
		for(int i = 1; i <= n; i++) {
			que.add(i);
		}
		
		// 요세푸스 순열 만들기
		int i = 1;
		while(!que.isEmpty()) {
			int tmp = que.peekFirst();
			que.removeFirst();
			if(i == m) {
				result.add(tmp);
				i = 1;
			}
			else {
				que.addLast(tmp);
				i++;
			}
		}
		
		// 출력
		bw.write("<" + result.get(0));
		for(int j = 1; j < n; j++) {
			bw.write(", " + result.get(j));
		}
		bw.write(">");
		
		bw.flush();
	}
}
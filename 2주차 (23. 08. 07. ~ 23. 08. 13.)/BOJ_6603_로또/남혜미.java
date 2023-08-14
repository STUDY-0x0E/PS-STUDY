package com.w2;

import java.io.*;
import java.util.*;

public class 남혜미 {

	static final int lotto = 6;
	
	static int[] inputData = new int[13]; 
	static int[] chooseData = new int[6];
	static int k;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
	
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			k = Integer.parseInt(st.nextToken()); 
			if(k == 0) break; 
			
			for(int i = 0; i < k; i++) {
				inputData[i] = Integer.parseInt(st.nextToken());
			}
			
			Com(0, 0);
			sb.append("\n");
		}		
		System.out.println(sb);
	}
	
	public static void Com(int cnt, int start) {
		
		if(cnt == lotto) {
			for(int i = 0; i < chooseData.length; i++) {
				sb.append(chooseData[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i < k; i++) {
			chooseData[cnt] = inputData[i];
			Com(cnt + 1, i + 1);
		}
	}
}
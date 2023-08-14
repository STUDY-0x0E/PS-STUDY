package com.w2;

import java.io.*;
import java.util.*;

public class 남혜미 {

	static List<Character> edit = new LinkedList<>(); 
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		String startText = br.readLine();

		int cursor = startText.length();
		for(int i = 0; i < cursor; i++) {
			edit.add(startText.charAt(i)); // 배열은 0부터 시작해서 마지막 인덱스가 n이면, 길이는 n+1이다.
		}
		
		int cnt = Integer.parseInt(br.readLine());

		for(int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			
			if(c == 'P') {
				char addText = st.nextToken().charAt(0);
				
				edit.add(cursor++, addText); // 커서 있는 위치에 추가하고 커서를 오른쪽으로 한칸 이동시킨다.
			}
			else if(c == 'L'){
				if(cursor == 0) continue;
				cursor--; // 커서를 왼쪽으로 이동시킨다.
			}
			else if(c == 'D') {
				if(cursor == edit.size()) continue;
				cursor++; // 커서를 오른쪽으로 이동시킨다.
			}
			else if(c == 'B') {
				if(cursor == 0) continue;
				
				cursor--; // 왼쪽에 있는 문자를 지워야 하기 때문에 한칸 이동후에 지운다.
				edit.remove(cursor); 
			}
		}
		
		for(Character c : edit) {
			bw.write(c);
		}
		bw.flush();
	}
}
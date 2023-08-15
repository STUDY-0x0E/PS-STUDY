package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class 오주현 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int len = s.length();
		LinkedList<Character> str = new LinkedList<>();
		
		int M = Integer.parseInt(br.readLine());
		int pos = len;
		
		for (int i = 0; i < len; i++) {
			str.add(s.charAt(i));
		}
		
		for (int i = 0; i < M; i++) {	
			String s1 = br.readLine();
			switch (s1.charAt(0)) {
			case 'L':
				pos = pos-1 < 0? pos : pos-1;
				break;
			case 'D':
				pos = pos+1 > len? pos : pos+1;
				break;
			case 'B':
				if (pos-1 >= 0) {
					str.remove(pos---1);
					len--;
				}
				break;
			case 'P':
				str.add(pos++, s1.charAt(2));
				len++;
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (Character st : str) {
			sb.append(st);
		}
		
		System.out.println(sb);
	}
}

import java.util.*;
import java.io.*;

/*
1. summary : 문자열 폭발
	문자열 안에 특수 문자열은 사라진다.
	폭발하고 난 후 남은 문자열은 합쳐진다.
	특수 문자가 모두 폭발하고 남은 문자열을 출력하자
2. strategy : 스택
	
3. note
	폭발 문자열은 같은 문자를 두 개 이상 포함하지 않는다.
	문자열은 알파벳 대문자, 소문자, 숫자로 구성되어 있다.
	1 <= 문자열 길이 <= 1,000,000
	1 <= 폭발 문자열 길이 <= 36
*/

public class BOJ9935 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String fullString = br.readLine();
		String subString = br.readLine();
		int subStringlength = subString.length() - 1;
		
		String result = "FRULA";

		char c = subString.charAt(subStringlength); // 폭발 문자열 마지막 문자

		Deque<Character> list = new ArrayDeque<>(); 		
		for(int i = 0; i < fullString.length(); i++) { // 전체 문자열의 첫번째 문자부터 체크
			list.addLast(fullString.charAt(i)); // 결과 문자열에 저장
			
			if(list.peekLast() == c && list.size() >= subString.length()) { // 현재 저장한 문자열이 폭발 문자열 마지막 문자와 같은지 체크 & 길이 체크
				Deque<Character> tmpList = new ArrayDeque<>(); // 삭제 문자 임시 저장 배열
				
				for(int index = 0; index <= subStringlength; index++) { // 폭발 문자열 체크
					if(subString.charAt(subStringlength - index) == list.peekLast()){ // 폭발 문자열 문자와 같으면
						tmpList.addLast(list.pollLast()); // 바로 삭제하지 말고 임시 문자열에 저장
					}
					else { // 만약에 폭발 문자열 완성하지 못하면
						while(!tmpList.isEmpty()) { 
							list.addLast(tmpList.pollLast()); // 임시 문자열 복원
						}
						break;
					}
				}
			}
		}
		
		// 결과 출력
		if(list.isEmpty()) System.out.println(result); // 문자열 비어있는 경우
		else { // 문자열 남아있는 경우
			StringBuilder sb = new StringBuilder();
			while(!list.isEmpty()) {
				sb.append(list.pollFirst());
			}
			System.out.println(sb);
		}
	}
}
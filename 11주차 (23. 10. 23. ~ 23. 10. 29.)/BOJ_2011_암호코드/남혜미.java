import java.util.*;
import java.io.*;

/*
1. summary : 암호 코드
	A(1), B(2), C(3), ..., Z(26) 암호화
	주어진 숫자를 통해 얻을 수 있는 문자열 가짓수 찾아내기
2. strategy : DP
	한글자씩 추가하면서 체크
	- 새로 추가되는 한글자 또는 직전 글자 포함 두글자
	- 중복되는 부분은 메모이제이션 활용
	암호가 잘못되어 해석할 수 없는 경우 0 출력 (숫자 0이 연속 두번 들어오는 경우)
3. note :
	0 < 문자열 길이 <= 5000
	1000000 으로 나눈 나머지 값 출력
*/

public class BOJ2011 {

	static int result = 0;
	static int DP[] = new int[5001]; 
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String code = br.readLine();
		int length = code.length();
		
		if(length != 1 && checkNumber(code.substring(0,2))) DP[0] = 1;
		if(checkNumber(code.substring(0,1))) DP[1] = 1; // 숫자 1개
		
		for(int i = 1; i < length; i++) {  
			String tempOne = code.substring(i,i+1);
			String tempTwo = code.substring(i-1,i+1);

			if(checkNumber(tempOne) && checkNumber(tempTwo)) {
				DP[i+1] = (DP[i] + DP[i-1]) % 1000000; 
			}
			else if(checkNumber(tempOne)) DP[i+1] = DP[i];
			else if(checkNumber(tempTwo)) DP[i+1] = DP[i-1];
			else {
				DP[length] = 0;  
				break;
			}
		}
		
		System.out.println(DP[length]);
	}
	
	static boolean checkNumber(String number) { // 유효한 숫자인지 체크
		// 두자리수중에서 0으로 시작하는 경우는 제외
		if(number.length() > 1 && number.charAt(0) == '0') { 
			return false;
		}
		int num = Integer.parseInt(number);
		if(0 < num && num < 27) return true; // 암호 코드 범위 내 숫자인지 체크
		return false;
	}
}

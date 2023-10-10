import java.util.*;
import java.io.*;

/*
1. summary : 와인을 가장 많이 마시는 경우
	와인은 3개 연속으로 먹을 수 없다.
2. strategy : choose[i] : i번째 최고 값 
	1. i번째 와인을 고르는 경우 
		1-1.choose[i-2] + i // # _ # (이전값을 고르지 않는 경우)
		1-2.choose[i-3] + i + i-1 // # _ # # (이전값을 고르는 경우) 
	2. i번째 와인을 고르지 않는 경우 choose[i-1] // # # _ 
3. note : 입력값이 1개인경우와, 2개인 경우를 따로 구현해야 한다.
*/
public class BOJ2156 {

	static int N;
	static int wine[], choose[];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 데이터 저장
		N = Integer.parseInt(br.readLine());
		wine = new int[N+1]; 
		for(int i = 1; i < N+1; i++) { // 와인 0~N-1
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		if(N >= 3) {
			// 와인 선택
			choose = new int[N+1]; 
			choose[0] = 0; // 첫번째 와인[0] 선택
			choose[1] = wine[1]; // 두번째 와인[1] 선택
			choose[2] = wine[2] + wine[1]; // 두번째 와인[1] 선택
			for(int i = 3; i <= N; i++) { // 세번째 와인[3]부터 마지막 와인[N-1]까지 선택해보기
				choose[i] = Math.max(choose[i-1], Math.max(choose[i-3] + wine[i-1] + wine[i], choose[i-2] + wine[i]));
			}
			System.out.println(choose[N]);
		}
		else if(N == 2) {
			System.out.println(wine[1] + wine[2]);
		}
		else if(N == 1) {
			System.out.println(wine[1]);
		}
	}
}
package DAY0926;

import java.util.*;
import java.io.*;

/*
1. summary : 선택된 수의 합이 최대가 되는 경로
2. strategy : DP
    
    마지막 줄(N) 제외, N-1번째줄부터 1번째줄까지 각 위치에서 대각선 아래 오른쪽, 대각선 아래 왼쪽 값 중에 더 큰 값을 골라 자신 값과 더해 업데이트 시킨다. 
    
    배열의 1번 인덱스부터 사용한다고 했을때
    
    대각선 아래 왼쪽은 현재 인덱스 + (삼각형) 줄
    
    대각선 아래 오른쪽 : 현재 인덱스 + (삼각형)  줄 + 1 
    
3. note
*/

public class BOJ1932 {

	static int N; // 삼각형 크기
	static List<Integer> list = new ArrayList<>(); // 일차원 배열 사용
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		list.add(0); // 1번 인덱스부터 유효값 저장
		for(int i = 1; i <= N ; i++) { 
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= i; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		
		
		int index = list.size(); // 마지막 인덱스 + 1
		
		for(int i = N; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				index--;
				if(i != N) {
					int updateNum = list.get(index) + Math.max(list.get(index + i), list.get(index + i + 1));
					list.set(index, updateNum);
				}
			}
		}
		
//		index = 1;
//		for(int i = 1; i <= N ; i++) { 
//			for(int j = 1; j <= i; j++) {
//				System.out.print(list.get(index++) + " ");
//			}
//			System.out.println();
//		}

		System.out.println(list.get(1));
	}
}
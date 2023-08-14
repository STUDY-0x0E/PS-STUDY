package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1.summary:
각 테스트 케이스에서 받을 숫자의 수 N과 N개의 숫자를 입력받는다.
N개의 숫자중 6개의 숫자를 고른 조합을 출력한다.
N이 0이면 종료한다.

2.strategy:
6개의 숫자조합을 재귀 방식으로 출력한다.

3.note:
오름차순 출력에 주의한다.
*/

public class Lotto {

	public static void comb(int[] arr, int n, int s, int[] num, StringBuilder sb) {
		if (n==0) {
			for (int i=0; i<num.length; i++) {
				sb.append(num[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i=s; i<=arr.length-n; i++) {
			num[num.length-n] = arr[i];
			comb(arr, n-1, i+1, num, sb);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if (N==0) break;
			
			int[] arr = new int[N];
			for (int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			comb(arr, 6, 0, new int[6], sb);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}

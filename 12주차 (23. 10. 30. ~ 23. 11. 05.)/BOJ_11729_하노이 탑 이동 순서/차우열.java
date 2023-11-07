package week12;

//재귀 문제
//각 단계에서 적절한 값을 출력한다.
//dp처럼 미리 계산한 값을 이용하면 시간을 줄일 수 있다.
//arraylist<int[]>를 이용하면 stringbulider[]보다 메모리를 줄일 수 있다.

import java.util.Scanner;

public class BOJ11729 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		
		
		StringBuilder[][][] sbs = new StringBuilder[K+1][4][4];
		int[] cal = new int[K+1];
		cal[1] = 1;
		
		for (int i=1; i<K; i++) {
			cal[i+1] = cal[i]*2+1;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(cal[K]);
		hanoi(K, 1, 3, 2, sbs);
		sb.append('\n').append(sbs[K][1][3]);
		System.out.println(sb);
	}
	
	static void hanoi(int val, int s, int e, int ne, StringBuilder[][][] sbs) {
		if (sbs[val][s][e]!=null) return;
		if (val == 1) {
			sbs[val][s][e] = new StringBuilder();
			sbs[val][s][e].append(s).append(' ').append(e).append('\n');
		} else {
			sbs[val][s][e] = new StringBuilder();
			hanoi(val-1, s, ne, e, sbs);
			hanoi(val-1, ne, e, s, sbs);
			sbs[val][s][e].append(sbs[val-1][s][ne]).append(s).append(' ').append(e).append('\n').append(sbs[val-1][ne][e]);
		}
	}
}
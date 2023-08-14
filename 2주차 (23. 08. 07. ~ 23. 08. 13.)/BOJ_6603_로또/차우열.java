package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1.summary:
�� �׽�Ʈ ���̽����� ���� ������ �� N�� N���� ���ڸ� �Է¹޴´�.
N���� ������ 6���� ���ڸ� �� ������ ����Ѵ�.
N�� 0�̸� �����Ѵ�.

2.strategy:
6���� ���������� ��� ������� ����Ѵ�.

3.note:
�������� ��¿� �����Ѵ�.
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

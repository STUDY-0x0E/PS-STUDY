package week11;

/*
1.summary:
물건의 수N과 가방의 무게제한 C를 입력받는다.
N개의 물건의 무게를 입력받는다.
각각의 물건을 최대 하나씩 가방에 넣을 때, 무게의 합이 C이하인 경우의 수를 출력한다.

2.strategy:
모든 물건에 대해 무게순으로 정렬한다.
가장 무거운 물건부터 가방에 넣어본다. 넣을 수 있다면, 그 물건을 넣었을때의 모든 경우의 수를 재귀로 구한다.
단, 가장 가벼운 물건부터 i번째 물건까지 모두 가방에 넣을 수 있다면, 발생하는 경우의 수는 2^i개이다.
이는 아무 물건도 넣지 못할 때, i가 0일때도 성립한다.

3.note:
모든 물건의 무게합이 int범위를 넘을 수 있다.
시간제한이 생각보다 널널하다. 주석친 부분을 이분탐색으로 찾을경우 더 시간이 많이 걸린다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1450 {
	static int cal(int[] arr, int N, int C, long[] acum, int[] pow) {
		int sum = 0;
		for (int i=N; i>=0; i--) {
			if (acum[i]<=C) { //이분 탐색으로 찾을 수 있다.
				sum += pow[i];
				break;
			} else if (arr[i]<=C) { //이분 탐색으로 찾을 수 있다.
				sum += cal(arr, i-1, C-arr[i], acum, pow);
			}
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		long[] acum = new long[N + 1];
		int[] pow = new int[N + 1];

		acum[0] = 0;
		pow[0] = 1;
		for (int i = 1; i <= N; i++) {
			acum[i] = arr[i] + acum[i - 1];
			pow[i] = pow[i - 1] * 2;
		}

		System.out.println(cal(arr, N, C, acum, pow));

	}
}
/*

package week11;


strategy 
중간에서 만나기(meet in the middle) 알고리즘을 사용하여 O(2^N)을 O(2*2^(N/2))로 만든다.
주어진 입력을 절반씩 나눠서 가능한 경우의 수를 전부 구한다.
투포인터를 사용하여 한쪽 입력의 합이 만들 수 있는 경우의 수를 구한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1450_1 {
	static long[] getArr(StringTokenizer st, int N, int[] pow) {
		long[] arr = new long[pow[N]];
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			int size = cnt;
			int val = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size; j++) {
				arr[cnt++] = arr[j]+val;
			}
		}

		return arr;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] pow = new int[N - N / 2 + 1];

		pow[0] = 1;
		for (int i = 1; i < pow.length; i++) {
			pow[i] = pow[i - 1] * 2;
		}

		long[] arr1 = getArr(st, N/2, pow);
		long[] arr2 = getArr(st, N - N / 2, pow);

		Arrays.sort(arr1);
		Arrays.sort(arr2);

		int p = 0;
		int cnt = 0;
		for (int i = arr1.length - 1; i >= 0; i--) {

			while (p < arr2.length && arr2[p] + arr1[i] <= C) {
				p++;
			}
			cnt+=p;
		}
		System.out.println(cnt);
	}

}


*/

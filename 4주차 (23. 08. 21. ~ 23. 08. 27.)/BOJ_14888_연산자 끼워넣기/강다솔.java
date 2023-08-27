package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_14888 {

	private static int N, numbers[], opNum[];
	private static int max, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		max = -Integer.MAX_VALUE;
		min = Integer.MAX_VALUE;
		solution(1, numbers[0], opNum[0], opNum[1], opNum[2], opNum[3]);
		System.out.println(max);
		System.out.println(min);
	}

	private static void solution(int index, int result, int add, int minus, int multi, int divide) {
		if (index == N) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		if (add > 0) {
			solution(index + 1, result+numbers[index], add - 1, minus, multi, divide);
		}
		if (minus > 0) {
			solution(index + 1, result-numbers[index], add, minus - 1, multi, divide);
		}
		if (multi > 0) {
			solution(index + 1, result*numbers[index], add, minus, multi - 1, divide);
		}
		if (divide > 0) {
			solution(index + 1, result/numbers[index], add, minus, multi, divide - 1);
		}
	}

	private static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			numbers[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		opNum = new int[4];
		for (int i = 0; i < 4; i++)
			opNum[i] = Integer.parseInt(st.nextToken());
	}

}

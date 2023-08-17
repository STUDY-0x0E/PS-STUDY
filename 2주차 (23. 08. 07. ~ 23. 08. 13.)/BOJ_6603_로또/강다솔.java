package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_6603 {

	private static String[] S;
	private static int k, result[];
	private static final int choose = 6;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			S = br.readLine().split(" ");
			if (S[0].equals("0"))
				break;
			k = Integer.parseInt(S[0]);
			result = new int[choose];
			combination(1, 0);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void combination(int start, int index) {
		if (index == choose) {
			printArr();
			return;
		}

		for (int i = start; i < k + 1; i++) {
			result[index] = Integer.parseInt(S[i]);
			combination(i + 1, index + 1);
		}
	}

	private static void printArr() {
		for (int i = 0; i < choose; i++) {
			sb.append(result[i]).append(" ");
		}
		sb.append("\n");
	}

}

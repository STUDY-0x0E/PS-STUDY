import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder sb;
	static int N, M;
	static int[] selectedNumbers, inputs;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		N = 6;
		selectedNumbers = new int[6];

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		while (n != 0) {
			M = n;
			inputs = new int[M];
			for (int i = 0; i < inputs.length; i++) {
				inputs[i] = Integer.parseInt(st.nextToken());
			}

			comb(0, 0);
			sb.append('\n');

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
		}

		System.out.println(sb.toString());

	}

	private static void comb(int cnt, int start) {
		if (cnt == N) {
			for (int i : selectedNumbers) {
				sb.append(i).append(' ');
			}
			sb.append('\n');
		} else if(N - cnt <= M - start + 1) {
			for (int i = start; i < M; i++) {
				selectedNumbers[cnt] = inputs[i];
				comb(cnt + 1, i + 1);
			}
		}
	}
}
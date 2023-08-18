package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_13305 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] distance = new long[N - 1], price = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			distance[i] = Long.parseLong(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			price[i] = Long.parseLong(st.nextToken());
		}

		int start = 0, end = 0;
		long answer = 0;
		while (start < N - 1) {
			int dist = 0;
			for (end = start + 1; end < N; end++) {
				dist += distance[end - 1];
				if (price[start] > price[end]) {
					break;
				}
			}
			answer += (price[start] * dist);
			start = end;
		}
		System.out.println(answer);
	}

}

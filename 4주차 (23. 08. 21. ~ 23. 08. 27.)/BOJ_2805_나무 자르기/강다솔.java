package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_2805 {
	private static int N;
	private static long M, tree[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new long[N];
		st = new StringTokenizer(br.readLine());
		long end = 0;
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, tree[i]);
		}

		long answer = 0;
		long start = 0;
		while (start <= end) {
			long mid = (start + end) / 2;
			long t = cutTree(mid);
			if (t == M) {
				answer=mid;
				break;
			}
			if (t > M) {
				// 필요한 나무보다 많이 자름
				answer=mid;
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		System.out.println(answer);
	}

	private static long cutTree(long height) {
		long result = 0;
		for (int i = 0; i < N; i++) {
			if (tree[i]-height>0)
				result += (tree[i] - height);
		}
		return result;
	}

}

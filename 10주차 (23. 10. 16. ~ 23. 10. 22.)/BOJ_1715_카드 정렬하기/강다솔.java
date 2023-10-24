package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_1715 {

	private static int N;
	private static PriorityQueue<Integer> pQueue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		int answer = 0;
		while (pQueue.size() > 1) {
			int temp = pQueue.poll() + pQueue.poll();
			pQueue.add(temp);
			answer += temp;
		}
		System.out.println(answer);
	}

	private static void init(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		pQueue = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pQueue.add(Integer.parseInt(br.readLine()));
		}
	}

}

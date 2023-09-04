package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_1939 {

	private static int N, M, start, end;
	private static List<Bridge>[] move;

	static class Bridge implements Comparable<Bridge> {
		int island;
		long weight;

		Bridge(int island, long weight) {
			this.island = island;
			this.weight = weight;
		}

		public int compareTo(Bridge o) {
			return (int) (o.weight - this.weight);
		}

		@Override
		public String toString() {
			return "Bridge [island=" + island + ", weight=" + weight + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		System.out.println(getMaxWeight());
	}

	private static long getMaxWeight() {
		PriorityQueue<Bridge> pQueue = new PriorityQueue<>();
		pQueue.offer(new Bridge(start, 0L));
		long[] maxWeight = new long[N + 1];
		while (!pQueue.isEmpty()) {
			Bridge cur = pQueue.poll();
			if (cur.weight > maxWeight[cur.island]) {
				maxWeight[cur.island] = cur.weight;
				if (cur.island == end) {
					return maxWeight[cur.island];
				}
			}

			for (Bridge b : move[cur.island]) {
				long limit = Math.min(maxWeight[cur.island], b.weight);
				if (limit == 0) {
					pQueue.offer(b);
					continue;
				}
				if (maxWeight[b.island] < limit) {
					pQueue.offer(new Bridge(b.island, limit));
				}
			}
		}
		return -1;
	}

	private static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		move = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++)
			move[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			long C = Long.parseLong(st.nextToken());
			move[A].add(new Bridge(B, C));
			move[B].add(new Bridge(A, C));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
	}

}
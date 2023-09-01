package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_1916 {

	private static int N, M, start, end;
	private static List<Bus>[] graph;

	static class Bus implements Comparable<Bus> {
		int arrive, cost;

		public Bus(int arrive, int cost) {
			this.arrive = arrive;
			this.cost = cost;
		}

		public int compareTo(Bus o) {
			return this.cost - o.cost;
		}

		@Override
		public String toString() {
			return "Bus [arrive=" + arrive + ", cost=" + cost + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input(br);
		System.out.println(dijkstra());
	}

	private static int dijkstra() {
		PriorityQueue<Bus> pQueue = new PriorityQueue<>();
		pQueue.offer(new Bus(start, 0)); // 출발점
		int[] visited = new int[N + 1];
		Arrays.fill(visited, Integer.MAX_VALUE);
		while (!pQueue.isEmpty()) {
			Bus cur = pQueue.poll();
			if (visited[cur.arrive] > cur.cost) {
				visited[cur.arrive] = cur.cost;
				if (cur.arrive == end)
					return visited[end];
			}
			for (Bus bus : graph[cur.arrive]) {
				if (visited[bus.arrive] > cur.cost + bus.cost) {
					pQueue.offer(new Bus(bus.arrive, cur.cost + bus.cost));
				}
			}
		}
		return -1;
	}

	private static void input(BufferedReader br) throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++)
			graph[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new Bus(to, cost));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
	}

}

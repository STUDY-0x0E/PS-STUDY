package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_9019 {

	static class Register {
		int num;
		StringBuilder sb;

		public Register(int num, StringBuilder sb) {
			super();
			this.num = num;
			this.sb = sb;
		}

		public int getNum() {
			return num;
		}

		public StringBuilder getSb() {
			return sb;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			String answer = getSolution(a, b).toString();
			System.out.println(answer);
		}
	}

	private static StringBuilder getSolution(int a, int b) {
		boolean[] visited = new boolean[10000];
		visited[a] = true;
		Queue<Register> queue = new ArrayDeque<>();
		queue.offer(new Register(a, new StringBuilder()));
		while (!queue.isEmpty()) {
			Register cur = queue.poll();
			if (cur.getNum() == b)
				return cur.getSb();
			for (int i = 1; i <= 4; i++) {
				int nextNum = calculate(i, cur.getNum());
				StringBuilder temp = new StringBuilder(cur.getSb());
				if (!visited[nextNum]) {
					visited[nextNum] = true;
					temp.append(getNextCommand(i));
					queue.offer(new Register(nextNum, temp));
				}
			}
		}
		return new StringBuilder();
	}

	private static int calculate(int command, int n) {
		switch (command) {
			case 1: // D : n을 2배로
				return (n *= 2) % 10000;
			case 2: // S : n-1
				return (--n) < 0 ? 9999 : n;
			case 3: // L : 왼쪽 회전
				return n / 1000 + n % 1000 * 10;
			case 4: // R : 오른쪽 회전
				return 1000 * (n % 10) + n / 10;
		}
		return -1;
	}

	private static String getNextCommand(int command) {
		switch (command) {
		case 1:
			return "D";
		case 2:
			return "S";
		case 3:
			return "L";
		case 4:
			return "R";
		}
		return "";
	}

}

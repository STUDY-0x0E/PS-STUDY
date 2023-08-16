package baekjoon;

import java.util.*;
import java.util.Deque;

public class baekjoon_1158 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int K = scanner.nextInt();

		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			deque.add(i);
		}
		
		List<Integer> answer = new ArrayList<>();
		while (!deque.isEmpty()) {
			for (int i=0;i<K-1;i++) {
				deque.addLast(deque.pollFirst());
			}
			answer.add(deque.pollFirst());
		}
		
		System.out.print("<");
		for (int i=0;i<N;i++) {
			System.out.print(answer.get(i));
			if (i!=N-1) System.out.print(", ");
		}
		System.out.println(">");
	}

}

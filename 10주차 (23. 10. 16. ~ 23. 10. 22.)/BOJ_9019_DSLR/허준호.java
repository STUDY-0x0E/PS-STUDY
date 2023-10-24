import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		for (int t = 0; t < T; t++) {
			int A = scanner.nextInt();
			int B = scanner.nextInt();
			bfs(A, B);
		}
	}

	static void bfs(int A, int B) {
		String[] commands = new String[10000];
		boolean[] visited = new boolean[10000];
		Queue<Integer> queue = new LinkedList<>();

		commands[A] = "";
		visited[A] = true;
		queue.add(A);

		while (!queue.isEmpty()) {
			int n = queue.poll();

			if (n == B) {
				System.out.println(commands[n]);
				return;
			}

			int d = (2 * n) % 10000; // D
			if (!visited[d]) {
				visited[d] = true;
				queue.add(d);
				commands[d] = commands[n] + "D";
			}

			int s = (n - 1) < 0 ? 9999 : n - 1; // S
			if (!visited[s]) {
				visited[s] = true;
				queue.add(s);
				commands[s] = commands[n] + "S";
			}

			
			int l = (n % 1000) * 10 + n / 1000; // L 연산
			if (!visited[l]) {
				visited[l] = true;
				queue.add(l);
				commands[l] = commands[n] + "L";
			}

			int r = (n % 10) * 1000 + n / 10; // R
			if (!visited[r]) {
				visited[r] = true;
				queue.add(r);
				commands[r] = commands[n] + "R";
			}
		}
	}
}

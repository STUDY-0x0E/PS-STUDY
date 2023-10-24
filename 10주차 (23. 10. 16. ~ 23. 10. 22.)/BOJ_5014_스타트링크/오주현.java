import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int F, U, D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		boolean visited[] = new boolean[F+1];
		
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {S,0});
		visited[S] = true;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			if (cur[0] == G) {
				System.out.println(cur[1]);
				return;
			}
			
			int next = up(cur[0]);
			if (!visited[next]) {
				queue.add(new int[] {next,cur[1]+1});
				visited[next] = true;
			}
		
			next = down(cur[0]);
			if (!visited[next]) {
				queue.add(new int[] {next,cur[1]+1});
				visited[next] = true;
			}
		}
		
		System.out.println("use the stairs");
	}

	private static int up(int floor) {
		return (floor + U > F)? floor : (floor + U);
	}

	private static int down(int floor) {
		return (floor - D < 1)? floor : (floor - D);
	}

}
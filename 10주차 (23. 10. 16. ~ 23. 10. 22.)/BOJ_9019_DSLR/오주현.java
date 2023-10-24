import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final String FUNCNAME[] = {"D", "S", "L", "R"};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			Queue<Integer> queue = new ArrayDeque<Integer>();
			boolean visited[] = new boolean[10001];
			String command[] = new String[10001];
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			queue.add(start);
			visited[start] = true;
			
			Arrays.fill(command, "");
			
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				
				if (cur == end) {
					sb.append(command[cur] + "\n");
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int next = func(cur, i);
					if (!visited[next]) {
						queue.add(next);
						visited[next] = true;
						command[next] = command[cur] + FUNCNAME[i];
					}					
				}				
			}
		}
		
		System.out.println(sb);
	}

	private static int func(int cur, int i) {	
		switch (i) {
		case 0:
			return (2*cur) % 10000;
		case 1:
			return cur == 0? 9999 : cur - 1;
		case 2:
			return (cur % 1000) * 10 + cur / 1000;
		case 3:
			return (cur % 10) * 1000 + cur / 10;
		}
		return 0;
	}

}
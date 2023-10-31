import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, budget[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int max = 0;
		budget = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			budget[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, budget[i]);
		}

		M = Integer.parseInt(br.readLine());

		int answer = binSearch(1,max+1);
		System.out.println(answer);
	}

	private static int binSearch(int start, int end) {
		while (start+1 < end) {
			int mid = (start + end) / 2;

			if (check(mid)) start = mid;
			else end = mid;
		}
		return start;
	}

	private static boolean check(int mid) {
		long sum = 0;
		for (int i = 0; i < N; i++) {
			sum += (budget[i] > mid) ? mid : budget[i];
		}
		
		if (sum > M) return false;
		else return true;
	}

}
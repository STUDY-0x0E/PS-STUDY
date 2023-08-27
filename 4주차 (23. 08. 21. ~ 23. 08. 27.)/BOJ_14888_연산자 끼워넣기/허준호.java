import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] num, op;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine()); // 수의 개수 N
        num = new int[n]; // A1, A2, ..., AN
        op = new int[4]; // +, -, ×, ÷의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(num[0], 1);//가능한 모든 수식을 만들어 최대,최솟값 계산

        System.out.println(max + "\n" + min);
    }

	static void dfs(int result, int idx) {
		if (idx == n) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] > 0) {
				op[i]--;
				int nextResult = calc(result, num[idx], i);
				dfs(nextResult, idx + 1);
				op[i]++;
			}
		}
	}

	static int calc(int a, int b, int operator) {
		switch (operator) {
		case 0: return a + b;
		case 1: return a - b;
		case 2: return a * b;
		case 3: return a / b;
		default: return 0;
		}
	}
}
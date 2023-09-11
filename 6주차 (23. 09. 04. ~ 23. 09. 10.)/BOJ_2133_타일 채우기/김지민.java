import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		if (n % 2 == 1) {
			System.out.println(0);
			return;
		}
		
		int[] mem = new int[n / 2 + 1];
		mem[0] = 1;
		for (int i = 1; i <= n / 2; i++) {
			mem[i] = mem[i - 1] * 3;
			for (int j = 0; j < i - 1; j++) {
				mem[i] += mem[j] * 2;
			}
		}
		
		System.out.println(mem[n / 2]);

	}

}
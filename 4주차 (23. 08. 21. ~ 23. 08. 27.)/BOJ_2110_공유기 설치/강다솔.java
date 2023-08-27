package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_2110 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] home = new int[N];
		for (int i = 0; i < N; i++)
			home[i] = Integer.parseInt(br.readLine());
		Arrays.sort(home);
		int start =1, end = home[N-1];
	}

}

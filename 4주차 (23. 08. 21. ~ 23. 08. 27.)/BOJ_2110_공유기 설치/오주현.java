package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN2110_RouterInstall {

	static int N, C, interval[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int house[] = new int[N];
		for (int i = 0; i < N; i++) house[i]= Integer.parseInt(br.readLine());
		
		Arrays.sort(house);
		
		interval = new int[N-1];
		for (int i = 0; i < N-1; i++) interval[i]= house[i+1]-house[i];
		
		System.out.println(Search(1,house[N-1]-house[0]+1));
	}

	private static int Search(int start, int end) {

		while (start+1 < end) {
			int mid = (start + end)/2;
			if (install(mid) < C) end= mid;
			else start= mid;
		}
		
		return start;
	}

	private static int install(int mid) {
		int router = 1, a = 0;
		for (int i = 0; i < N-1; i++) {
			if ((a+= interval[i]) >= mid) {
				a= 0;
				router++;
			}
		}
		
		return router;
	}

}

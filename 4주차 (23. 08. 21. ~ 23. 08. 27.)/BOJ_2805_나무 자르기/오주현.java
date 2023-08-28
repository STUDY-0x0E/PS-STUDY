package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN2805_LumberCut {

	static int N, tree[];
	static long M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		
		tree = new int[N];
		int max = -1;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			tree[i]= Integer.parseInt(st.nextToken());
			max= tree[i] > max ? tree[i] : max;
		}
		
		System.out.println(binarySearch(0,max+1));

	}
	
	public static int binarySearch(int start, int end) {
		
		while (start+1 < end) {
			int mid = (start + end)/2;
			
			if (!available(mid)) end = mid;
			else start = mid;
		}
		
		return start;
	}
	
	public static boolean available(int height) {
		long total = 0;
		
		for (int i : tree) {
			int cut = (i-height) > 0 ? i-height : 0;
			total+= cut;
		}
		
		return total >= M;
	}

}

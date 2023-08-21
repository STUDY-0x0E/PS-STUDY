import java.io.*;
import java.util.*;

public class BOJ13305 {

	static int N;
	static long[] road;
	static long[] village;
	
	static long sumPrice;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st ;
		
		N = Integer.parseInt(br.readLine());
		
		road = new long[N+1];
		village = new long[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N; i++) { road[i] = Integer.parseInt(st.nextToken());}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) { village[i] = Integer.parseInt(st.nextToken());}
		
		long minPrice = village[0];
		for(int i = 1; i < N; i++) {
			sumPrice += minPrice * road[i];
			minPrice = Math.min(minPrice, village[i]);
		}
		System.out.println(sumPrice);
	}
}
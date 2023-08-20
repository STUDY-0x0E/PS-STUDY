import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// input

		int n = Integer.parseInt(br.readLine());//도시의 개수
		long[] dis = new long[n-1];//주유소의 리터당 가격
		long[] gas = new long[n];//인접한 두 도시를 연결하는 도로의 길이
		
		StringTokenizer st = new StringTokenizer(br.readLine());		
		for (int i = 0; i < n-1; i++) 
			dis[i]=Long.parseLong(st.nextToken());
		st = new StringTokenizer(br.readLine());		
		for (int i = 0; i < n; i++) 
			gas[i]=Long.parseLong(st.nextToken());
		
		long ans=0;
		long min=Integer.MAX_VALUE;
		for (int i = 0; i < n-1; i++) {
			if(min>gas[i]) {
				min=gas[i];
			}
			ans+=min*dis[i];
		}
		
		System.out.println(ans);
	}
}
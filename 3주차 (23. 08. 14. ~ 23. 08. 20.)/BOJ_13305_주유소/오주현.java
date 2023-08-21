package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer dist = new StringTokenizer(br.readLine());
		StringTokenizer cost = new StringTokenizer(br.readLine());
		
		long bcost = Integer.MAX_VALUE;
		long answer = 0;
		for (int i = 0; i < N-1; i++) {
			long cdist = Long.parseLong(dist.nextToken());
			long ccost = Long.parseLong(cost.nextToken());
			
			if(bcost>ccost) {
				bcost = ccost;
				answer += bcost * cdist;
			}else {
				answer += bcost * cdist;
			}
		}
		System.out.println(answer);
	}

}
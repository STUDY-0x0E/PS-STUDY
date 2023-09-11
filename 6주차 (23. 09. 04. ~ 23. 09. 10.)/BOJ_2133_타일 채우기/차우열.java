package BOJ0913;

import java.util.Scanner;

public class tiling {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		if (n%2==1) System.out.println(0);
		else {
			int[] dp = new int[n+1];
			dp[0] = 1;
			dp[1] = 2;
			
			for (int i=2; i<=n; i++) {
				if (i%2==0) {
					dp[i] = dp[i-2]+dp[i-1];
				} else {
					dp[i] = dp[i-2]+dp[i-1]*2;
				}
			}
			System.out.println(dp[n]);
		}
	}

}

package week11;

//dp문제
//26 기준으로 문제를 잘 고려해서 푼다.
//0으로 시작하는 두자리수는 없다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2011 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		
		int[] dp = new int[S.length()+1];
		int mod = 1_000_000;
		if (S.charAt(0)=='0') {
			System.out.println(0);
			return;
		}
		dp[0] = 1;
		dp[1] = 1;
		
		for (int i=1; i<S.length(); i++) {
			if (S.charAt(i)=='0') {
				if (S.charAt(i-1)=='0' || S.charAt(i-1)>'2') {
					System.out.println(0);
					return;
				} else {
					dp[i+1] = dp[i-1];
				}
			} else if (S.charAt(i-1)=='0'||S.charAt(i-1)>'2'||(S.charAt(i-1)=='2' && S.charAt(i)>'6')) {
				dp[i+1] = dp[i];
			} else {
				dp[i+1] = (dp[i-1] + dp[i])%mod;
			}
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[S.length()]);
	}
}

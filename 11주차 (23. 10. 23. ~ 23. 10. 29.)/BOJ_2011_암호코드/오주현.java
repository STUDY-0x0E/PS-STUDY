import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	static final int DIV = 1000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int len = s.length();
		
		ArrayList<Integer> code = new ArrayList<>();
		for (int i = 0; i < len; i++) code.add(Integer.parseInt(s.substring(i,i+1)));
		
		int dp[] = new int[len+1];
		dp[0] = 1;
		if (0 < code.get(0)) dp[1] = 1;
		else {
			System.out.println(0);
			return;
		}

		for (int i = 1 ; i < len; i++) {
			if (0 < code.get(i)) {
				dp[i+1] += dp[i];
				dp[i+1] %= DIV;
			}
			else {
				if (code.get(i-1) > 2) {
					System.out.println(0);
					return;
				}
			}
			
			int tmp = code.get(i-1) * 10 + code.get(i);
			if (10 <= tmp && tmp <= 26) {
				dp[i+1] += dp[i-1];
				dp[i+1] %= DIV;
			}
		}
		
		System.out.println(dp[len]);
	}
}
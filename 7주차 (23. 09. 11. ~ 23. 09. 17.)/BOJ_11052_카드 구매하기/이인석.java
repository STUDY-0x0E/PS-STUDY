import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	
    public static void main(String[] args) throws Exception {
        
    	br = new BufferedReader(new InputStreamReader(System.in));
    	bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int n = Integer.parseInt(br.readLine());
    	int[] cards = new int[n + 1];
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for (int i = 1; i <= n; i++) {
    		cards[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	int[] dp = new int[n + 1];
    	
    	// 카드 묶음에 대해서 반복
    	for (int i = 1; i <= n; i++) {
    		
    		// 금액에 대해 반복
    		for (int j = 0; j <= n; j++) {
    			if (j - i < 0) continue;
    			dp[j] = Math.max(dp[j], dp[j - i] + cards[i]);
    		}
    	}
    	
    	bw.write(dp[n] + "");
    	bw.flush();
        bw.close();
        br.close();
    }
    
}
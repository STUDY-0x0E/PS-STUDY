import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	
	static int n;
	static int[] unit;
	static int[] dp;
	
    public static void main(String[] args) throws Exception {
        
    	br = new BufferedReader(new InputStreamReader(System.in));
    	bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	int k = Integer.parseInt(st.nextToken());
    	
    	unit = new int[n];
    	for (int i = 0; i < n; i++) {
    		unit[i] = Integer.parseInt(br.readLine()); 
    	}
    	
    	dp = new int[k + 1];
    	dp[0] = 1;
    	
    	// f(2) + f(1) 과 f(1) + f(2)는 같은 조합
    	// 중복을 제거하기 위해 순서를 설정 => 사용하는 동전 단위를 차차 늘려감
    	for (int i = 0; i < n; i++) {
    		
    		for (int j = 1; j <= k; j++) {
    			if (j >= unit[i]) dp[j] += dp[j - unit[i]];
    		}
    	}
    	
    	bw.write(dp[k]+ "" );
    	bw.flush();
        bw.close();
        br.close();
    }
}
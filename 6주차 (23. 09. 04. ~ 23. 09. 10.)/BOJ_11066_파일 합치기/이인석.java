import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static BufferedWriter bw;
	
	static int n;
	static int[][] dp;
	static int[] prefixSum;
	
    public static void main(String[] args) throws Exception {
        
    	br = new BufferedReader(new InputStreamReader(System.in));
    	bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int T = Integer.parseInt(br.readLine());
    	for (int tc = 0; tc < T; tc++) {
    		
    		n = Integer.parseInt(br.readLine());
    		dp = new int[n][n];
    		prefixSum = new int[n + 1];
    		
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		for (int i = 0; i < n; i++) {
    			dp[i][i] = Integer.parseInt(st.nextToken());
    			prefixSum[i + 1] = prefixSum[i] + dp[i][i]; 
    		}
    		
    		bw.write(getCost(0, n - 1) + "\n");
    	}
   
    	bw.flush();
        bw.close();
        br.close();
    }
    
    public static int getCost(int start, int end) {
    	// 낱개 파일 => 생성 비용 없음
    	if (start == end) {
    		return 0;
    	}
    	
    	if (dp[start][end] != 0) {
    		return dp[start][end];
    	}
    	
    	// 현재 파일을 만드는데 사용된 비용 = 현재 파일의 크기
    	int cost = prefixSum[end + 1] - prefixSum[start];
    	for (int i = start; i < end; i++) {
    		
    		// 이전 파일의 비용 + 현재 파일 비용 => 지금까지의 비용
    		int result = getCost(start, i) + getCost(i + 1, end) + cost;
    		// 최저 비용 저장
    		dp[start][end] = dp[start][end] == 0 ? result : Math.min(dp[start][end], result);
    	}
    	
    	return dp[start][end];
    }
}
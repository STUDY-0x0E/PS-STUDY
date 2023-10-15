import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	
	public static String[][] map;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        int c = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);
        
        // 0 : 도시에서 홍보하는데 드는 비용 / 1 : 홍보할 경우 늘어나는 고객의 수
        int[][] city = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
        	input = br.readLine().split(" ");
        	
        	city[i][0] = Integer.parseInt(input[0]);
        	city[i][1] = Integer.parseInt(input[1]);
        }
        Arrays.sort(city, (o1, o2) -> o1[0] - o2[0]);
        
        int[] dp = new int[c + 101];
        Arrays.fill(dp, 1000001);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
        	
        	for (int j = 1; j < dp.length; j++) {
        		if (j < city[i][1]) continue;
        		dp[j] = Math.min(dp[j], dp[j - city[i][1]] + city[i][0]);
        	}
        }
        
        int result = Integer.MAX_VALUE;
        for (int i = dp.length - 1; i >= c; i--) {
        	result = Math.min(result, dp[i]);
        }
        bw.write(result + "");
        br.close();
        bw.flush();
        bw.close();
    }
}
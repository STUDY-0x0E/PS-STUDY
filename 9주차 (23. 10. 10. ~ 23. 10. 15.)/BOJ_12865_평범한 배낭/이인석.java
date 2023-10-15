import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	
	public static String[][] map;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        
        int[][] items = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
        	input = br.readLine().split(" ");
        	
        	items[i][0] = Integer.parseInt(input[0]);
        	items[i][1] = Integer.parseInt(input[1]);
        }
        
        int[][] knapsack = new int[k + 1][n + 1];
        for (int i = 0; i <= k; i++) {
        	
        	for (int j = 1; j <= n; j++) {
        		
        		if (i < items[j][0]) {
        			knapsack[i][j] = knapsack[i][j - 1];
        			continue;
        		}
        		
        		knapsack[i][j] = Math.max(knapsack[i - items[j][0]][j - 1] + items[j][1]
        									, knapsack[i][j - 1]);
        		
        	}
        }
        
        bw.write(knapsack[k][n] + "");
        bw.flush();
        bw.close();
    }
}
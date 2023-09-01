import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class 이인석 {
	
	static BufferedReader br;
	static BufferedWriter bw;
	
    public static void main(String[] args) throws Exception {
        
    	br = new BufferedReader(new InputStreamReader(System.in));
    	bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
        	Arrays.fill(map[i], Integer.MAX_VALUE);
        	map[i][i] = 0; 
        }
        
        for (int i = 0; i < m; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken()) - 1;
        	int end = Integer.parseInt(st.nextToken()) - 1;
        	
        	map[start][end] = Math.min(map[start][end], Integer.parseInt(st.nextToken()));
        }
        
        long[] dist = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
        	
        	int minIdx = -1;
        	long minCost = Integer.MAX_VALUE;
        	for (int j = 0; j < n; j++) {
        		if (!visited[j] && minCost > dist[j]) {
        			minCost = dist[j];
        			minIdx = j;
        		}
        	}
        	if (minIdx == -1) break;
        	
        	visited[minIdx] = true;
        	if (minIdx == end) break;
        	
        	for (int j = 0; j < n; j++) {
        		if (!visited[j]) {
        			dist[j] = Math.min(dist[j], minCost + map[minIdx][j]);
        		}
        	}
        }
        
    	bw.write(dist[end] + "" );
        bw.flush();
        bw.close();
        br.close();
    }
}
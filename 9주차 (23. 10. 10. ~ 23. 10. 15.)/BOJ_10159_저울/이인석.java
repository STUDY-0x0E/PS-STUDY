import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        
        boolean[][] fw = new boolean[n][n];

        for (int i = 0; i < m; i++) {
        	String[] input = br.readLine().split(" ");
        	int a = Integer.parseInt(input[0]) - 1;
        	int b = Integer.parseInt(input[1]) - 1;
        	
        	fw[a][b] = true;
        }
        
        for (int k = 0; k < n; k++) {
        	for (int i = 0; i < n; i++) {
        		if (i == k) continue;
        		
        		for(int j = 0; j < n; j++) {
        			if (i == j || j == k) continue;
        			
        			fw[i][j] = fw[i][j] || (fw[i][k] && fw[k][j]);
        		}
        	}
        }
        
        for (int i = 0; i < n; i++) {
        	
        	for (int j = 0; j < n; j++) {
        		if (i == j || fw[i][j]) fw[j][i] = true;
        	}
        }
        
        for (int i = 0; i < n; i++) {
        	
        	int answer = 0;
        	
        	for (int j = 0; j < n; j++) {
        		if (i == j) continue;
        		if (!fw[i][j]) ++answer;
        	}
        	
        	bw.write(answer + "\n");
        }
        
        br.close();
        bw.flush();
        bw.close();
    }
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    
    static int[][] dir = {
    		{0, 0},
    		{-1, 0}, {-1, -1}, {0, -1}, {1, -1},
    		{1, 0}, {1, 1}, {0, 1}, {-1, 1}
    };
    
    static int[] diagonal = { 2, 4, 6, 8 };
    
    public static class Cloud {
    	int x;
    	int y;
    	
    	public Cloud(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
    
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        
        int[][] basket = new int[n][n];
        for (int i = 0; i < n; i++) {
        	
        	input = br.readLine().split(" ");
        	for (int j = 0; j < n; j++) {
        		basket[i][j] = Integer.parseInt(input[j]);
        	}
        }
        
        Queue<Cloud> queue = new LinkedList<Cloud>();
        queue.add(new Cloud(0, n - 1));
        queue.add(new Cloud(0, n - 2));
        queue.add(new Cloud(1, n - 1));
        queue.add(new Cloud(1, n - 2));
        
        int[][] movePattern = new int[m][2];
        for (int i = 0; i < m; i++) {
        	input = br.readLine().split(" ");
        	movePattern[i][0] = Integer.parseInt(input[0]);
        	movePattern[i][1] = Integer.parseInt(input[1]) % n;
        			
        }
        
        for (int i = 0; i < m; i++) {
        	
        	// 구름 이동 후 물 추가
        	int d = movePattern[i][0];
        	boolean[][] destroy = new boolean[n][n];
        	while (!queue.isEmpty()) {
        		Cloud curr = queue.poll();
        		
        		int nx = curr.x, ny = curr.y;
        		for (int j = 0; j < movePattern[i][1]; j++) {
        			nx += dir[d][0];
        			ny += dir[d][1];
        			
        			if (nx < 0) nx = n - 1;
        			else if (nx >= n) nx = 0;
        			if (ny < 0) ny = n - 1;
        			else if (ny >= n) ny = 0;
         		}
        		
        		basket[ny][nx] += 1;
        		destroy[ny][nx] = true;
        	}
        	
        	// 물복사
        	for (int j = 0; j < n; j++) {
        		
        		for (int k = 0; k < n; k++) {
        			if (!destroy[j][k]) continue;
        			
        			for (int t = 0; t < 4; t++) {
        				int nx = k + dir[diagonal[t]][0];
        				int ny = j + dir[diagonal[t]][1];
        				
        				if (nx < 0 || nx >= n || ny < 0 || ny >= n || basket[ny][nx] < 1) continue;
        				
        				basket[j][k] += 1;
        			}
        		}
        	}
        	
        	// 구름 생성
        	for (int j = 0; j < n; j++) {
        		
        		for (int k = 0; k < n; k++) {
        			if (destroy[j][k] || basket[j][k] < 2) continue;
        			
        			basket[j][k] -= 2;
        			queue.offer(new Cloud(k, j));
        		}
        	}
        	
        }
        
        int result = 0;
        for (int i = 0; i < n; i++) {
        	
        	for (int j = 0; j < n; j++) {
        		result += basket[i][j];
        	}
        }
        
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

}
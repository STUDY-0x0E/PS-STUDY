import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;


    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int F = Integer.parseInt(input[0]);
        int S = Integer.parseInt(input[1]);
        int G = Integer.parseInt(input[2]);
        int U = Integer.parseInt(input[3]);
        int D = Integer.parseInt(input[4]);
        
        int[] floor = new int[F + 1];
        int result = 0;
        Arrays.fill(floor, Integer.MAX_VALUE);
        floor[S] = 0;
        
        Queue<Integer> queue = new LinkedList<Integer>();
        if (S != G) {
        	queue.add(S);
        	result = -1;
        }
        while (!queue.isEmpty()) {
        	
        	int curr = queue.poll();

        	if (curr + U <= F && floor[curr + U] == Integer.MAX_VALUE) {
        		if (curr + U == G) {
        			result = floor[curr] + 1;
        			break;
        		}
        		floor[curr + U] = floor[curr] + 1;
        		queue.offer(curr + U);
        	}
        	
        	if (curr - D > 0 && floor[curr - D] == Integer.MAX_VALUE) {
        		if (curr - D == G) {
        			result = floor[curr] + 1;
        			break;
        		}
        		floor[curr - D] = floor[curr] + 1;
        		queue.offer(curr - D);
        	}
        }
        
        
        bw.write(result < 0 ? "use the stairs" : result + "");
        bw.flush();
        bw.close();
        br.close();
    }

}
package week10;

//색다른 BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			Queue<int[]> q = new ArrayDeque<int[]>();
			int[] farr = {Integer.parseInt(st.nextToken()), 0};
			int result = Integer.parseInt(st.nextToken());
			q.offer(farr);
			boolean[] visited = new	boolean[10000];
			visited[farr[0]] = true;
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				
				if (cur[0]==result) {
					for (int i=2; i<cur.length; i++) {
						if (cur[i]==0) sb.append('D');
						else if (cur[i]==1) sb.append('S');
						else if (cur[i]==2) sb.append('L');
						else if (cur[i]==3) sb.append('R');
					}
					sb.append('\n');
					break;
				}

				int val = cur[0];
				int[] tmp = new int[4];
				
				tmp[0] = val*2%10000;
				tmp[1] = (val==0)?9999:val-1;
				tmp[2] = val/1000 + (val*10)%10000;
				tmp[3] = val/10 + (val%10)*1000;
				
				for (int i=0; i<4; i++) {
					if (visited[tmp[i]]) continue;
					visited[tmp[i]] = true;
					int[] copy = new int[cur.length+1];
					for (int j=1; j<cur.length; j++) copy[j] = cur[j];
					copy[0] = tmp[i];
					copy[1]++;
					copy[cur.length] = i;
					q.offer(copy);
				}
			}
			
			
		}
		System.out.println(sb);
	}
}
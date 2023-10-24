package week10;

//BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5014 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] inputs = new int[5];
		for (int i=0; i<5; i++) inputs[i] = Integer.parseInt(st.nextToken());
		
		inputs[1]--;
		inputs[2]--;
		
		boolean[] visited = new boolean[inputs[0]];
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(inputs[1]);
		visited[inputs[1]] = true;
		
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-->0) {
				int cur = q.poll();
				
				if (cur==inputs[2]) {
					System.out.println(cnt);
					return;
				}
				if (cur+inputs[3]<inputs[0] && !visited[cur+inputs[3]]) {
					visited[cur+inputs[3]] = true;
					q.offer(cur+inputs[3]);
				}
				if (cur-inputs[4]>=0 && !visited[cur-inputs[4]]) {
					visited[cur-inputs[4]] = true;
					q.offer(cur-inputs[4]);
				}
			}
			cnt++;
		}
		System.out.println("use the stairs");
	}
}
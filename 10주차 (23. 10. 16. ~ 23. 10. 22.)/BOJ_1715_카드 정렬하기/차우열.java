package week10;

//우선순위 큐

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1715 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i=0; i<N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		int sum = 0;
		
		while(pq.size()>1) {
			int val = pq.poll() + pq.poll();
			sum += val;
			pq.offer(val);
		}
		
		System.out.println(sum);
	}
}
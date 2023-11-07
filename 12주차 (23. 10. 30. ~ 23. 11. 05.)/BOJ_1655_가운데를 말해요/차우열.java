package week12;

//중간 index 힙
//최대힙과 최소힙을 만들고, 양쪽힙의 크기를 유지하면서, 최대힙의 최댓값이 최소힙의 최솟값보다 작게 한다.
//최대힙의 최댓값이 항상 문제에서 요구하는 값이다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1655 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> lpq = new PriorityQueue<Integer>((x,y)->y-x);
		PriorityQueue<Integer> rpq = new PriorityQueue<Integer>();
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			int val = Integer.parseInt(br.readLine());
			if (i%2==0) {
				if (rpq.size()>0 && rpq.peek()<val) {
					lpq.offer(rpq.poll());
					rpq.offer(val);
				} else {
					lpq.offer(val);
				}
			} else {
				if (lpq.peek()>val) {
					rpq.offer(lpq.poll());
					lpq.offer(val);
				} else {
					rpq.offer(val);
				}
			}
			sb.append(lpq.peek()).append('\n');
		}
		System.out.println(sb);
	}
}
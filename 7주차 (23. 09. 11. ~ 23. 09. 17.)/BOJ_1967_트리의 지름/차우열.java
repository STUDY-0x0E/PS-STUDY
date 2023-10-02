package week7;

/*
1.summary:
숫자 N을 입력받고 다음N-1줄에 트리의 간선정보를 입력받는다.
트리에서 가장 먼 두 지점인 트리의 지름을 출력한다.

2.strategy:
BFS를 통해 어떤지점에서 가장 먼 위치를 구하고, 그 지점에서 가장 먼 지점을 구한다.
두 지점사이의 거리가 트리의 지름이다.

3.note:
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1947 {
	public static int[] getM(ArrayList<int[]>[] adjl, int idx) {
		int[] max = new int[2];
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		boolean[] visited = new boolean[adjl.length];
		q.offer(new int[] {idx, 0});
		visited[idx] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			if (cur[1]>max[1]) {
				max[1] = cur[1];
				max[0] = cur[0];
			}
			
			for (int[] elem:adjl[cur[0]]) {
				if (visited[elem[0]]) continue;
				visited[elem[0]] = true;
				q.offer(new int[] {elem[0], cur[1]+elem[1]});
			}
		}
		return max;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]>[] adjl = new ArrayList[N+1];

		for (int i=0; i<adjl.length; i++) {
			adjl[i] = new ArrayList<int[]>();
		}
		
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjl[a].add(new int[] {b,w});
			adjl[b].add(new int[] {a,w});
		}
		
		System.out.println(getM(adjl, getM(adjl, 1)[0])[1]);
		
	}
}

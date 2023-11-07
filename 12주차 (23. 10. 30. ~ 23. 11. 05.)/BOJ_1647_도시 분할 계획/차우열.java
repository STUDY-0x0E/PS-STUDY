package week12;

//최소 신장트리를 만드는 과정에서 마지막 하나의 간선을 덜 추가하면 되는 문제

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1647 {
	
	static int find(int[] p, int i) {
		if (p[i]==i) return i;
		return p[i] = find(p, p[i]);
	}
	
	static boolean union(int[] p, int i, int j) {
		int I = find(p, i);
		int J = find(p, j);
		if (I==J) return false;
		p[J] = I;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] edges = new int[M][3];
		int[] p = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			p[i] = i;
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<3; j++) {
				edges[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Arrays.sort(edges, (x,y)->x[2]-y[2]);
		
		int sum = 0;
		int cnt = 0;
		if (N==2) {
			System.out.println(0);
			return;
		}
		for (int i=0; i<edges.length; i++) {
			if(!union(p, edges[i][0], edges[i][1])) continue;
			sum += edges[i][2];
			if(++cnt==N-2) break;
		}
		System.out.println(sum);
		
	}
}
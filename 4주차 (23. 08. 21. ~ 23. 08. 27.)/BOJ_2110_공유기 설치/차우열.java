package algo0828;

/*
1.summary:
숫자 N과 C를 입력받고, N개의 위치를 입력받는다.
N개의 위치중 C개를 골랐을 때, 인접한 위치의 거리의 최솟값이 최대가 될 때의 인접한 위치의 거리의 최솟값을 출력한다.

2.strategy:
인접 위치의 거리의 최솟값을 기준으로 이진 탐색을 수행한다.

3.note:
이진 탐색이 아닌 방법으로 찾는다면 시간 초과가 발생할 수 있다.
적절한 범위 설정과 값 출력을 수행해야 한다.
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] X = new int[N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			X[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(X);
		int h = (X[N-1]-X[0])/(C-1)+1;
		int l = 1;
		while (l<h) {
			int pre = 0;
			int mid = (h+l)/2;
			
			boolean flag = true;
			
			for (int i=0; i<C-1; i++) {
			int idx = Arrays.binarySearch(X, X[pre]+mid);
			
			if (idx<0) {
				idx = -idx-1;
				
				if (idx>N-1) {
					flag = false;
					break;
				}
			}
			pre = idx;
		}
			
			if (!flag) {
				h = mid;
			} else {
				l = mid+1;
			}
		}
		System.out.println(l-1);
		

	}
}
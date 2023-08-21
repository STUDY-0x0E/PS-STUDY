package algo0821;

/*
1.summary:
도시 수N개를 입력받는다. 다음 줄에는 도시간 거리 N-1개를 입력받는다. 다음 줄에는 도시에서 기름 비용 N개를 입력받는다. 
차에 넣을 수 있는 기름에 제한이 없다고 할 때, 왼쪽에서 오른쪽 도시로 이동하기 위한 최소 비용을 출력한다.

2.strategy:
각 도시에서 더 싼 비용을 가진 도시를 만날때 까지 필요한 기름만 충전하는 그리디 알고리즘이다.

3.note:
맨 오른쪽 도시의 비용은 사용되지 않는다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13305 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] distance = new int[N-1];
		int[] price = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N-1; i++) {
			distance[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		
		long sum = 0;
		for (int i=0; i<N-1; i++) {
			sum += (long)price[i]*distance[i];
			int k = i;
			for (int j=i+1; j<N-1; j++) {
				if (price[k]<price[j]) {
					sum += (long)price[k]*distance[j];
					i++;
				} else {
					break;
				}
			}
		}
		
		System.out.println(sum);
	}

}
package week8;

/*
1.summary:
테스트케이스를 입력받고 각 테스트케이스에서 문제를 해결한다.
학생들의 수 N을 입력받고 다음줄에 N개의 정보를 입력받는다.
각 학생이 선택한 학생들이 순환구조라면 그 학생들끼리 한 팀이 될 수 있다.
팀이 되지 못하는 학생의 수를 출력한다.

2.strategy:
각 학생에서 선택한 학생들을 참조하여 순환구조인지 확인한다.
방문배열로 각 학생을 언제 방문했는지 기록한다.
	
3.note:
시간초과가 나지않게 주의한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for (int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] arr = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int cnt = 0;
			int[][] visited = new int[N+1][2];
			for (int i=1; i<=N; i++) {
				if (visited[i][0]!=0) continue;
				int tar = i;
				visited[tar][0] = i;
				visited[tar][1] = 1;
				while (visited[arr[tar]][0]==0) {
					visited[arr[tar]][0] = i;
					visited[arr[tar]][1] = visited[tar][1]+1;
					tar = arr[tar];
				}
				if (visited[arr[tar]][0] == i) {
					cnt += visited[arr[tar]][1]-1;
				} else {
					cnt += visited[tar][1];
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}
}

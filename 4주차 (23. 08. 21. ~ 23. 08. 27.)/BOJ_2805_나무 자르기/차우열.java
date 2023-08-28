package algo0828;

/*
1.summary:
나무개수 N과 집에 가져가야할 나무 길이 M을 입력받는다.
다음줄에는 N개의 나무 높이를 입력받는다.
절단기의 높이를 설정하면 절단기보다 각 나무에서 절단기보다 높은 만큼 나무를 얻을 수 있다.
M미터의 나무를 얻기 위한 절단기의 최대높이를 구하여라.

2.strategy:
각 나무의 높이를 저장한 배열을 정렬하고, 가장 긴 나무부터 원하는 높이의 나무를 얻을 수 있을 때까지 절단기의 높이를 낮춘다.

3.note:
이분탐색으로 풀면..?
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] Trees = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			Trees[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(Trees);
		
		int cur = M;
		int tcnt = 1;
		int height = Trees[Trees.length-tcnt];
		
		while(true) {
			if ((double)cur/tcnt > (height-Trees[Trees.length-1-tcnt])) {
				cur-= tcnt*(height-Trees[Trees.length-1-tcnt]);
				tcnt++;
				height = Trees[Trees.length-tcnt];
			} else {
				height -= cur/tcnt;
				if (cur%tcnt!=0) height--;
				break;
			}
		}
		
		System.out.println(height);
		
	}

}
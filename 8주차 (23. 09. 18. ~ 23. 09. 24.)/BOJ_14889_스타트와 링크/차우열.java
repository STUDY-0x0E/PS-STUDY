package week8;

/*
1.summary:
사람들의 수 N을 입력받는다. 다음 N줄에 걸쳐 각 사람이 다른사람과 같은 팀일때 추가되는 능력치를 입력받는다.
각 팀이 N/2명일때의 두 팀의 능력치합의 차이의 최솟값을 출력한다.

2.strategy:
조합을 통해 가능한 모든 경우의 수를 탐색하고 답을 출력한다.

3.note:
한명을 한 팀으로 고정한다면 필요한 경우의 수만 탐색할 수 있다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {
	static boolean np(int[] arr) {
		int i = arr.length-2;
		while (i>0 && arr[i]<=arr[i-1]) i--;
		if (i==0) return false;
		int j = arr.length-2;
		while (arr[j]<=arr[i-1]) j--;
		swap(arr, j, i-1);
		int k = arr.length-2;
		while (i<k) {
			swap(arr, i++, k--);
		}
		return true;
	}
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[][] S = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] arr = new int[N];
		
		for (int i=0; i<N/2; i++) {
			arr[arr.length-1-i] = 1;
		}
		int min = Integer.MAX_VALUE;
		
		do {
			int[] val = new int[2];
			for (int i=0; i<N-1; i++) {
				for (int j=i+1; j<N; j++) {
					if (arr[i]==arr[j])
						val[arr[i]] += S[i][j]+S[j][i];
				}
			}
			min = Math.min(min, Math.abs(val[0]-val[1]));
		} while (np(arr));
		System.out.println(min);
		
	}
}
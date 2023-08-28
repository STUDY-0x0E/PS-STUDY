package algo0828;

/*
1.summary:
숫자 N을 입력받고, 다음줄에는 N개의 자연수를 입력받는다. 다음줄에는 합이 N-1인 각 연산자의 갯수를 입력받는다.
연산은 연산자 우선순위를 무시하고 앞에서부터 진행한다.
주어진 연산자로 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 출력한다.

2.strategy:
순열을 이용하여 가능한 모든 경우의 수에서 식의 결과를 얻고 최대, 최소인 값을 구한다.

3.note:
연산 순서가 일반적이지 않음에 유의한다.
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888 {
	static boolean np(int[] arr) {
		int i = arr.length-1;
		while (i>0 && arr[i]<=arr[i-1]) i--;
		if (i==0) return false;
		int j = arr.length-1;
		while (arr[j]<=arr[i-1]) j--;
		swap(arr, j, i-1);
		int k = arr.length-1;
		while(i<k) {
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
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] oper = new int[4];
		int[] order = new int[N-1];
		st = new StringTokenizer(br.readLine());
		for (int i=0, idx=0; i<oper.length; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
			for (int j=0; j<oper[i]; j++) {
				order[idx++] = i;
			}
		}
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		do {
			int ans = arr[0];
			
			for (int i=0; i<order.length; i++) {
				if (order[i]==0) {
					ans += arr[i+1];
				} else if (order[i]==1) {
					ans -= arr[i+1];
				} else if (order[i]==2) {
					ans *= arr[i+1];
				} else if (order[i]==3) {
					ans /= arr[i+1];
				}
			}
			if (ans<min) min = ans;
			if (ans>max) max = ans;
		} while (np(order));
		
		System.out.println(max);
		System.out.println(min);
	}
}

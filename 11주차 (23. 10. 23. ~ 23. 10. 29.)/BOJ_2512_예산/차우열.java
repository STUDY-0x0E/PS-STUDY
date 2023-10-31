package week11;

//이분탐색

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2512 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		int sum = 0;
		for(int i=0; i<N; i++) {
			sum+=arr[i];
		}
		if (sum<=M) {
			System.out.println(arr[N-1]);
			return;
		}
		
		int high = M-1;
		int low = 0;
		int mid = 0;
		int idx = 0;
		
		while (low<high) {
			mid = (low+high)/2;
			sum = 0;
			for (idx=0; idx<N; idx++) {//상한액보다 작으면 그 값을 더한다.
				if (arr[idx]<mid) sum+=arr[idx];
				else break;
			}	
			sum+=(N-idx)*mid;//상한액보다 크다면 대상값만큼 더한다.
//			System.out.println(sum);
			if (sum<=M) {
				low = mid+1;
			} else {
				high = mid;
			}
		}
		
		System.out.println(high-1);
	}
}

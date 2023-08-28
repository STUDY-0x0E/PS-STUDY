package backtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN14888_OpInsert {

	static int N, operand[], operator[];
	static int max= Integer.MIN_VALUE, min= Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		operand = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			operand[i]= Integer.parseInt(st.nextToken());
		}
		
		operator = new int[4];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			operator[i]= Integer.parseInt(st.nextToken());
		}
		
		find(0,operand[0]);
		System.out.println(max);
		System.out.println(min);
	}

	private static void find(int cnt, int result) {
		if (cnt == N-1) {
			min= (min > result)? result : min;
			max= (max < result)? result : max;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (operator[i] > 0) {
				operator[i]--;
				find(cnt+1, calculate(cnt,result,i));
				operator[i]++;
			}
		}
	}

	private static int calculate(int cnt,int result, int i) {
		switch (i) {
		case 0:
			result= result + operand[cnt+1];
			break;
		case 1:
			result= result - operand[cnt+1];
			break;
		case 2:
			result= result * operand[cnt+1];
			break;
		case 3:
			result= result / operand[cnt+1];
			break;
		}
		return result;
	}

}

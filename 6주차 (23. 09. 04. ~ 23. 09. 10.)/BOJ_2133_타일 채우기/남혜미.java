import java.util.*;
import java.io.*;
/*
1. summary :
	3×N 크기의 벽을 2×1, 1×2 크기의 타일로 채우는 경우의 수 구하기
2. strategy

3. note
	N이 짝수인 경우에만 타일을 설치할 수 있다.
*/
public class BOJ2133 {

	static long D[];
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		D = new long[N+1];
		
		D[0] = 0;
		
		if(N > 1) D[2] = 3;
		
		for(int i = 4; i <= N; i+=2) {
			D[i] += D[i-2] * D[2];
			for(int j = 4; j <= i; j+=2)
			{
				if(i-j == 0) D[i] +=2;
				else D[i] += D[i-j] * 2;
			}			
		}
		System.out.println(D[N]);
	}
}
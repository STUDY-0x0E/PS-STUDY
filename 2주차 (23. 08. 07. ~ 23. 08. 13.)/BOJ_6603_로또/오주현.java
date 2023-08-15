package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 오주현 {

	static ArrayList<Integer> S = new ArrayList<>();
	static int k;
	static int[] numbers;
	static boolean[] isSelected;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			sb = new StringBuilder();
			k = Integer.parseInt(st.nextToken());
			if (k == 0)
				break;
			
			numbers = new int[k];
			isSelected = new boolean[k];
			
			for (int i = 0; i < k; i++) {
				S.add(Integer.parseInt(st.nextToken()));
			}
			permutation(0,0);
			System.out.println(sb);
			
			S.clear();
			
		}
		
		

	}
	
	public static void permutation(int cnt, int start) {
		if (cnt == 6) {
			for (int i = 0; i < k; i++) {
				if (isSelected[i])
					sb.append(S.get(i)+" ");
			}
			sb.append("\n");

			return;
		}
		
		for (int i = start; i < k; i++) {
			if (isSelected[i])	continue;
			numbers[cnt]= S.get(i);
			isSelected[i]= true;
			permutation(cnt+1, i);
			isSelected[i]= false;
		}
			
	}

}

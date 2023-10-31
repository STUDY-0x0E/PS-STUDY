import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int C;
	static ArrayList<Long> list1, list2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int weight[] = new int[N]; 
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) weight[i] = Integer.parseInt(st.nextToken());
		
		int[] subset1 = Arrays.copyOfRange(weight, 0, N/2);
		int[] subset2 = Arrays.copyOfRange(weight, N/2, N);
		
		list1 = new ArrayList<>();
		list2 = new ArrayList<>();
		
		findSum(0, 0, subset1, list1);
		findSum(0, 0, subset2, list2);
		
		Collections.sort(list2);
		int length1 = list1.size();
		int length2 = list2.size();

		int answer = 0;
		for (int i = 0; i < length1; i++) {
			answer += binSearch(C - list1.get(i), length2);
		}

		System.out.println(answer);
	}

	private static int binSearch(long toFind, int end) {
		int start = 0;
		
		while (start+1 < end) {
			int mid = (start + end) / 2;
			if (list2.get(mid) > toFind) end = mid;
			else start = mid;
		}

		return end;
	}

	private static void findSum(int cnt, long sum, int[] subset, ArrayList<Long> list) {
		if (sum > C) return;
		else if (cnt == subset.length) {
			list.add(sum);
			return;
		}
		
		findSum(cnt+1, sum+subset[cnt], subset, list);
		findSum(cnt+1, sum, subset, list);
	}
}
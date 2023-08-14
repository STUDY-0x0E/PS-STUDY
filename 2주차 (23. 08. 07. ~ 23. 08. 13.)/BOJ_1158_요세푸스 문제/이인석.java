import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. summary
 * 
 * k번째 순서마다 사람을 제거할 때,
 * 제거하는 사람의 순서를 구하시오
 * 
 * 2. strategy
 * 
 * k번째 사람의 인덱스를 구해서
 * ArrayList로 용이하게 제거
 * 
 * 3. note
 */

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer input = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(input.nextToken());
		int k = Integer.parseInt(input.nextToken());
		
		List<Integer> list = new ArrayList<>(n);
		for (int i = 1; i < n + 1; i++) {
			list.add(i);
		}
		int[] result = new int[n];
		
		int cnt = 0;
		int idx = 0;
		while (!list.isEmpty()) {		
			idx = (idx + k - 1) % list.size(); 
			result[cnt++] = list.get(idx);
			list.remove(idx);
		}
		
		bw.write("<");
		for (int i = 0; i < n - 1; i++) {
			bw.write(result[i] + ", " );
		}
		bw.write(result[n - 1] + ">");
		bw.flush();
		bw.close();
		br.close();
	}

}
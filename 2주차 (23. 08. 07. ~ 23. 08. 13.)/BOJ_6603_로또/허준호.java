import java.io.*;
import java.util.*;

public class Main {
	static int k;
	static int[] nums, ans;
	static boolean[] isSelected;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb= new StringBuilder();
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if (k == 0)
				break;

			ans = new int[6];
			nums = new int[k];
			isSelected = new boolean[k];

			for (int i = 0; i < k; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			comb(0, 0);//조합
			sb.append("\n");
		}
		sb.replace(sb.length()-2, sb.length(), "");
		bw.write(sb.toString());
		bw.flush();
	}

	private static void stringBuild() throws IOException {//출력 생성
		for (int i = 0; i < ans.length; i++) {
			sb.append(ans[i]).append(' ');
		}
		sb.append("\n");
	}

	public static void comb(int cnt, int start) throws IOException {
		if (cnt == 6) {
			stringBuild();
			return;
		}
		for (int i = start; i < k; i++) {
			ans[cnt] = nums[i];
			comb(cnt + 1, i + 1);
		}
	}
}
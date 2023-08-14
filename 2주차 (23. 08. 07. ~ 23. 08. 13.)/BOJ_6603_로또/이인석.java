import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	private static BufferedReader br;
	private static BufferedWriter bw;

	public static void main(String[] args) throws Exception {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int k = 0;
		String[] st = br.readLine().trim().split(" ");
		String[] numbers = new String[6];
		while ((k = Integer.parseInt(st[0])) != 0) {
			
			printComb(0, 1, numbers, st);		
			bw.write("\n");
			st = br.readLine().trim().split(" ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void printComb(int cnt, int range, String[] comb, String[] data) throws Exception {
		
		if (cnt >= 6) {
			for (int i = 0; i < 6; i++) {
				bw.write(comb[i] + " " );
			}
			bw.write("\n");
			return;
		}
		
		for (int i = range; i < data.length; i++) {
			comb[cnt] = data[i];
			printComb(cnt + 1, i + 1, comb, data);
		}
	}

}
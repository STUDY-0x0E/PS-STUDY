import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		BigInteger[] road = new BigInteger[n - 1];
		for (int i = 0; i < n - 1; i++) {
			road[i] = new BigInteger(st.nextToken()); 
		}
		
		st = new StringTokenizer(br.readLine());
		BigInteger[] node = new BigInteger[n];
		for (int i = 0; i < n; i++) {
			node[i] = new BigInteger(st.nextToken());
		}
		
		BigInteger cost = new BigInteger("0");
		int idx = 0;
		while (idx < n - 1) {
			int next = idx + 1;
			while (node[idx].compareTo(node[next]) == -1) {
				++next;
				if (next == n - 1) break;
			}
			
			for (int i = idx; i < next; i++) {
				cost = cost.add(node[idx].multiply(road[i])); 
			}
			idx = next;
		}
		
		bw.write(cost.toString());		
		bw.flush();
		bw.close();
		br.close();
	}
}
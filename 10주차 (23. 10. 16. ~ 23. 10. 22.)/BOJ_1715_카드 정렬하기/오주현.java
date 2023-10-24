import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Long> sum = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			sum.add(Long.parseLong(br.readLine()));
		}	
		
		long total = 0;
		while (sum.size() != 1) {
			long a = sum.poll();
			long b = sum.poll();
			long c = a+b;
			total+= c;
			sum.add(c);
		}
		
		System.out.println(total);
		
	}
}
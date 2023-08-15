import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
		int n = sc.nextInt();
		int k = sc.nextInt();

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			queue.add(i);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
       while(queue.size() != 1) {
            for (int i = 0; i < k - 1; i++) {
                queue.offer(queue.poll());
            }
            sb.append(queue.poll() + ", ");
        }
	       
		sb.append(queue.poll()).append(">");
		bw.write(sb.toString());
		bw.flush();
		
	}

}
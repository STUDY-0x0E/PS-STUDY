import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Stack<Character> frontStack = new Stack<>();
		Stack<Character> backStack = new Stack<>();
		
		String firstStr = br.readLine().trim();
		for (int i = 0; i < firstStr.length(); i++) {
			frontStack.add(firstStr.charAt(i));
		}
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 String operater = st.nextToken();
			 
			 if (operater.equals("L")) {
				 if (!frontStack.isEmpty()) backStack.add(frontStack.pop());
			 }
			 else if (operater.equals("D")) {
				 if (!backStack.isEmpty()) frontStack.add(backStack.pop());
			 }
			 else if (operater.equals("B")) {
				 if (!frontStack.isEmpty()) frontStack.pop();
			 }
			 else {
				 frontStack.add(st.nextToken().charAt(0));
			 }
		}
		
		for (Object c : frontStack.toArray()) {
			bw.write((Character) c);
		}
		while (!backStack.isEmpty()) {
			bw.write(backStack.pop());
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
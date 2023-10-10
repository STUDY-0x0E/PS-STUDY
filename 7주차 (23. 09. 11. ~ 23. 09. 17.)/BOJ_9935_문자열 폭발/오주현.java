import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		Stack<Character> stack = new Stack<Character>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String explosion = br.readLine();
		
		for (int i = 0; i < s.length(); i++) {
			stack.push(s.charAt(i));
			
			if (stack.size() >= explosion.length()) {
				boolean same = true;
				
				for (int j = 0; j < explosion.length(); j++) {
					if (stack.get(stack.size()-explosion.length()+j) != explosion.charAt(j)){
						same = false;
						break;
					}
				}
				
				if (same) {
					for (int j = 0; j < explosion.length(); j++) {
						stack.pop();
					}
				}
			}
		}
		
		if (stack.size() != 0) {
			StringBuilder sb = new StringBuilder();
			for (Character c : stack) {
				sb.append(c);
			}
			System.out.println(sb);
		}
		else
			System.out.println("FRULA");
	}
}

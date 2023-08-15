import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		String input = br.readLine();
		Deque<Character> left = new LinkedList<>();
		Deque<Character> right = new LinkedList<>();
		for (int i = 0; i < input.length(); i++) // String을 Dequeue형태로 변환
			left.addLast(input.charAt(i));

		int m = Integer.parseInt(br.readLine());// 명령어 개수
		for (int i = 0; i < m; i++) {
			String cmd = br.readLine();
			try {
				switch (cmd.charAt(0)) {
				case 'L':// 커서 왼쪽 옮김
					right.addFirst(left.removeLast());
					break;
				case 'D':// 커서 오른쪽 옮김
					left.addLast(right.removeFirst());
					break;
				case 'B':// 커서 왼쪽 삭제
					left.removeLast();
					break;
				case 'P':// 커서 왼쪽 추가
					left.addLast(cmd.charAt(2));
					break;
				}
			} catch (Exception e) {}
		}

		for (Character c : left) 
			sb.append(c);
		for (Character c : right) 
			sb.append(c);
		bw.write(sb.toString());
		bw.close();
	}
}
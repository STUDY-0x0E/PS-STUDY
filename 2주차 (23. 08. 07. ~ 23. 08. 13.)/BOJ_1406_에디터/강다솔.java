package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_1406 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Character> editor = new ArrayList<>();
		ListIterator<Character> iter = editor.listIterator();

		for (char c : br.readLine().toCharArray()) {
			iter.add(c);
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			char[] command = br.readLine().toCharArray();

			if (command[0] == 'L') {
				if (iter.hasPrevious())
					iter.previous();
			} else if (command[0] == 'D') {
				if (iter.hasNext())
					iter.next();
			} else if (command[0] == 'B') {
				if (iter.hasPrevious()) {
					iter.previous();
					iter.remove();
				}
			} else if (command[0] == 'P') {
				iter.add(command[2]);
			}

		}

		StringBuilder sb = new StringBuilder();
		for (char c : editor) {
			sb.append(c);
		}
		sb.append("\n");
		System.out.println(sb.toString());

	}

}

package week2;

import java.util.LinkedList;
import java.util.Scanner;

public class 오주현 {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int next = K-1;
		LinkedList<Integer> list = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		sb.append("<");
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}
		
		while(list.size() != 0) {
			while (next >= list.size())
				next-= list.size();
			sb.append(list.remove(next)).append(", ");
			next+= K-1;	
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(">");
		System.out.println(sb);
	}

}

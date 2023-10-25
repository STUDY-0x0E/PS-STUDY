import java.util.*;
import java.io.*;

/*
1. summary
	네 개의 명령어 D, S, L, R
	- D : n을 두 배로 바꾼다. (9999보다 큰 경우 10000으로 나눈 나머지) => 2n mod 10000 
	- S : n에서 1을 뺀다. (n이 0이라면 9999 저장)
	- L : 각 자리를 왼편으로 회전시킨다.
	- R : 각 자리르 오른편으로 회전시킨다.
	
	두 정수가 주어졌을 때, 최소한의 명령어를 생성하는 프로그램을 구하자.
2. strategy : BFS
3. note
	두 개의 정수 A와 B(A ≠ B)
*/

public class BOJ9019 {

	static class Node{
		int num;
		String commend;

		public Node(int num, String commend) {
			this.num = num;
			this.commend = commend;
		}
	}
	
	static int startNum, endNum;
	static String result;
	static boolean visited[];
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			startNum = Integer.parseInt(st.nextToken());
			endNum = Integer.parseInt(st.nextToken());
			
			visited = new boolean[10000];
			
			BFS();
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void BFS() { // DSLR 실행
		
		Queue<Node> q = new ArrayDeque<>();
		
		q.offer(new Node(startNum, ""));
		
		while(!q.isEmpty()) {
			
			Node current = q.poll();
	
			int num = 0;
			String commend = "";
			
			// D
			num = (current.num * 2) % 10000;
			if(!visited[num]) { // 아직 체크하지 않은 숫자라면
                visited[num] = true;
				commend = current.commend + "D";
				if(check(num)) { 
					result = commend; // 결과 반환
					break;
				}
				else {
					q.offer(new Node(num, commend));
				}
			}
			
			// S
			num = (current.num == 0) ? 9999 : (current.num - 1); 
			if(!visited[num]) {
                visited[num] = true;
				commend = current.commend + "S";
				if(check(num)) {
					result = commend;
					break;
				}
				else {
					q.offer(new Node(num, commend));
				}
			}
			
			// L
			num = ((current.num * 10) % 10000) + ((current.num * 10) / 10000);
			if(!visited[num]) {
                visited[num] = true;
				commend = current.commend + "L";
				if(check(num)) {
					result = commend;
					break;
				}
				else {
					q.offer(new Node(num, commend));
				}
			}

			// R 
			num = ((current.num % 10) * 1000) + (current.num / 10);
			if(!visited[num]) {
                visited[num] = true;
				commend = current.commend + "R";
				if(check(num)) {
					result = commend;
					break;
				}
				else {
					q.offer(new Node(num, commend));
				}
			}
		}
	}
	
	public static boolean check(int num) { // 결과값과 같은지 체크
		if(num == endNum) return true;
		return false;
	}
}
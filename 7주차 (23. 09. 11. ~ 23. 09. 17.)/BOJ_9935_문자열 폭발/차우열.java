package week7;

/*
1.summary:
문자열을 입력받고, 다음줄에 폭발문자열을 입력받는다.
뮨저열내에 폭발문자열이 있다면 그 부분만 사라진다.
폭발문자열이 사라져서 새로 폭발문자열이 생길 수 있다.
모든 폭발문자열을 제거한 후의 문자열을 출력한다. 

2.strategy:
링크드리스트를 이용해서 스택과 남은 문자열을 저장한다.
문자열을 읽으면서 폭발문자열을 확인하는 과정에서 폭발문자열의 첫글자를 만나면 확인하던 문자를 스택에 넣고 나중에 확인한다.

3.note:
메모리초과가 나지않게 조심한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9935 {
	static class node {
		char c;
		node pre;
		node next;
		node(char c, node pre){
			this.c = c;
			this.pre = pre;
		}
	}
	
	static class snode{
		int val;
		node start;
		snode pre;
		snode(node start, snode pre){
			this.start = start;
			this.pre = pre;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		String key = st.nextToken();
		
		snode top = null;
		
		node head = new node('\0',null);
		node curnode = head;
		for (int e=0; e<str.length(); e++) {
			curnode = new node(str.charAt(e), curnode);
			curnode.pre.next = curnode;
			
			if (str.charAt(e)==key.charAt(0)) {
				top = new snode(curnode, top);
			} 
			if (top!=null) {
				if(str.charAt(e)==key.charAt(top.val)) {
					top.val++;
					if (top.val==key.length()) {
						curnode = top.start.pre;
						curnode.next = null;
						top = top.pre;
					}
				} else top = null;
			}
		}
		
		curnode = head.next;
		StringBuilder sb = new StringBuilder();
		while(curnode!=null) {
			sb.append(curnode.c);
			curnode = curnode.next;
		}
		
		if(sb.length()>0) {
			System.out.println(sb);
		} else {
			System.out.println("FRULA");
		}
	}
}
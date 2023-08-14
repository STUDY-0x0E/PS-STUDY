package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1.summary:
편집할 문자열 S, 입력할 명령어 수 N, N개의 명령어 cmd를 입력받는다.
명령어 L, D, B ,P $에 따라 커서를 움직이거나 문자열 S를 편집한다.
L은 커서 왼쪽이동, D는 커서 오른쪽 이동, B는 커서왼쪽 제거, P $는 문자 $를 추가하는 명령어이다.
편집이 완료되면 편집된 문자열을 출력한다. 

2.strategy:
StringBuilder를 이용한 편집을 시도하면 시간초과가 발생한다.
이중 LinkedList를 구현하면 커서 위치에서의 삭제, 삽입 연산을 빠르게 수행할 수 있다.

3.note:
리스트 양끝에서 수행하는 명령어를 주의해야 한다.
*/

class cdnode{
	char value;
	cdnode pre;
	cdnode next;
	
	cdnode(char value){
		this.value =value;
	}
	
	cdnode(char value, cdnode pre){
		this(value);
		this.pre = pre;
	}
	
	cdnode(char value, cdnode pre, cdnode next){
		this(value, pre);
		this.next = next;
	}
}

public class Editor {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String S = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		cdnode cursor = new cdnode(S.charAt(0));
		cdnode front = cursor;
		
		for (int i=1; i<S.length(); i++) {
			cursor = new cdnode(S.charAt(i), cursor);
			cursor.pre.next = cursor;
		}
		cursor = new cdnode(' ', cursor);
		cursor.pre.next = cursor;
		cdnode last = cursor;
		
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			switch(cmd) {
			case "L":
				if (cursor.pre!=null)
					cursor = cursor.pre;
				break;
			case "D":
				if (cursor.next!=null)
					cursor = cursor.next;
				break;
			case "B":
				if (cursor.pre!=null) {
					if (cursor.pre==front) {
						front = cursor;
						cursor.pre = null;
					} else {
						cursor.pre.pre.next = cursor;
						cursor.pre = cursor.pre.pre;
					}
				}
				break;
			case "P":
				if (cursor.pre!=null) {
				cursor.pre.next = new cdnode(st.nextToken().charAt(0), cursor.pre, cursor);
				cursor.pre = cursor.pre.next;
				} else {
					front = new cdnode(st.nextToken().charAt(0), null, front);
					front.next.pre = front;
				}
				break;
			default:
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(front!=last) {
			sb.append(front.value);
			front = front.next;
		}
		System.out.println(sb);
	}
}

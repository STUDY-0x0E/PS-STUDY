package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1.summary:
������ ���ڿ� S, �Է��� ��ɾ� �� N, N���� ��ɾ� cmd�� �Է¹޴´�.
��ɾ� L, D, B ,P $�� ���� Ŀ���� �����̰ų� ���ڿ� S�� �����Ѵ�.
L�� Ŀ�� �����̵�, D�� Ŀ�� ������ �̵�, B�� Ŀ������ ����, P $�� ���� $�� �߰��ϴ� ��ɾ��̴�.
������ �Ϸ�Ǹ� ������ ���ڿ��� ����Ѵ�. 

2.strategy:
StringBuilder�� �̿��� ������ �õ��ϸ� �ð��ʰ��� �߻��Ѵ�.
���� LinkedList�� �����ϸ� Ŀ�� ��ġ������ ����, ���� ������ ������ ������ �� �ִ�.

3.note:
����Ʈ �糡���� �����ϴ� ��ɾ �����ؾ� �Ѵ�.
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

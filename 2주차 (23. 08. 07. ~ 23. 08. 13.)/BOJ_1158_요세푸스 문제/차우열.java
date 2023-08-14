package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
1.summary:
�ڿ��� N�� K�� �Է¹޴´�. ������ N���� ����� �ɾ������� K��° ����� �����Ѵ�.
���� �� ������ �ٽ� ����� ���� K��° ����� �����ϴ� ������ �ݺ��Ѵ�.
���ŵ� ����� ó�� ������ ���ŵ� ������ ������ �� ������ ����� �������� �� ����Ѵ�.

2.strategy:
double circular linked list�� �̿��ϸ� circular linked list���� �� ������ ������ �ذ� �� �� �ִ�.
���� ������� cnt�� �ϸ�, K��° ����� K%cnt��° ����� �׻� ����.
���� K%cnt>cnt-K%cnt��� cnt-K%cnt��ŭ �������� ���°� �� ������.

3.note:
linked list��  ������ ���� �����ϴ� node�� �����ϰ� �����ؾ��Ѵ�.
*/
class idnode{
	int value;
	idnode next;
	idnode pre;
	idnode(int value, idnode pre){
		this.value = value;
		this.pre = pre;
	}
}

public class Josephus3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int cnt = N;
		
		idnode cur = new idnode(1, null);
		idnode head = cur;
		for (int i=2; i<=N; i++) {
			cur = new idnode(i, cur);
			cur.pre.next = cur;
		}
		cur.next = head;
		head.pre = cur;
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		while(cur.next != cur) {
			boolean forward = true;
			int min = K%cnt;
			if (cnt-min<min) {
				forward = false;
				min = cnt-min;
			}
			
			if (forward) {
				for (int i=0; i<min; i++) {
					cur = cur.next;
				}
			} else {
				for (int i=0; i<min; i++) {
					cur = cur.pre;
				}
			}
			sb.append(cur.value).append(", ");
			cur.next.pre = cur.pre;
			cur.pre.next = cur.next;
			cur = cur.pre;
			cnt--;

		}
		
		sb.append(cur.value).append(">");
		System.out.println(sb);
	}

}

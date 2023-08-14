package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
1.summary:
자연수 N과 K를 입력받는다. 원으로 N명의 사람이 앉아있을때 K번째 사람을 제거한다.
제거 후 원으로 다시 만들고 다음 K번째 사람을 제거하는 과정을 반복한다.
제거된 사람의 처음 순서를 제거된 순서로 저장한 뒤 마지막 사람을 제거했을 때 출력한다.

2.strategy:
double circular linked list를 이용하면 circular linked list보다 더 빠르게 문제를 해결 할 수 있다.
남은 사람수를 cnt라 하면, K번째 사람과 K%cnt번째 사람은 항상 같다.
만약 K%cnt>cnt-K%cnt라면 cnt-K%cnt만큼 역순으로 세는게 더 빠르다.

3.note:
linked list의  참조와 현재 참조하는 node를 적절하게 설정해야한다.
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

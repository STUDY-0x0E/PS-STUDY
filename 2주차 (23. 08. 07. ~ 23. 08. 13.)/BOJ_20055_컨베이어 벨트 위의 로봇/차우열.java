package myAlgo;

/*
1.summary:
�����̾� ��Ʈ�� ���� N�� �������� K�� �Է¹޴´�. �����ٿ��� 2N���� �����̾� ��Ʈ�� �������� �Է¹޴´�.
�����̾� ��Ʈ�� ������ �Է� ������ ���� ��ġ�� ������ ����.
1    2    3  ... N-1  N
2N 2N-1 2N-2 ... N+2 N+1
�����̾� ���鿡 ���� �� ��ġ������ �κ��� �ø� �� �ְ�, �����̾� ���鿡 ������ �� ��ġ���� �κ��� �ִٸ� ��� ������.
�������� 0�� �����̾� ��Ʈ�� K�� �̸��̶�� ������ �ݺ��Ѵ�.
1. ��Ʈ�� �ð�������� ȸ���Ѵ�.
2. ��Ʈ���� �κ��� �ð�������� �����ϴٸ� �̵���Ų��. �̵���Ű�� ���ؼ� �̵��� ĭ�� �������� 1�̻��̿����ϰ� �κ��� ������Ѵ�. �κ��� ���� �� �ִٸ� ������.
3. �κ��� �ø� �� �ִٸ� �ø���. �κ��� �ø��� ���ؼ��� ĭ�� �������� 1�̻��̿����Ѵ�. (1.�� ���ؼ� ���ĭ���� �ٸ� �κ��� ���� �� ����)
4. �������� 0�� �����̾� ��Ʈ���� K�� �̸��̶�� 1.�� ���ư���.
����� ���° �ݺ����� ����϶�.

2.strategy:
double circular linked list�� �̿��Ͽ� �ذ��Ѵ�.
�����̾� ���� ���� ���� ������ ���� �����صдٸ� ������ ���� �� �� �ִ�.

3.note:
�� ������ ������ ��带 �����Ѿ� �Ѵ�.
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotOnCon {
	static class node{
		boolean isRobot;
		int dura;
		node pre;
		node next;
		
		node(int dura, node pre){
			this.dura = dura;
			this.pre = pre;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		node cur = new node(0, null);
		node head = cur;
		
		for (int i=0; i<N; i++) {
			cur = new node(Integer.parseInt(st.nextToken()), cur);
			cur.pre.next = cur;
		}
		node end = cur;
		for (int i=0; i<N; i++) {
			cur = new node(Integer.parseInt(st.nextToken()), cur);
			cur.pre.next = cur;
		}
		
		cur.next = head.next;
		cur.next.pre = cur;
		head = cur.next;
		
		int broken = 0;
		int iter = 0;
		
		
		while (broken<K) {
			iter++;
			head = head.pre;
			end = end.pre;

			end.isRobot = false;
			
			cur = end;
			for (int i=0; i<N-1; i++) {
				cur = cur.pre;
				if (cur.isRobot && cur.next.dura>0 && !cur.next.isRobot) {
					cur.next.dura--;
					if (cur.next.dura==0) broken++;
					cur.isRobot = false;
					cur.next.isRobot = true;
				}
			}

			end.isRobot = false;
			
			if (head.dura>0) {
				head.dura--;
				if (head.dura==0) broken++;
				head.isRobot = true;
			}
		}
		System.out.println(iter);
	}
}

package myAlgo;

/*
1.summary:
컨베이어 벨트의 길이 N과 종료조건 K를 입력받는다. 다음줄에는 2N개의 컨베이어 벨트의 내구도를 입력받는다.
컨베이어 벨트의 내구도 입력 순서에 따른 위치는 다음과 같다.
1    2    3  ... N-1  N
2N 2N-1 2N-2 ... N+2 N+1
컨베이어 윗면에 왼쪽 끝 위치에서는 로봇을 올릴 수 있고, 컨베이어 윗면에 오른쪽 끝 위치에서 로봇이 있다면 즉시 내린다.
내구도가 0인 컨베이어 벨트가 K개 미만이라면 다음을 반복한다.
1. 벨트를 시계방향으로 회전한다.
2. 벨트위의 로봇을 시계방향으로 가능하다면 이동시킨다. 이동시키기 위해서 이동할 칸의 내구도가 1이상이여야하고 로봇이 없어야한다. 로봇을 내릴 수 있다면 내린다.
3. 로봇을 올릴 수 있다면 올린다. 로봇을 올리기 위해서는 칸의 내구도가 1이상이여야한다. (1.에 의해서 대상칸에는 다른 로봇이 있을 수 없다)
4. 내구도가 0인 컨베이어 벨트수가 K개 미만이라면 1.로 돌아간다.
종료시 몇번째 반복인지 출력하라.

2.strategy:
double circular linked list를 이용하여 해결한다.
컨베이어 윗면 왼쪽 끝과 오른쪽 끝을 저장해둔다면 빠르게 연산 할 수 있다.

3.note:
각 변수가 적절한 노드를 가리켜야 한다.
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

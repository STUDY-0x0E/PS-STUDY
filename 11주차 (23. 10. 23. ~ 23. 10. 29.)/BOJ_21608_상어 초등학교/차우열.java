package week11;

/*
1.summary:
자리의 수 N을 입력받고 다음 N*N줄에 걸쳐 학생 정보를 입력받는다.
학생정보는 각 학생의 번호와 그 학생이 좋아하는 4명의 학생의 번호이다.
학생을 입력받은 순서대로 학생을 자리에 배치하는데, 다음 규칙을 고려해서 배치한다.
1) 주변에 좋아하는 학생이 많은 자리에 우선배치한다.
2) 1)의 결과가 여러개일 경우 추가로 주변에 빈 자리가 많은 자리에 배치한다.
3) 2)의 결과가 여러개일 경우 추가로 행번호가 가장낮은 순으로 배치한다. 행번호가 같다면 열번호가 가장 낮은 순으로 배치한다.
주변은 상하좌우를 말한다.
학생을 전부 배치한 후, 각 학생의 주변에 좋아하는 학생의 수에 따라 각 만족도를 구한다.
좋아하는 학생의 수(만족도): 0(0), 1(1), 2(10), 3(100), 4(1000) 
모든 학생의 만족도 합을 출력한다.

2.strategy:
주어진 문제를 구현한다.
배치할 수 있는 남은 자리는 linkedlist를 사용했다.

3.note:
학생을 배치할 때 이미 입력받은 학생의 번호에 대해서만 고려할 수 있을것같다.
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21608 {
	static class node {
		int pos;
		node next;

		node(int pos) {
			this.pos = pos;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		int[][] friendly = new int[N * N][5];
		int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

		node head = new node(-1);
		node cur = head;

		for (int i = 0; i < friendly.length; i++) {
			cur.next = new node(i);
			cur = cur.next;

			st = new StringTokenizer(br.readLine());
			friendly[i][0] = Integer.parseInt(st.nextToken());
			friendly[i][1] = Integer.parseInt(st.nextToken());
			friendly[i][2] = Integer.parseInt(st.nextToken());
			friendly[i][3] = Integer.parseInt(st.nextToken());
			friendly[i][4] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < friendly.length; i++) {
			int tarval = 0;
			node tarnode = head;

			cur = head;
			while (cur.next != null) {
				int x = cur.next.pos / N;
				int y = cur.next.pos % N;

				int val = 0;
				for (int d = 0; d < dir.length; d++) {
					int dx = x + dir[d][0];
					int dy = y + dir[d][1];
					if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
						if (arr[dx][dy] == 0) {
							val += 1;
						} else {
							for (int j = 1; j < 5; j++) {
								if (arr[dx][dy] == friendly[i][j]) {
									val += 10;
									break;
								}
							}
						}
					}
				}
				if (tarval < val) {
					tarval = val;
					tarnode = cur;
				}
				cur = cur.next;
			}

			int pos = tarnode.next.pos;
			arr[pos / N][pos % N] = friendly[i][0];
			friendly[i][0] = pos;
			tarnode.next = tarnode.next.next;
		}

		int sum = 0;
		for (int i = 0; i < friendly.length; i++) {
			int cnt = 0;
			for (int d = 0; d < dir.length; d++) {
				int dx = friendly[i][0] / N + dir[d][0];
				int dy = friendly[i][0] % N + dir[d][1];
				if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
					for (int j = 1; j < 5; j++) {
						if (arr[dx][dy] == friendly[i][j]) {
							cnt++;
						}
					}
				}
			}
			if (cnt==1) {
				sum+=1;
			} else if (cnt==2) {
				sum+=10;
			} else if (cnt==3) {
				sum+=100;
			} else if (cnt==4) {
				sum+=1000;
			}
		}

		System.out.println(sum);

	}
}

/*
 
3
4 -1 -1 -1 -1
3 -1 -1 -1 -1
9 -1 -1 -1 -1
8 -1 -1 -1 -1
7 -1 -1 -1 -1
1 -1 -1 -1 -1
6 -1 -1 -1 -1
5 -1 -1 -1 -1
2 -1 -1 -1 -1


 */

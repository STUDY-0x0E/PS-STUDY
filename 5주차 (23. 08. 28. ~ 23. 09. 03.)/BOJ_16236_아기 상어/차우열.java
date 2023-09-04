package algo0904;

/*
1.summary:
공간의 크기 N을 입력받고 다음 N줄에 걸쳐 N*N의 공간정보를 입력받는다.
9는 아기상어의 시작 위치이고 1~6은 물고기의 크기이다.
아기상어의 시작크기는 2이고 가장 가깝고(위, 왼쪽 우선) 자신보다 작은 물고기가 있는 지점으로 이동하면서 해당 물고기를 먹는다.
아기상어는 자신보다 크기가 큰 물고기가 있는 지점을 이동할 수 없다.
아기상어는 현재 크기만큼의 물고기를 먹을때마다 크기가 1증가한다.
아기상어가 먹을 수 있는 물고기가 없을때까지 이동한거리를 출력한다.

2.strategy:
BFS로 거리가 같은 지점을 탐색하면서 우선순위에 맞는 지점으로 이동한다.

3.note:
우선순위 큐를 이용한 탐색이 효과적이지 않았다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N+2][N+2];
		final int inf = 100;
		
		Arrays.fill(board[0], inf);
		Arrays.fill(board[N+1], inf);
		
		for (int i=1; i<=N; i++) {
			board[i][0] = inf;
			board[i][N+1] = inf;
		}
		
		int[] babyShark = new int[3];
		int size = 2;
		int cnt = 0;
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j]==9) {
					board[i][j]=0;
					babyShark[0] = i;
					babyShark[1] = j;
				}
			}
		}
		
		int[][] dir = {{-1,0},{0,-1},{0,1},{1,0}};
		while (true) {
			boolean[][] visited = new boolean[N+2][N+2];
			Queue<int[]> q = new ArrayDeque<int[]>();
			q.offer(babyShark);
			visited[babyShark[0]][babyShark[1]] = true;
			int[] tmp = null;
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				if (tmp!=null && tmp[2]==cur[2]) break;
				for (int i=0; i<dir.length; i++) {
					int x = cur[0] + dir[i][0];
					int y = cur[1] + dir[i][1];
					if (board[x][y]!=0 && size>board[x][y]) {
						if (tmp==null) {
							tmp = new int[] {x, y, cur[2]+1};
						} else {
							boolean flag = false;
							if (tmp[0]>x) flag = true;
							else if (tmp[0]==x && tmp[1]>y) flag = true;
							if (flag) tmp = new int[] {x, y, cur[2]+1};
						}
						
					}
					else if (!visited[x][y] && (size==board[x][y]||board[x][y]==0)) {
						visited[x][y] = true;
						q.offer(new int[] {x,y, cur[2]+1});
					}
				}	
			}
			
			if (tmp!=null) {
				if (++cnt==size) {
					size++;
					cnt=0;
				}
				board[tmp[0]][tmp[1]] = 0;
				babyShark = tmp;
			} else break;
			
		}
		System.out.println(babyShark[2]);
	}
}

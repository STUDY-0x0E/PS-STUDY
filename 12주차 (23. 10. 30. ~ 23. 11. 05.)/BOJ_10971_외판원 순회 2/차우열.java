package week12;

/*
1.summary:
외판원 순회문제(Traveling Salesman Problem).
도시의 수 N을 입력받고, 다음 N줄에 걸쳐 그 도시에서 다른 도시로 갈 때의 비용을 입력받는다.
비용이 0인경우는 이동할 수 없는 경우이다.
어떤 도시에서 출발해서 다른 모든 도시를 방문해서 원래 도시로 돌아올 때의 최소 비용을 출력한다.
한 번 방문한 도시는 다시 방문할 수 없다.
항상 순회할 수 있는 경우만 입력으로 주어진다.

2.strategy:
BFS로 탐색을 수행한다. 출발점, 방문도시, 현재도시가 같을때의 최소 비용을 비트마스킹을 사용한 방문배열에 저장한다.
이렇게 하면 출발도시를 제외하고 i번째 도시를 방문할 때 (i-1)!의 경우의 수를 덜 탐색할 수 있다.
10개 도시의 경우 최대 9!*8!*7!*6!*...*0!의 경우의 수를 덜 탐색한다.

3.note:
방문하지 않은 도시에서 진행하지 않도록한다.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ10971 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] adjM = new int[N][N];
		int[] bit = new int[N];
		for (int i=0; i<N; i++) {
			bit[i] = 1<<i;
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				adjM[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		
		for (int s=0; s<N; s++) {
			int[][] visited = new int[1<<N][N];
			Queue<int[]> q = new ArrayDeque<int[]>();
			q.offer(new int[]{s, bit[s]});
			
			for (int i=0; i<N-1; i++) {//while(!q.isEmpty())이면 for(int i=0; i<N; i++)이다. 
				int size = q.size();
				while (size-->0) {
					int[] cur = q.poll();
					for (int j=0; j<N; j++) {
						int val = bit[j]|cur[1];
						if (val==cur[1]) continue;
						if (adjM[cur[0]][j]==0) continue;
						if (visited[val][j]==0) {
							visited[val][j] = visited[cur[1]][cur[0]] + adjM[cur[0]][j];
							q.offer(new int[] {j, val});
						} else {
							visited[val][j] = Math.min(visited[val][j], visited[cur[1]][cur[0]] + adjM[cur[0]][j]);
						}
					}
				}
			}
			
//			System.out.println(Arrays.toString(visited[(1<<N)-1]));
			for (int i=0; i<N; i++) {
//				if (i==s) continue;
				if (adjM[i][s]==0) continue;
				if (visited[(1<<N)-1][i]==0) continue;
				min = Math.min(visited[(1<<N)-1][i]+adjM[i][s], min);
			}
		}
		System.out.println(min);
	}
}

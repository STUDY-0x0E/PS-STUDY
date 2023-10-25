import java.util.*;
import java.io.*;

/*
1. summary 
	총 F층의 건물에서 S층에서 G층으로 이동하려고 할 때, 최소 버튼 수 구하기
	이동할 수 없을 경우 "use the stairs" 출력
2. strategy : BFS 
3. note
	1 ≤ S, G ≤ F ≤ 1,000,000, 0 ≤ U, D ≤ 1,000,000
*/

public class BOJ5014 {

	static int visited[];
	static Queue<Integer> floor = new ArrayDeque<>(); 
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int F = Integer.parseInt(st.nextToken()); // 건물층 
		int S = Integer.parseInt(st.nextToken()); // 출발층
		int G = Integer.parseInt(st.nextToken()); // 목적층
		int U = Integer.parseInt(st.nextToken()); // 올라갈 때 이동 수
		int D = Integer.parseInt(st.nextToken()); // 내려갈 때 이동 수

		visited = new int[F+1];

		floor.offer(S);
		visited[S] = 1;
		
		int result = 0;
		while(!floor.isEmpty()) {
			int curFloor = floor.poll(); 
            
			if(curFloor == G) { // 목적층 도착
				result = visited[curFloor];
				break;
			}
			
			int upFloor = curFloor + U; 
			if(upFloor <= F) { // 건물 층 범위
				if(visited[upFloor] == 0) { // 미방문 층
					floor.offer(upFloor);
					visited[upFloor] = visited[curFloor] + 1;		
				}
			}
			
			int downFloor = curFloor - D;
			if(downFloor > 0) { // 건물 층 범위
				if(visited[downFloor] == 0) { // 미방문
					floor.offer(downFloor);
					visited[downFloor] = visited[curFloor] + 1;	
				}
			}		
		}
		
		if(result == 0) System.out.println("use the stairs");
		else System.out.println(result - 1);
	}
}
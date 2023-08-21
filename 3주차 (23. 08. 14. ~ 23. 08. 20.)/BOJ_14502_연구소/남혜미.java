import java.io.*;
import java.util.*;

public class BOJ14502 {
	
	static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 방향 벡터(우, 하, 좌, 상)
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	
	static int N, M; // 연구소 가로 세로 길이
	static int board[][], chooseVacant[] = new int[3];
	static boolean visit[];

	static List<Pair> emptyList = new ArrayList<>();
	static List<Pair> virusList = new ArrayList<>();
	
	static int emptyCnt = 0, maxEmptyCnt = 0;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력 데이터 저장하기
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 0) emptyList.add((new Pair(i,j))); // 순열시 사용
				else if(board[i][j] == 2) virusList.add((new Pair(i,j))); // bfs시 사용
			}
		}	
		
		visit = new boolean[emptyList.size()]; // 벽 선택할때 사용
		
		Perm(0);

		System.out.println(maxEmptyCnt);
		
		br.close();
	}
	
	// 벽 선택(0인 공간 중들 중에서 3개 선택, 순서 상관 X) : 조합
	public static void Perm(int cnt) { // 매개변수 : 벽 선택 개수
		if(cnt == 3) { // 공간 3개 선택 완료
			for(int i = 0; i < cnt; i++) // 선택된 공간 3곳에   
				board[emptyList.get(chooseVacant[i]).x][emptyList.get(chooseVacant[i]).y] = 1; // 벽 건설	
			
			virusSpread();
			
			for(int i = 0; i < cnt; i++) // 선택된 공간 3곳에  
				board[emptyList.get(chooseVacant[i]).x][emptyList.get(chooseVacant[i]).y] = 0; // 벽 철거

			return;
		}
		
		for(int i = 0; i < emptyList.size(); i++) {
			if(visit[i]) continue;
			
			visit[i] = true;
			chooseVacant[cnt] = i;
			Perm(cnt + 1);
			visit[i] = false;
		}
	}
	
	// 바이러스 확산 후 빈공간 찾는 함수
	public static void virusSpread() {
		
		// 원본 공간 배열 복사 
		int tmpBoard[][] = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
		
		// 바이러스 담는 큐
		Queue<Pair> queue = new ArrayDeque<>();
		for(Pair virus : virusList) {
			queue.offer(virus); // 이미 공간배열에 2로 저장되어 있기 때문에 따로 방문배열 사용 X
		}

		// 바이러스 확산
		while(!queue.isEmpty()) {
			Pair virus = queue.poll();
			
			for(int i = 0 ; i < 4; i++) { // 바이러스 상하좌우로 퍼지기
				int mx = virus.x + dx[i];
				int my = virus.y + dy[i];
				
				if(mx < 0 || mx >= N || my < 0 || my >= M) continue; // 공간 배열 범위 넘어가면 패스
				if(tmpBoard[mx][my] != 0) continue; // 벽이 아니면 패스(이미 바이러스가 퍼졌거나, 
				
				tmpBoard[mx][my] = 2; // 바이러스 기록 남기기
				queue.offer(new Pair(mx, my));
			}
		}
		
		emptyCnt = 0;
		// 바이러스 확산이 끝난 후 0인 공간 찾기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(tmpBoard[i][j] == 0) emptyCnt++;
			}
		}
		
		maxEmptyCnt = Math.max(emptyCnt, maxEmptyCnt); // 결과값이 더 크면 저장
	}
}
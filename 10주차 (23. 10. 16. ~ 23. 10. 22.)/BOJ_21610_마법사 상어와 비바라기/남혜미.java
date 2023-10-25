import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
1. summary 
	N * N 격자 크기의 각 칸마다 바구니가 하나 존재 
	바구니에 저장할 수 있는 물의 양 제한 X
	이동 한정으로 N번과 1번은 연결되어 있다. (1번 오른쪽 또는 왼쪽은 N번, N번 오른쪽 또는 아래쪽은 1번)
	
	비바라기 마법은 비구름 생성
	비바라기 시전 (N, 1), (N, 2), (N-1, 1), (N-1, 2) 비구름 생성
	
	M번 명령을 통해 구름 이동
	이동 명령
	1. 모든 구름이 di 방향으로 Si칸 이동
	2. 각 구름에서 비 내려서 바구니 물의 양 1 증가
	3. 구름 증발
	4. 2에서 물 증가한 칸(r,c)에서 물복사버그 마법 시전 
		대각선 방향으로 거리가 1인 칸에 물이 있는 바구니 수만큼 (r,c) 물 증가 (경계넘어가는 칸은 대각선 거리로 인정 X)
	5. 바구니에 저장된 물의 양이 2이상이 모든 칸에 구름 생성, 물의 양 2 감소 (이전에 구름이 생성됐던 칸은 제외)
	
	M번의 이동 후에 바구니에 들어있는 물의 양의 합 출력하기
2. strategy : 구현
3. note
	2 ≤ N ≤ 50
	1 ≤ M ≤ 100
	0 ≤ A[r][c] ≤ 100
	1 ≤ di ≤ 8
	1 ≤ si ≤ 50
*/

public class BOJ21610 {

	static int dir[][] = {{0, 0}, {0, -1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}}; // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	
	static int N, M;
	static int board[][]; // 바구니 정보
	static boolean preCloud[][]; // 이전 구름 위치 

	static Queue<int[]> cloudList = new ArrayDeque<>(); // 이동할 구름 위치
	static Queue<int[]> basketList = new ArrayDeque<>(); // 물 복사 바구니 위치
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 데이터 입력 받기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 이동 횟수
		
		board = new int[N+1][N+1]; // 1~N칸 초기 정보
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		init();
		
		for(int i = 1; i <= M; i++) { // M번 이동
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()); // 방향
			int s = Integer.parseInt(st.nextToken()); // 거리
			
			moveCloud(d, s); // 구름 이동 후 증발
			copyWater(); // 물 복사
			createCloud(); // 구름 생성
		}
		int result = waterCnt();
		
		System.out.println(result);
	}
	
	// 초기 구름 위치
	public static void init() {
		preCloud = new boolean[N+1][N+1];
		
		for(int i = N-1; i <= N; i++) {
			for(int j = 1; j <= 2; j++) {
				cloudList.offer(new int[] {i, j});
			}
		}
	}
	
	public static void moveCloud(int direction, int distance) {
		
		while(!cloudList.isEmpty()) {
			int cloud[] = cloudList.poll();
			
			for(int i = 0; i <= 1; i++) { // r좌표, c좌표
				for(int move = 0; move < (distance % N); move++) { // s 만큼 이동 (제자리로 돌아오는 만큼 제외)
					cloud[i] += dir[direction][i];
					
					if(cloud[i] == N+1) cloud[i] = 1;
					else if(cloud[i] == 0) cloud[i] = N;
				}
			}
			board[cloud[0]][cloud[1]]++; // 물의 양 1증가
			preCloud[cloud[0]][cloud[1]] = true; // 구름 증발
			
			basketList.offer(new int[] {cloud[0], cloud[1]}); // 물 증가한 바구니
		}
	}
	
	public static void copyWater() {
		
		while(!basketList.isEmpty()) {
			int basket[] = basketList.poll();

			int basketCnt = 0;
			for(int direction = 2; direction <= 8
					; direction+=2) { // 2, 4, 6, 8
				int mx = basket[0] + dir[direction][0];
				int my = basket[1] + dir[direction][1];
				
				if(mx < 1 || my < 1 || mx > N || my > N) continue; // 격자(1~N) 벗어남
				if(board[mx][my] == 0) continue; // 물 X
				
				basketCnt++;
			}
			board[basket[0]][basket[1]] += basketCnt; // 물 있는 바구니 수만큼 물 복사
		}
	}
	
	public static void createCloud() {
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(board[i][j] >= 2 && !preCloud[i][j]) { // 물의 양 2이상 + 이전에 구름 사라진 자리 X
					board[i][j] -= 2; // 물 2 증발
					cloudList.offer(new int[] {i, j}); // 구름 생성
				}
				preCloud[i][j] = false; // 초기화
			}
		}
	}
	
	public static int waterCnt() {
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				cnt += board[i][j];
			}
		}
		return cnt;
	}
}
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
1. summary : 아기 상어 (크기 2)
	수조 정보 0(빈공간), 1~8(물고기), 9(상어)
	상어는 상하좌우로 움직일 수 있다.
	1. 자기보다 작은 물고기만 먹을 수 있다. (자기 무게 수만큼 먹으면 크기가 커진다.)
	2. 가장 가까운 물고기를 먹는다. (위쪽 -> 왼쪽 -> 오른쪽 -> 아래쪽)
	3. 크기가 큰 물고기는 지나갈 수 없지만(벽), 크기가 같으면 지나갈 수 있다.
	더이상 먹을 수 있는 물고기가 없으면 종료한다.
2. strategy : BFS
	거리가 한칸씩 넓어지는 범위에 물고기가 있는지 체크한다.
	자신보다 작은 물고기가 있다면 멈춰서 먹는다.
		먹은 물고기 수를 확인한다. => 무게만큼 먹었다면 무게를 증가시킨다.
	더 이상 먹을 수 있는 물고기가 없다면 종료한다.
		물고기를 다 먹은 경우
		무게가 작은 물고기가 없는 경우
3. note
	 N(2 ≤ N ≤ 20) : N이 작은편에 속하기 때문에 완전 탐색 고려해볼만하다. 
	 최악의 탐색 20*20 = 400, 물고기가 최대 20*20 = 400  => 160000
*/
public class Main {

	// 상, 좌, 우, 하
	static int dir[][] = {{-1,0},{0,-1},{0,1},{1,0}};
	
	static int N;
	static int board[][];
	
	static Point shark;
	static int sharkSize = 2, eatFish = 0; 
	static int moveCnt = 0;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 데이터 입력
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 9) shark = new Point(i,j); // 상어 위치
			}
		}
		
		while(true) {
			if(!findFish()) break; // 더 이상 찾은 물고기가 없다면 종료
		}
		
		System.out.println(moveCnt);
	}
	
	private static boolean findFish() {
	
		int depthFlag = 0;
		int visit[][] = new int[N][N]; // 방문 배열
		
		// 잡아먹을 물고기 후보
		PriorityQueue<Point> fishList = new PriorityQueue<>(new Comparator<Point>(){
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.x == o2.x) {
					return o1.y - o2.y;
				}
				return o1.x - o2.x;
			}
		});
		Queue<Point> checkFish = new ArrayDeque<>(); // 상어가 이동할 위치 후보
		
		checkFish.offer(new Point(shark.x, shark.y)); // 상어 이동 시작
		visit[shark.x][shark.y] = 1;
		board[shark.x][shark.y] = 0;
		
		while(!checkFish.isEmpty()) {
			Point current = checkFish.poll();
			
			if(depthFlag == visit[current.x][current.y]) { // 물고기 발견시 depth 달라지면
				checkFish.clear(); // 추가 물고기 탐색 종료 
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int mx = current.x + dir[i][0];
				int my = current.y + dir[i][1];
				
				if(mx < 0 || mx >= N || my < 0 || my >= N) continue; // 영역 벗어남
				if(visit[mx][my] != 0) continue; // 이미 방문
				
				if(board[mx][my] != 0) { // 물고기 발견
					if(board[mx][my] > sharkSize) continue; // 물고기 > 상어 (벽)
					else if(board[mx][my] < sharkSize) { // 물고기 < 상어 
						fishList.offer(new Point(mx, my));
						
						if(depthFlag == 0) { // 물고기 처음 방문 (현재 depth 기록)
							depthFlag = visit[current.x][current.y] + 1;
						}
					}
					else ; // 물고기 = 상어
				}
				
				checkFish.offer(new Point(mx, my));
				visit[mx][my] = visit[current.x][current.y] + 1; // 방문처리 depth 계산
			}
		}
		
		
		if(fishList.isEmpty()) return false; // 찾은 물고기가 없다면 false (사냥 중단)
		else { // 찾은 물고기가 있다면
			
			board[shark.x][shark.y] = 0; // 원래 상어 위치 흔적 지우기

			shark = fishList.poll(); // x, y 위치가 가장 작은 물고기 선택
			board[shark.x][shark.y] = 9; // 상어 위치 이동

			moveCnt += (visit[shark.x][shark.y]-1); // 이동 횟수 체크
			
			eatFish++;
			if(eatFish == sharkSize) { // 크기만큼 먹을시
				sharkSize++; // 크키 증가
				eatFish = 0;
			}
			return true;
		}
	}
}
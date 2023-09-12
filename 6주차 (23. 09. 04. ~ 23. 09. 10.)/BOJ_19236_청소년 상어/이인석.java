import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    //윗 방향부터 45도씩 반시계 방향
    static int[][] dir = {
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}
    };

    static class Fish {
        int x;
        int y;
        int dir;

        public Fish(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
        
        @Override
        public String toString() {
        	return this.dir + "";
        }
    }

    static int n = 4;
    static int result = -1;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Fish[] fish = new Fish[n * n + 1];
        int[][] pool = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                pool[i][j] = num;
                fish[num] = new Fish(j, i, dir);
            }
        }
        
        // 물고기 번호는 1부터 => 0은 비어있는 칸
        // 상어는 0, 0에 위치
        Fish shark = new Fish(0, 0, fish[pool[0][0]].dir);
        
        int cnt = pool[0][0];
        fish[pool[0][0]] = null;
        pool[0][0] = 0;

        sharkMove(pool, fish, shark, cnt);
        
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void sharkMove(int[][] pool, Fish[] fish, Fish shark, int cnt) {
    	
    	result = Math.max(result, cnt);
    	
    	int[][] copyPool = new int[n][n];
    	Fish[] copyFish = new Fish[n * n + 1];
    	
    	// 사용할 맵 복사
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			copyPool[i][j] = pool[i][j]; 
    		}
    	}
    	
    	for (int i = 1; i <= n * n; i++) {
    		if (fish[i] == null) continue;
    		copyFish[i] = new Fish(fish[i].x, fish[i].y, fish[i].dir);
    	}
    	
    	// 상어 이동 전 물고기 이동
    	fishMove(copyPool, copyFish, shark);
    	
    	int nx = shark.x, ny = shark.y;
    	for (int i = 0; i < 4; i++) {
    		nx += dir[shark.dir][0];
    		ny += dir[shark.dir][1];
    		
    		if (nx < 0 || nx >= n || ny < 0 || ny >= n || copyPool[ny][nx] < 1) continue;
    		
    		int preyIdx = copyPool[ny][nx];
    		Fish prey = copyFish[preyIdx];
    		
    		copyPool[ny][nx] = 0;
    		copyFish[preyIdx] = null;
    		
    		sharkMove(copyPool, copyFish, new Fish(nx, ny, prey.dir), cnt + preyIdx);
    		
    		copyPool[ny][nx] = preyIdx;
    		copyFish[preyIdx] = prey;
    	}
    	
    }

    public static void fishMove(int[][] pool, Fish[] fish, Fish shark) {
    	
    	// 1번 물고기부터 움직임
    	for (int i = 1; i <= n * n; i++) {
    		if (fish[i] == null) continue;
    		
    		for (int j = 0; j < 8; j++) {
    			
    			int idx = (fish[i].dir + j) % 8;
    			int nx = fish[i].x + dir[idx][0], ny = fish[i].y + dir[idx][1];
    			
    			// 갈 수 없는 곳이거나 상어가 있는 곳이라면 물고기가 갈 수 없음
    			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
    			if (shark.x == nx && shark.y == ny) continue;
    			
    			int existIdx = pool[ny][nx];
    			pool[fish[i].y][fish[i].x] = existIdx;
    			
    			// 물고기가 비어있지 않다면 좌표 수정
    			if (existIdx > 0) {
    				fish[existIdx].x = fish[i].x;
    				fish[existIdx].y = fish[i].y;
    			}
    			
    			
    			pool[ny][nx] = i;
    			fish[i].x = nx;
    			fish[i].y = ny;
    			fish[i].dir = idx;
    			
    			// 이동에 성공했다면 다른 방향은 탐색하지 않음
    			break;
    		}
    	}
    }
}
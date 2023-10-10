ㅍimport java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark {
	int i;
	int j;
	int dir;
	
	public Shark(int i, int j, int dir) {
		this.i = i;
		this.j = j;
		this.dir = dir;
	}
}

class Smell {
	int shark;
	int kind;
	int left;
	int cand;
	
	public Smell(int shark, int kind, int left, int cand) {
		this.shark = shark;
		this.kind = kind;
		this.left = left;
		this.cand = cand;
	}
}

public class Main {
	
	static Smell board[][];
	static Shark sharks[];
	static int N, M, K, alive, priority[][][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new Smell[N][N];
		sharks = new Shark[M+1];
		alive = M;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp != 0) {
					board[i][j] = new Smell(tmp,tmp,K,0);
					sharks[tmp] = new Shark(i,j,0);
				}
				else board[i][j] = new Smell(0,0,0,0);
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			int dir = Integer.parseInt(st.nextToken());
			sharks[i].dir = dir - 1; 
		}
		
		priority = new int[M+1][4][4];
		for (int i = 1; i <= M; i++) {			
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int k = 0; k < 4; k++) {
					int pdir = Integer.parseInt(st.nextToken());
					priority[i][j][k] = pdir - 1;					
				}
			}
		}
		
		sharkMove(0);
	}

	private static void sharkMove(int cnt) {
		
		if (cnt > 1000) {
			System.out.println(-1);
			return;
		}else if (alive == 1) {
			System.out.println(cnt);
			return;
		}		
		
		for (int i = 1; i <= M; i++) {
			if (sharks[i].dir == -1) continue;
			
			int x = sharks[i].i;
			int y = sharks[i].j;
			
			int ni = -1, nj = -1, ndir = -1, ci = -1, cj = -1, cdir = -1;
			boolean flag = false;
			for (int j = 0; j < 4; j++) {
				ndir = priority[i][sharks[i].dir][j];
				ni = x + dx[ndir];
				nj = y + dy[ndir];
				
				if (inRange(ni,nj)) {
					if (board[ni][nj].left == 0) {
						flag = true;
						break;
					}
					else if (board[ni][nj].kind == i && cdir == -1) {
						ci = ni;
						cj = nj;
						cdir = ndir;
					}
				}
			}
			
			if (flag) {
				if (board[ni][nj].cand != 0) {
					alive--;
					sharks[i].dir = -1;
				}
				else {			
					sharks[i] = new Shark(ni, nj, ndir);
					board[ni][nj].cand = i;
					board[ni][nj].kind = i;
				}
				
			}else {
				sharks[i] = new Shark(ci, cj, cdir);
				board[ci][cj].cand = i;
			}
		}
		
		smellDesc();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j].shark != 0) board[i][j].shark = 0;
				else if (board[i][j].cand != 0) {
					board[i][j] = new Smell(board[i][j].cand, board[i][j].cand, K, 0);
				}
			}
		}
		
		sharkMove(cnt+1);
	}

	private static void smellDesc() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j].left > 0) {
					if (--board[i][j].left == 0) board[i][j].kind = 0;
				}
			}
		}
	}

	private static boolean inRange(int ni, int nj) {
		if (0<=ni && ni<N && 0<=nj && nj<N) return true;
		return false;
	}

}
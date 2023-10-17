import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class Tree {
		int age;
		Tree prev;
		Tree next;
		
		public Tree(int age, Tree prev, Tree next) {
			this.age = age;
			this.prev = prev;
			this.next = next;
		}
		
		public void eat(int i, int j) {
			boolean flag = false;
			
			for (Tree t = this; t != null; t = t.next) {
				if (curA[i][j] >= t.age && !flag) {
					curA[i][j] -= t.age++;
				}else {
					dead[i][j] += t.age/2;
					if (t.prev != null) t.prev.next = null;
					else tree[i][j] = null;
					flag = true;
				}
			}
		}

		public void spread(int i, int j) {
			for (Tree t = this; t != null; t = t.next) {
				if (t.age % 5 == 0 && t.age > 0) {
					for (int k = 0; k < 8; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						if (!inRange(nx, ny)) continue;
						
						tree[nx][ny] = new Tree(1, null, tree[nx][ny]);
						if (tree[nx][ny].next != null) tree[nx][ny].next.prev = tree[nx][ny];
					}
				}
			}
			
		}
	}
	
	static int N, A[][], curA[][], dead[][];
	static Tree tree[][];
	static int dx[] = {-1,-1,-1,0,0,1,1,1};
	static int dy[] = {-1,0,1,-1,1,-1,0,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		A = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		curA = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) Arrays.fill(curA[i], 5);
		
		tree = new Tree[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			tree[x][y] = new Tree(age, null, tree[x][y]);
		}

		int year = 0;
		while (year++ < K) {
			dead = new int[N+1][N+1];
			spring();
			summer();
			autumn();
			winter();
		}
		
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (Tree t = tree[i][j]; t != null; t = t.next) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}

	private static void spring() {		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {	
				if (tree[i][j] == null) continue;
				tree[i][j].eat(i,j);
			}
		}
	}

	private static void summer() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				curA[i][j] += dead[i][j];
			}
		}
	}

	private static void autumn() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (tree[i][j] == null) continue;
				tree[i][j].spread(i,j);
			}
		}
	}

	private static void winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				curA[i][j] += A[i][j];
			}
		}
	}

	private static boolean inRange(int nx, int ny) {
		if (0<nx && nx<=N && 0<ny && ny<=N) return true;
		return false;
	}
}
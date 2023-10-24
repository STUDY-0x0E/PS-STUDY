import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, board[][];
	static ArrayList<int[]> clouds;
	static boolean isCloud[][];
	static int dx[] = {0,-1,-1,-1,0,1,1,1};
	static int dy[] = {-1,-1,0,1,1,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		clouds = new ArrayList<>();
		isCloud = new boolean[N][N];
		
		for (int i = N-2; i <= N-1; i++) {
			for (int j = 0; j <= 1; j++) {
				clouds.add(new int[] {i,j});
			}
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			
			move(d,s);
			rain();
			waterCopy();
			cloud();
		}
		
		int answer = sum();
		System.out.println(answer);
	}

	private static void move(int d, int s) {
		isCloud = new boolean[N][N];
		ArrayList<int[]> tmpCloud = new ArrayList<>();
		
		int x = dx[d] * s;
		int y = dy[d] * s;
		
		for (int[] cloud : clouds) {
			int nx = (cloud[0] + x) % N;
			int ny = (cloud[1] + y) % N;

			if (nx < 0) nx += N;
			if (ny < 0) ny += N;

			tmpCloud.add(new int[] {nx, ny});
			isCloud[nx][ny] = true;
		}
		
		clouds = tmpCloud;
	}

	private static void rain() {
		for (int[] cloud : clouds) {
			board[cloud[0]][cloud[1]]++;
		}
	}

	private static void waterCopy() {
		for (int[] cloud : clouds) {
			for (int i = 1; i < 8; i += 2) {
				int nx = cloud[0] + dx[i];
				int ny = cloud[1] + dy[i];
				
				if (inRange(nx,ny) && board[nx][ny] > 0) 
					board[cloud[0]][cloud[1]]++;
			}
		}
	}

	private static void cloud() {
		clouds.clear();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!isCloud[i][j] && board[i][j] > 1) {
					clouds.add(new int[] {i,j});
					board[i][j] -= 2;
				}
			}
		}
	}
	
	private static int sum() {
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer += board[i][j];
			}
		}
		return answer;
	}
	
	private static boolean inRange(int nx, int ny) {
		if (0<=nx && nx<N && 0<=ny && ny<N) return true;
		return false;
	}
	
}
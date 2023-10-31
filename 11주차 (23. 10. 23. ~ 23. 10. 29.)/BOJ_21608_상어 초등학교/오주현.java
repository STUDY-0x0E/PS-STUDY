import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, list[][], board[][];
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};

	static class candidate implements Comparable<candidate>{
		int i, j, friends, blank;

		public candidate(int i, int j, int friends, int blank) {
			this.i = i;
			this.j = j;
			this.friends = friends;
			this.blank = blank;
		}

		@Override
		public int compareTo(candidate other) {
			if (this.friends == other.friends) {
				if (this.blank == other.blank) {
					if (this.i == other.i) {
						return this.j - other.j;
					}
					return this.i - other.i;
				}
				return other.blank - this.blank;
			}
			return other.friends - this.friends;
		}		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int num = N * N;
		list = new int[num+1][4];
		board = new int [N][N];
		StringTokenizer st;
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int stud = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) list[stud][j] = Integer.parseInt(st.nextToken());
			
			setLocation(stud);
		}
		
		int answer = getTotal(); 		
		System.out.println(answer);
	}
	
	public static void setLocation(int stud) {
		PriorityQueue<candidate> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 0) continue;
				
				int friends = 0;
				int blank = 0;				
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if (inRange(nx, ny)) {
						if (board[nx][ny] == 0) {
							blank++;
							continue;
						}else {
							for (int a = 0; a < 4; a++) {
								if (board[nx][ny] == list[stud][a]) friends++;
							}
						}
					}
				}
				pq.add(new candidate(i, j, friends, blank));				
			}
		}
		
		candidate loc = pq.poll();
		board[loc.i][loc.j] = stud;
	}
	
	public static int getTotal() {
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int sum = -1;
				
				int stud = board[i][j];
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if (inRange(nx, ny)) {
						for (int a = 0; a < 4; a++) {
							if (list[stud][a] == board[nx][ny]) sum++;
						}
					}
				}
				answer += Math.pow(10, sum);
			}
		}
		return answer;
	}
	
	public static boolean inRange(int nx, int ny) {
		if (0<=nx && nx<N && 0<=ny && ny<N) return true;
		return false;
	}
}
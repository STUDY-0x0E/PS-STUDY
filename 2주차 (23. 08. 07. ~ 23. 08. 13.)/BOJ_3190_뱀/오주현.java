package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class ChangeDir {
	int time;
	char dir;
	
	public ChangeDir(int time, char dir) {
		this.time = time;
		this.dir = dir;
	}
}

public class 오주현 {

	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static LinkedList<int[]> snake = new LinkedList<>();
	static Queue<ChangeDir> move = new LinkedList<>();
	static int N, count = 0;
	static boolean[][] apple;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		apple = new boolean[N][N];
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			apple[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]= true;	
		}
		
		int L =  Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			move.add(new ChangeDir(Integer.parseInt(st2.nextToken()), st2.nextToken().charAt(0)));
		}
		
		snake.offer(new int[]{0,0});
		game_start(0,0);
		
		System.out.println(count);

	}
	
	public static void game_start(int a, int b) {
		int xindex = 0; 	
		int yindex = 0;
		
		while (true) {
			if (!move.isEmpty() && count==move.peek().time)
				if (move.poll().dir == 'L') {
					xindex= xindex-1<0? 3 : xindex-1;
					yindex= xindex;
				}
				else {
					xindex= (xindex+1)%4;
					yindex= xindex;
				}
			
			count++;
			
			a += dx[xindex];
			b += dy[yindex];
			if (isDead(a,b))
				break;			
			
			if (apple[a][b]) {
				snake.offer(new int[]{a,b});
				apple[a][b]= false;
			}else {
				snake.offer(new int[]{a,b});
				snake.poll();
			}
		}
		
	}
	
	public static boolean isDead(int a, int b) {
		if (N<=a || a<0 || N<=b || b<0 || snake.isEmpty()) return true;
		for (int i = 0; i < snake.size(); i++) {
			if (snake.get(i)[0]==a && snake.get(i)[1]==b) return true;
		}
		return false;
	}

}

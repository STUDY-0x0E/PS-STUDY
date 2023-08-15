import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		int n = Integer.parseInt(br.readLine());// 보드 크기
		int k = Integer.parseInt(br.readLine());// 사과 위치
		List<int[]> apple = new ArrayList<int[]>();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			apple.add(new int[] {Integer.parseInt(st.nextToken())-1,
				Integer.parseInt(st.nextToken())-1});
		}

		int l = Integer.parseInt(br.readLine());// 뱀의 방향
		String[][] move = new String[l][2];
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			move[i][0] = st.nextToken();
			move[i][1] = st.nextToken();
		}

		// calc
		int ans = 0;// 지난 시간
		List<int[]> queue = new LinkedList<>();// 뱀

		int[] p = { 0, 0 }; // 위치 초기화
		queue.add(new int[] {0,0});
		int d = 3;// direction 초기화
		boolean repeat = true;
		int d_done = 0;//방향전환한 횟수
		
		int[] dx = { -1, 1, 0, 0 };// 0상1하2좌3우
		int[] dy = { 0, 0, -1, 1 };
		while (repeat) {
			ans++;
			p[0] += dx[d];
			p[1] += dy[d];
			
			// 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
			if (p[0] < 0 || p[0] == n || p[1] < 0 || p[1] == n)// 벽
				repeat=false;
			for(int i =0; i<queue.size();i++) { //몸
				if(queue.get(i)[0]==p[0]&&queue.get(i)[1]==p[1])
					repeat=false;
			}
			
			queue.add(new int[] {p[0],p[1]});
			
			// 사과먹기
			boolean s = true;
			for (int a =0; a<apple.size();a++) {
				if(apple.get(a)[0]==p[0]&&apple.get(a)[1]==p[1]) {
					apple.remove(a);
					s=false;
					break;
				} 
			}
			if(s)
				queue.remove(0);
			
			// 방향전환
			if(d_done<l && ans == Integer.parseInt(move[d_done][0])){
				if(move[d_done][1].equals("D")) {// D:오른쪽
					if(d==0) d=3;
					else if(d==1) d=2;
					else if(d==2) d=0;
					else d=1;
				} else { // C:왼쪽
					if(d==0) d=2;
					else if(d==1) d=3;
					else if(d==2) d=1;
					else d=0;
				}
				d_done++;
			}
		}

		// output
		System.out.println(ans);
	}
}
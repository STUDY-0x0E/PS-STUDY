package week2;
/* 1. 1번 칸 : 로봇 올리기  N번 칸 : 로봇 내리기
 * 2. 로봇 내리는 위치에 도달 시 즉시 내림
 * 3. 로봇 올리거나 움직이면 해당 칸 내구도 -1 (올리는 위치 내구도 0이면 못 올림)
 * 순서 : 벨트 한 칸씩 회전 -> 가장 먼저 올라간 로봇부터 가능하다면 한 칸씩 이동 (조건: 내구도 1이상, 앞에 로봇x)
 *       -> 내구도 0인 칸 K개 이상일 시 종료
 * 요약하자면 회전 후, 로봇 이동 후, 올리기
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 오주현 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] belt = new int[2*N];       // 벨트 내구도 배열
		int belt1 = 0; int belt2 = N;       // 로봇 올리는 위치
		
		boolean[] robot = new boolean[N];      // on~off 범위의 벨트 위에 올라가 있는 로봇 배열                                   // 로봇에 올라간 순서 부여하여 active에 add
		
		st= new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 2*N; i++) {
			belt[i]= Integer.parseInt(st.nextToken());
		}
		
		int count = 0;      // 로봇이 올라가거나 움직여서 0이 되었을 때 count+1
		int stage = 0;      // 몇 단계?
		
		while (count < K) {        // 내구도 0인 벨트가 K개 이상일 때까지 반복 실행
			stage++;               // stage마다 +1
			
			// 1. 컨테이너 이동 - 배열은 고정하고 포인터만 움직이므로 belt와 robot 배열을 바꿀 필요 x
			belt1 = (belt1+ 2*N-1) % (2*N);          // 벨트는 오른쪽으로 움직이므로 배열이 고정되어 있다면
			belt2 = (belt2+ 2*N-1) % (2*N);        // 로봇 올리는 위치와 내리는 위치는 상대적으로 왼쪽으로 움직이게 됨
			
			for (int i = N-2; i > 0; i--) {    // 로봇도 한칸 옮기기
				robot[i]= robot[i-1];
			}
			robot[0]= false;                   // 올리는 위치와 내리는 위치엔 로봇이 있을 수 없으므로 false
			robot[N-1]= false;
			
			// 2. 로봇 이동
			for (int i = N-2; i >= 0 ; i--) {       // 먼저 올라간 로봇부터 -> 내리는 위치에 가까운 로봇
				if (robot[i]) {                   
					int next = (belt1+i+1) % (2*N);           
					
					if (!robot[i+1] && belt[next]>0) {     // 앞 칸에 로봇이 없고 내구도가 1 이상일 때 한 칸 이동
						robot[i]= false;                   // 원래 위치는 비워두기
						if(--belt[next] == 0)              // 움직여서 내구도가 0이 되면 count+1
							count++;							
						
						if (next == belt2) {               // 앞으로 간 칸이 내리는 위치면 즉시 내림
							break;
						}
						robot[i+1]= true;                  // robot에 로봇 위치 갱신
					}
				}
				
			}
			
			// 3. 로봇 올리기
			if (!robot[0] && belt[belt1]>0) {                    // 로봇 올리는 위치에 로봇이 없고 내구도가 0이상일 때
				robot[0]= true;                                   // 로봇 올리기
				if (--belt[belt1] == 0)                           // 로봇 올려서 내구도가 0이 되어도 count+1
					count++;
			}
		}
		
		System.out.println(stage);
	}

}

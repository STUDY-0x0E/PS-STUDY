import java.util.*;
import java.io.*;

/*
1. summary : 예산 분배
	정해진 금액 이하에서 최대의 예산 구하기
	- 상한가 이상인 경우 상한가 만큼 분배
	- 상한가 이하인 경우 요청한 금액 분배
2. strategy : 이분 탐색
	- 이분 탐색을 통해서 상한가 찾기 : checkSumCost
	- 상한가 내에서 최대 금액 찾기 : findMaxCost
3. note
	 3 <= N(지방 수) <= 10,000
	 1 <= C(요청 금액) <= 100,000
	 N <= M(총 예산) <= 1,000,000,000
*/

public class BOJ2512 {

	static int N, M, costList[]; 

	static int resultCost;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 입력 데이터 저장
		N = Integer.parseInt(br.readLine()); // 지방 수
		costList = new int[N];
		
		st = new StringTokenizer(br.readLine()); 
		for(int i = 0; i < N; i++) { // 지방 요청 금액
			costList[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine()); // 총 예산
	
		// 최대 상한가 찾기 (이분 탐색)
		int start = 0;
		int end = M; 
		while(start + 1 < end) {
			int mid = (start + end) / 2;
			if(checkSumCost(mid)) { // 상한가 증가
				start = mid;
			}
			else { // 상한가 감소
				end = mid;
			}
		}

		int maxCost = findMaxCost(start); 
		
		// 결과 데이터 출력하기
		System.out.println(maxCost);
	}
	
	static boolean checkSumCost(int mid) {
		long sum = 0;
		for(int i = 0; i < N; i++) {
			if(costList[i] > mid) sum += mid; // 상한가 초과 (상한 금액)
			else sum += costList[i]; // 상한가 미만 (요청 금액)
		}
		if(sum > M) return false; // 예산금 초과
		return true; // 예산금 충족
	}
	
	static int findMaxCost(int cost) { // 배정 예산금 중 최대값 구하기
		
		int maxCost = 0;
		
		for(int i = 0; i < N; i++) {
			if(costList[i] > cost) {
				maxCost = cost; // 상한가보다 높은 금액인 경우 상한가가 무조건 최대 금액
				break;
			}
			maxCost = Math.max(maxCost, costList[i]);
		}
		return maxCost;
	}
}

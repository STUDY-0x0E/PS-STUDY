import java.util.*;
import java.io.*;

/*
1. summary : 카드 정렬하기
	두 장의 카드를 묶을 때마다 카드 크기만큼 비용이 발생한다.
	카드를 묶을 때 발생하는 최소 비용 구하기
2. strategy
	카드를 묶을 때 비용이 작은 카드들을 선택하는게 최소 비용이 발생한다.
	묶음을 이룬 카드를 추가로 다시 정렬해야 함으로 우선순위 큐 사용
3. note
	1 ≤ N ≤ 100,000
*/

public class BOJ1715 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력 데이터 저장
		int N = Integer.parseInt(br.readLine()); // 카드 수
		PriorityQueue<Integer> card = new PriorityQueue<>();
		
		int num = 0;
		for(int i = 0; i < N; i++) { // 카드 크기
			num = Integer.parseInt(br.readLine());
			card.offer(num);
		}
		
		int result = 0;
		int cardOne, cardTwo = 0;
		while(card.size() > 1) { // 카드 개수가 1개가 될 때까지 반복
			cardOne = card.poll();
			cardTwo = card.poll();
			
			result += (cardOne + cardTwo);
			card.offer(cardOne + cardTwo);
		}
		
		System.out.println(result);
	}
}

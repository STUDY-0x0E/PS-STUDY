import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);// 집의 개수 N
		int C = Integer.parseInt(input[1]);// 공유기 개수 C

		int[] houses = new int[N];
		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(br.readLine());// 집의 좌표
		}

		Arrays.sort(houses);

		int start = 1;// 이분 탐색
		int end = houses[N - 1] - houses[0];

		int result = 0;// 인접한 두 공유기 사이의 최대 거리

		while (start <= end) {
			int mid = (start + end) / 2;
			int count = 1;// 설치한 공유기 수
			int prevHouse = houses[0];

			for (int i = 1; i < N; i++) {
				if (houses[i] - prevHouse >= mid) {// 공유기 설치 가능하다면
					count++;// 공유기 설치
					prevHouse = houses[i];// 갱신
				}
			}

			if (count >= C) {// 공유기 설치 개수가 목표보다 크거나 같은 경우, 거리 늘림
				result = mid;
				start = mid + 1;
			} else {// 공유기 설치 개수가 목표보다 작은 경우, 거리를 줄임
				end = mid - 1;
			}
		}

		System.out.println(result);
	}
}

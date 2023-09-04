import java.util.*;
import java.io.*;

public class BOJ1939 {

	private static class City {
		int name;
		int weight;
		
		public City(int name, int weight) {
			super();
			this.name = name;
			this.weight = weight;
		}
	}
	
	static int N, M, departCity, arriveCity;
	static List<City> cityList[];
	
	static int start = Integer.MAX_VALUE, end = 0;
	static int standardWeight = 0;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cityList = new ArrayList[N+1]; // 도시번호 1~N
		for(int i = 0; i <= N; i++) {
			cityList[i] = new ArrayList<City>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int city1 = Integer.parseInt(st.nextToken());
			int city2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			cityList[city1].add(new City(city2, weight));
			cityList[city2].add(new City(city1, weight));
			
			end = Math.max(weight, end);
			start = Math.min(weight, start);
		}
		
		st = new StringTokenizer(br.readLine());
		departCity = Integer.parseInt(st.nextToken());
		arriveCity = Integer.parseInt(st.nextToken());
		
		// result = start;
		while(end >= start) {
			chooseStandardWeight();
		}
		
		System.out.println(result);
	}
	
	private static void chooseStandardWeight() {
		
		standardWeight = (start + end) / 2;
		
		// System.out.println(standardWeight);
		
		if(findRoute()) { // 경로 탐색 가능 (기준 무게 증가)
			result = standardWeight;
			start = standardWeight + 1;
		}
		else { // 경로 탐색 실패 (기준 무게 감소)
			end = standardWeight - 1;
		}
	}

	private static boolean findRoute() {
		
		boolean flag = false;
		boolean visit[] = new boolean[N+1]; // 방문체크 배열
		
		Queue<Integer> nextCity = new ArrayDeque<>();
		nextCity.offer(departCity);
		
		visit[departCity] = true; // 방문 체크
		
		while(!nextCity.isEmpty()) {
			int current = nextCity.poll();
			
			for(int i = 0; i < cityList[current].size(); i++) {
				
				City next = cityList[current].get(i);
				
				if(visit[next.name]) continue; // 이미 방문한 도시 
				if(next.weight < standardWeight) continue; // 기준 무게 넘어서면 패스 (벽)
				
				visit[next.name] = true;
				nextCity.offer(next.name);
				
				if(next.name == arriveCity) { // 도착 도시 도착시 길 탐색 종료
					flag = true;
					nextCity.getClass();
					break;
				}
			}
		}
		// System.out.println(flag);
		return flag;
	}
}

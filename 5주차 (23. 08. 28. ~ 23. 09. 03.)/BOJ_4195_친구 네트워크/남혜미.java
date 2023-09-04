```java
import java.util.*;
import java.io.*;

public class BOJ4195 {

	static HashMap<String, Integer> nameList;
	static int parents[][];
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int cnt = 1;
			int F = Integer.parseInt(br.readLine());
			nameList = new HashMap<>();
			parents = new int[F*2+1][2];
			for(int friend = 0; friend < F; friend++) { 
				StringTokenizer st = new StringTokenizer(br.readLine());
				String name1 = st.nextToken(); // 친구 관계인 이름1
				String name2 = st.nextToken(); // 친구 관계인 이름2
				
				make(name1, cnt++); // 네트워크 생성
				make(name2, cnt++); // 네트워크 생성
				
				int result = union(name1, name2); // 네트워크 연결하기
				sb.append(result + "\n");
 			}
		}
		System.out.println(sb);
	}
	
	public static void make(String name, int cnt) {
		if(!nameList.containsKey(name)) {  // nameList에 name 없으면  추가
			parents[cnt][1] = 1; // 네트워크 크기
			parents[cnt][0] = cnt; // 부모 배열에 자기 자신 번호
			nameList.put(name, cnt); // 키 값에 부모 배열 인덱스 값
		}
	}
	
	public static int find(int idx) {
		if(idx == parents[idx][0]) return idx; // 현재가 부모인 경우

		return parents[idx][0] = find(parents[idx][0]); // 부모 찾아가기 (경로 압축하기)
	}
	
	public static int union(String name1, String name2) {
		
		int name1Idx = nameList.get(name1);
		int name2Idx = nameList.get(name2);
		
		int name1Root = find(name1Idx);
		int name2Root = find(name2Idx);
		
		if(parents[name1Root][0] == parents[name2Root][0]) return parents[name1Root][1];
	
		parents[name2Root][0] = name1Root; 
		parents[name1Root][1] += parents[name2Root][1]; 
		
		return parents[name1Root][1];
	}
}
```

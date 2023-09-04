package algo0904;

/*
1.summary:
테스트케이스 T를 입력받고 각 테스트케이스에서 문제를 해결한다.
간선의 개수 M을 입력받는다.
다음 M줄에 걸쳐 연결된 두 문자열을 입력받고 입력시점에서 두 문자열과 연결된 문자열의 개수를 출력한다.

2.strategy:
union-find를 이용한다. union시 네트워크의 수를 더하는 연산을 통해 연결된 네트워크의 크기를 구한다.

3.note:
hash를 사용할 경우 시간초과나 메모리 초과에 유의한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class BOJ4195 {
	
	static class node{
		String parent;
		int nChilds;
		public node(String parent, int nChilds) {
			super();
			this.parent = parent;
			this.nChilds = nChilds;
		}
		
	}
	
	static String find(LinkedHashMap<String, node> parents, String A) {
		if (parents.get(A).parent==A) return A;
		return parents.get(A).parent = find(parents, parents.get(A).parent);
	}
	
	static boolean union(LinkedHashMap<String, node> parents, String A, String B) {
		String rootA = find(parents, A);
		String rootB = find(parents, B);
		if (rootA==rootB) return false;
		parents.get(rootA).nChilds += parents.get(rootB).nChilds;
		parents.get(rootB).parent = rootA;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for (int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			LinkedHashMap<String, node> parents = new LinkedHashMap<String, node>();
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				String A = st.nextToken();
				String B = st.nextToken();
				if (!parents.containsKey(A)) {
					parents.put(A, new node(A, 1));
				}
				if (!parents.containsKey(B)) {
					parents.put(B, new node(B, 1));
				}
				
				union(parents, A, B);
				
				sb.append(parents.get(find(parents,A)).nChilds).append("\n");
			}
		}
		System.out.println(sb);
	}

}
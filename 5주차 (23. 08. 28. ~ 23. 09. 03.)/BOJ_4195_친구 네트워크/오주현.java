package unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class JUN4195_FriendNetwork {
	
	static ArrayList<String> friends;
	static HashMap<String, Integer> fidx; 
	static int[][] parents;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < tc; i++) {
			int F = Integer.parseInt(br.readLine());
			
			fidx = new HashMap<>();
			friends = new ArrayList<>();
			friends.add("");
			String[][] command = new String[F][2];
			
			int idx = 1;
			for (int j = 0; j < F; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				String f1 = st.nextToken();
				String f2 = st.nextToken();
				command[j]= new String[] {f1,f2};
				
				if (!fidx.containsKey(f1)) {
					fidx.put(f1, idx++);
					friends.add(f1);
				}
				if (!fidx.containsKey(f2)) {
					fidx.put(f2, idx++);
					friends.add(f2);					
				}
			}
			
			parents = new int[idx][2];
			for (int j = 1; j < idx; j++) {
				parents[j]= new int[] {j,1};
			}
					
			for (int j = 0; j < F; j++) {
				sb.append(union(fidx.get(command[j][0]),fidx.get(command[j][1])));
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	private static int union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) return parents[bRoot][1];
		
		parents[aRoot][0]= bRoot;
		parents[bRoot][1]+= parents[aRoot][1];
		return parents[bRoot][1];
		
	}

	public static int find(int a) {
		if (parents[a][0] == a) return a;
		
		return parents[a][0] = find(parents[a][0]);
	}

}

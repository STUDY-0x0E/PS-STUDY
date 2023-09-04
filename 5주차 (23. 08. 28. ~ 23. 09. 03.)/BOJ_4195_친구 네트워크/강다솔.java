package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_4195 {

	private static Map<String, String> parents;
	private static Map<String, Integer> relation;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		relation = new HashMap<>();
		for (int t = 0; t < T; t++) {
			parents = new HashMap<>();
			int F = Integer.parseInt(br.readLine());
			for (int i = 0; i < F; i++) {
				String[] friends = br.readLine().split(" ");
				union(friends[0], friends[1]);
				sb.append(getNetwork(friends[0])).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static int getNetwork(String n) {
		String p = find(n);
		return relation.get(p);
	}

	private static String find(String name) {
		if (!parents.containsKey(name)) {
			relation.put(name, 1);
			parents.put(name, name);
		}
		if (name.equals(parents.get(name)))
			return name;
		parents.put(name, find(parents.get(name)));
		return parents.get(name);
	}

	private static boolean union(String f1, String f2) {
		String p1 = find(f1);
		String p2 = find(f2);
		if (p1.equals(p2))
			return false;
		parents.put(p2, p1);
		relation.put(p1, relation.get(p1)+relation.get(p2));
		return true;
	}

}

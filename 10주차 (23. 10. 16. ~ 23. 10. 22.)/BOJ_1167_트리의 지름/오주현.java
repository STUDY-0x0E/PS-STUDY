import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<int[]>[] link;
	static int V, farnode, max= 0;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		
		link = new ArrayList[V+1];
		for (int i = 1; i < V+1; i++) link[i]= new ArrayList<>();
		
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			int next;
			while ((next= Integer.parseInt(st.nextToken())) != -1) {
				link[node].add(new int[] {next, Integer.parseInt(st.nextToken())});
			}
		}
		
		visited= new boolean[V+1];
		DFS(1,1,0);
		visited= new boolean[V+1];
		DFS(farnode,1,0);
		System.out.println(max);

	}
	
	public static void DFS (int node, int cnt, int sum) {
		if (max < sum) {
			farnode= node;
			max= sum;
		}
		
		visited[node]= true;
		
		for (int i = 0; i < link[node].size(); i++) {
			int n = link[node].get(i)[0];
			if (visited[n]) continue;
			DFS(n, cnt+1, sum+link[node].get(i)[1]);
		}
	}
}
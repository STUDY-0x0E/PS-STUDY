import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    static int[] parents;

    static class Bridge {
        int start;
        int end;
        int weight;

        public Bridge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Queue<Bridge> pq = new PriorityQueue<>((o1, o2) -> o2.weight - o1.weight);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.offer(new Bridge(start, end, weight));
        }

        parents = new int[n + 1];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int weight = Integer.MAX_VALUE;

        while (find(end) != find(start)) {
            Bridge node = pq.poll();
            if (union(node.start, node.end)) {
                weight = Math.min(weight, node.weight);
            }
        }

        bw.write(weight + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int find(int node) {
        if (node == parents[node]) return node;

        return parents[node] = find(parents[node]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
}
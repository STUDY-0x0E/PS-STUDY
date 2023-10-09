import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {

            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] wantMember = new int[n];
            for (int i = 0; i < n; i++) {
                wantMember[i] = Integer.parseInt(st.nextToken()) - 1;
            }

            int answer = 0;
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (visited[i]) continue;
                
                queue.add(i);
                visited[i] = true;
                
                int idx = wantMember[i];
                while (!visited[idx]) {
                	queue.add(idx);
                	visited[idx] = true;
                	idx = wantMember[idx];
                }
                
                int curr = -1;
                while (!queue.isEmpty() && (curr = queue.poll()) != idx) {
                	++answer;
                }
                
                queue.clear();
            }

            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
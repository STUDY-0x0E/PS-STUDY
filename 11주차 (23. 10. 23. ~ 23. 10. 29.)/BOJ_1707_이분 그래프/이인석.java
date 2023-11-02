import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < k; tc++) {
            String[] input = br.readLine().split(" ");

            int v = Integer.parseInt(input[0]);
            int e = Integer.parseInt(input[1]);

            ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
            for (int i = 0; i <= v; i++) {
                edge.add(new ArrayList<>());
            }
            for (int i = 0; i < e; i++) {
                input = br.readLine().split(" ");

                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);

                edge.get(a).add(b);
                edge.get(b).add(a);
            }

            int[] visited = new int[v + 1];
            Arrays.fill(visited, 0);
            boolean flag = true;

            for (int t = 1; t <= v; t++) {

                if (visited[t] != 0) continue;
                visited[t] = 1;

                Queue<Integer> queue = new LinkedList<>();
                queue.add(t);
                while (!queue.isEmpty()) {
                    Integer curr = queue.poll();

                    for (Integer i : edge.get(curr)) {

                        if (visited[i] == visited[curr]) {
                            flag = false;
                            break;
                        }

                        if (visited[i] != 0) continue;

                        visited[i] = visited[curr] * -1;
                        queue.add(i);
                    }

                    if (!flag) break;
                }

                if (!flag) break;
            }

            bw.write(flag ? "YES\n" : "NO\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
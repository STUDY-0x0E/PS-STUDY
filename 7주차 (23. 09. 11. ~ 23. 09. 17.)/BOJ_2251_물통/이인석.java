import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이인석 {

    static BufferedReader br;
    static BufferedWriter bw;

    // 0 : a, 1 : b, 2 : c
    static int[] maxAmount;
    static int[][] bottleCase = {
            {0, 1, 2}, {1, 0, 2},
            {1, 2, 0}, {2, 1, 0},
            {0, 2, 1}, {2, 0, 1}
    };

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        maxAmount = new int[3];
        for (int i = 0; i < 3; i++) {
            maxAmount[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] stateMap = new boolean[maxAmount[0] + 1][maxAmount[1] + 1];
        Queue<Integer> pq = new PriorityQueue<>();
        Queue<Integer[]> queue = new LinkedList<>();

        Integer[] tmp = {0, 0, maxAmount[2]};
        queue.offer(tmp);
        stateMap[0][0] = true;

        while (!queue.isEmpty()) {

            Integer[] curr = queue.poll();
            if (curr[0] == 0) pq.offer(curr[2]);

            for (int i = 0; i < 6; i++) {
                Integer[] result = move(bottleCase[i][0], bottleCase[i][1], bottleCase[i][2], curr);

                if (stateMap[result[0]][result[1]]) continue;
                stateMap[result[0]][result[1]] = true;

                queue.offer(result);
            }

        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(' ');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static Integer[] move(int pour, int fill, int stay, Integer[] state) {

        Integer[] result = new Integer[3];
        result[stay] = state[stay];

        // 더 담을 수 있는 공간
        int extra = maxAmount[fill] - state[fill];

        // 만약 따르는 물이 남은 공간보다 크다면
        if (state[pour] > extra) {
            result[fill] = maxAmount[fill];
            result[pour] = state[pour] - extra;
        }
        else {
            result[fill] = state[fill] + state[pour];
            result[pour] = 0;
        }

        return result;
    }
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static boolean[] visited;
    static Register result;

    static class Register {
        int[] num = new int[4];
        StringBuilder sb;

        public Register(String input, StringBuilder sb) {
            int idx = 4 - input.length();
            for (int i = 0; i < input.length(); i++) {
                num[idx++] = input.charAt(i) - '0';
            }

            this.sb = sb;
        }

        public Register(Register origin) {
            for (int i = 0; i < 4; i++) {
                num[i] = origin.num[i];
            }

            sb = new StringBuilder(origin.sb);
        }

        public int getInteger() {
            int result = 0;

            for (int i = 0; i < 4; i++) {
                result *= 10;
                result += num[i];
            }

            return result;
        }

        public Register D() {
            int round = 0;
            Register copy = new Register(this);

            for (int i = 3; i >= 0; i--) {
                int tmp = copy.num[i] * 2;

                copy.num[i] = tmp % 10 + round;
                round = tmp / 10;
            }

            copy.sb.append('D');
            return copy;
        }

        public Register S() {
            boolean round = true;
            Register copy = new Register(this);

            for (int i = 3; i >= 0; i--) {
                if (round) {
                    copy.num[i] -= 1;
                    round = false;
                }

                if (copy.num[i] < 0) {
                    round = true;
                    copy.num[i] = 9;
                }
            }

            copy.sb.append('S');
            return copy;
        }

        public Register R() {
            int tmp = num[3];
            Register copy = new Register(this);

            for (int i = 2; i >= 0; i--) {
                copy.num[i + 1] = copy.num[i];
            }

            copy.num[0] = tmp;
            copy.sb.append('R');
            return copy;
        }

        public Register L() {
            int tmp = num[0];
            Register copy = new Register(this);

            for (int i = 0; i < 3; i++) {
                copy.num[i] = copy.num[i + 1];
            }

            copy.num[3] = tmp;
            copy.sb.append('L');
            return copy;
        }
    }

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {

            String[] input = br.readLine().split(" ");
            Register start = new Register(input[0], new StringBuilder());
            int end = Integer.parseInt(input[1]);

            if (start.getInteger() == end) {
                bw.write("\n");
                break;
            }

            visited = new boolean[10000];
            visited[start.getInteger()] = true;

            Queue<Register> queue = new LinkedList<>();
            queue.offer(start);
            Register ret = start;
            while (!queue.isEmpty()) {

                Register curr = queue.poll();

                if (check(ret = curr.D(), queue, end)) {
                    result = ret;
                    break;
                }
                if (check(ret = curr.S(), queue, end)) {
                    result = ret;
                    break;
                }
                if (check(ret = curr.L(), queue, end)) {
                    result = ret;
                    break;
                }
                if (check(ret = curr.R(), queue, end)) {
                    result = ret;
                    break;
                }
            }

            ret.sb.append("\n");
            bw.write(ret.sb.toString());
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean check(Register reg, Queue<Register> queue, int dest) {

        int tmp = reg.getInteger();

        if (visited[tmp]) return false;
        if (dest == tmp) return true;

        visited[tmp] = true;
        queue.offer(reg);
        return false;
    }

}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    static int[][] dir = {
            {0, -1}, {1, -1}, {1, 0}, {1, 1},
            {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}
    };

    static class Fireball {
        int x;
        int y;
        int m;
        int d;
        int s;

        public Fireball(int x, int y, int m, int d, int s) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.d = d;
            this.s = s;
        }

        @Override
        public String toString() {
            return this.x + " " + this.y + " " + this.m + " " + this.s;
        }
    }

    static class CombFire {
        int m = 0;
        int s = 0;
        int dirBit = 0;
        int cnt = 0;

        public CombFire() {}

        @Override
        public String toString() {
            return this.m + " " + this.s + " " + this.dirBit + " " + this.cnt;
        }
    }

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);

        Queue<Fireball> fireballs = new LinkedList<>();
        CombFire[][] state = new CombFire[n][n];

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");

            int r = Integer.parseInt(input[0]) - 1;
            int c = Integer.parseInt(input[1]) - 1;
            int m2 = Integer.parseInt(input[2]);
            int s = Integer.parseInt(input[3]);
            int d = Integer.parseInt(input[4]);

            fireballs.offer(new Fireball(c, r, m2, d, s));
        }

        for (int t = 0; t < k; t++) {

            // 모든 파이어볼이 이동
            while (!fireballs.isEmpty()) {

                Fireball fireball = fireballs.poll();

                // 방향으로 속력만큼 이동
                for (int i = 0; i < fireball.s; i++) {
                    fireball.x += dir[fireball.d][0];
                    fireball.y += dir[fireball.d][1];

                    if (fireball.x >= n) fireball.x = 0;
                    else if (fireball.x < 0) fireball.x = n - 1;

                    if (fireball.y >= n) fireball.y = 0;
                    else if (fireball.y < 0) fireball.y = n - 1;
                }

                // 이동한 위치에 상태 저장
                if (state[fireball.y][fireball.x] == null) {
                    state[fireball.y][fireball.x] = new CombFire();
                }

                state[fireball.y][fireball.x].m += fireball.m;
                state[fireball.y][fireball.x].s += fireball.s;
                state[fireball.y][fireball.x].dirBit |= (1 << fireball.d);
                state[fireball.y][fireball.x].cnt += 1;

            }

            // 합쳐진 파이어볼 나누기
            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {
                    CombFire curr = state[i][j];
                    if (curr == null) continue;
                    state[i][j] = null;

                    // 위치의 파이어볼이 하나인 경우
                    if (curr.cnt == 1) {

                        // 방향만 계산해서 파이어볼 큐에 추가
                        Fireball fireball = new Fireball(j, i, curr.m, 0, curr.s);
                        for (int d = 0; d < 8; d++) {
                            if ((curr.dirBit & (1 << d)) > 0) {
                                fireball.d = d;
                                break;
                            }
                        }

                        fireballs.offer(fireball);
                        continue;
                    }

                    // 합쳐진 파이어볼의 경우
                    int tm = Math.floorDiv(curr.m, 5);
                    if (tm < 1) continue;

                    int ts = Math.floorDiv(curr.s, curr.cnt);

                    // 홀수 짝수 체크
                    boolean[] dirCheck = new boolean[2];
                    for (int d = 0; d < 8; d += 2) {
                        if ((curr.dirBit & (1 << d)) > 0) {
                            dirCheck[0] = true;
                            break;
                        }
                    }
                    for (int d = 1; d < 8; d += 2) {
                        if ((curr.dirBit & (1 << d)) > 0) {
                            dirCheck[1] = true;
                            break;
                        }
                    }

                    // 나눠진 파이어볼을 큐에 추가
                    for (int d = dirCheck[0] ^ dirCheck[1] ? 0 : 1; d < 8; d += 2) {
                        fireballs.offer(new Fireball(j, i, tm, d, ts));
                    }
                }

            }

        }

        int result = 0;
        while (!fireballs.isEmpty()) {
            result += fireballs.poll().m;
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

}
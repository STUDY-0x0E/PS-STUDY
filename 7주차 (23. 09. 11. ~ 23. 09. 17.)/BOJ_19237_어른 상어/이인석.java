import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    // 상 하 좌 우
    static int[][] dir = {
            {0, 0}, {0, -1}, {0, 1}, {-1, 0}, {1, 0}
    };

    static Smell[][] smellMap;
    static int[][] sharkMap;
    static Shark[] sharks;

    static class Smell {
        int idx;
        int cnt;

        public Smell(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    static class Shark {
        int x;
        int y;
        int dir;

        // 현재 dir => 행 / 4방향 => 열
        int[][] dirPriority = new int[5][4];

        public Shark (int x, int y) {
            this(x, y, 0);
        }

        public Shark (int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);

        sharks = new Shark[m + 1];
        smellMap = new Smell[n][n];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {

                if (!input[j].equals("0")) {
                    int idx = Integer.parseInt(input[j]);
                    sharks[idx] = new Shark(j, i);
                }
            }
        }

        input = br.readLine().split(" ");
        for (int i = 1; i <= m; i++) {

            sharks[i].dir = Integer.parseInt(input[i - 1]);
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= 4; j++) {
                input = br.readLine().split(" ");

                for (int t = 0; t < 4; t++) {
                    sharks[i].dirPriority[j][t] = Integer.parseInt(input[t]);
                }
            }
        }

        int time;
        int cnt = m;
        for (time = 0; time <= 1000; time++) {
            // 현재 남은 상어가 1마리라면 종료
            if (cnt < 2) break;

            // 상어가 자신의 위치에 냄새를 뿌린다
            for (int i = 1; i <= m; i++) {
                if (sharks[i] == null) continue;
                smellMap[sharks[i].y][sharks[i].x] = new Smell(i, k);
            }

            // 상어 이동
            sharkMap = new int[n][n];

            for (int i = 1; i <= m; i++) {
                Shark curr = sharks[i];
                // 이미 퇴출된 상어인 경우
                if (curr == null) continue;

                // 임시로 갈 칸을 기억
                int tx = -1, ty = -1, td = 0;
                int[] priority = curr.dirPriority[curr.dir];
                for (int j = 0; j < 4; j++) {

                    int d = priority[j];
                    int nx = curr.x + dir[d][0], ny = curr.y + dir[d][1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    // 빈 칸인 경우 이동 끝
                    if (smellMap[ny][nx] == null) {
                        tx = nx;
                        ty = ny;
                        td = d;
                        break;
                    }

                    // 내 냄새의 칸만 기억해두기 => 이미 기억한 칸이 있으면 더 기억하지 않음
                    if (smellMap[ny][nx].idx == i && tx < 0) {
                        tx = nx;
                        ty = ny;
                        td = d;
                    }

                }

                // 이미 상어가 있는 경우 => 수가 작은 상어부터 이동했으므로 무조건 퇴출
                if (sharkMap[ty][tx] != 0) {
                    sharks[i] = null;
                    --cnt;
                    continue;
                }

                // 이동 완료
                sharks[i].x = tx;
                sharks[i].y = ty;
                sharks[i].dir = td;
                sharkMap[ty][tx] = i;
            }

            // 냄새 관리
            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    if (smellMap[i][j] == null) continue;
                    smellMap[i][j].cnt -= 1;
                    if (smellMap[i][j].cnt < 1) smellMap[i][j] = null;
                }
            }
        }

        bw.write( time > 1000 ? "-1" : time + "" );
        bw.flush();
        bw.close();
        br.close();
    }

}
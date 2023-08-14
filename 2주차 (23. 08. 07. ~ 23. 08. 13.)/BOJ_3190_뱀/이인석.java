import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] dir = {
            { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }
    };

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        // 1 = 뱀 / 2 = 사과
        int[][] map = new int[n][n];
        map[0][0] = 1;
        StringTokenizer st;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 2;
        }

        Deque<Point> snake = new LinkedList<>();
        snake.add(new Point(0, 0));
        int cnt = 0;
        int dirIdx = 2;
        int l = Integer.parseInt(br.readLine());
        boolean isEnd = false;

        for (int t = 0; t < l; t++) {

            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);

            for (; cnt < time; cnt++) {

                Point head = snake.peekFirst();

                int nx = head.x + dir[dirIdx][0], ny = head.y + dir[dirIdx][1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[ny][nx] == 1) {
                    isEnd = true;
                    ++cnt;  // 마지막 이동에서 게임이 종료되었으므로 1초가 추가
                    break;
                }

                snake.addFirst(new Point(nx, ny));
                if (map[ny][nx] != 2) {
                    Point tail = snake.pollLast();
                    map[tail.y][tail.x] = 0;
                }
                map[ny][nx] = 1;
            }

            if (isEnd) break;
            if (direction == 'L') dirIdx = dirIdx == 0 ? 3 : dirIdx - 1;
            else dirIdx = (dirIdx + 1) % 4;
        }

        int x = snake.peekFirst().x, y = snake.peekFirst().y;
        while (!isEnd) {
            int nx = x + dir[dirIdx][0], ny = y + dir[dirIdx][1];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[ny][nx] == 1) {
                isEnd = true;
            }

            x = nx;
            y = ny;
            ++cnt;
        }

        bw.write(cnt + "");
        bw.flush();
        bw.close();
        br.close();
    }

}
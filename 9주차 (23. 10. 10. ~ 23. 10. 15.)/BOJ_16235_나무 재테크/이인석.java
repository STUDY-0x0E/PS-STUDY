import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    static int[][] dir = {
            {-1, 0}, {-1, -1}, {0, -1}, {1, -1},
            {1, 0}, {1, 1}, {0, 1}, {-1, 1}
    };

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);

        // 겨울에 줄 양분 배열 생성
        int[][] food = new int[n][n];
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");

            for (int j = 0; j < input.length; j++) {
                food[i][j] = Integer.parseInt(input[j]);
            }
        }

        // 나무 관리
        int cnt = 0;
        Deque<Integer>[][] trees = new ArrayDeque[n][n];
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");

            int y = Integer.parseInt(input[0]) - 1;
            int x = Integer.parseInt(input[1]) - 1;
            int z = Integer.parseInt(input[2]);

            if (trees[y][x] == null) {
                trees[y][x] = new ArrayDeque<>();
                trees[y][x].add(z);
            }

            else if (trees[y][x].peekFirst() <= z) {
                trees[y][x].addLast(z);
            }

            else {
                trees[y][x].addFirst(z);
            }

            ++cnt;
        }

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], 5);
        }

        for (int time = 0; time < k; time++) {

            int[][] newTreeCnt = new int[n][n];

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {
                    // 나무가 없는 칸은 스킵
                    if (trees[i][j] == null) continue;

                    // 양분을 준다 => 자란 나무는 새로운 Deque에 삽입
                    ArrayDeque<Integer> newDeque = new ArrayDeque<>();
                    Deque<Integer> deque = trees[i][j];
                    while (!deque.isEmpty() && deque.peekFirst() <= map[i][j]) {

                        int curr = deque.pollFirst();
                        map[i][j] -= curr;
                        ++curr;

                        // 번식할 나무는 미리 저장해둠
                        if (curr % 5 == 0) {
                            for (int d = 0; d < 8; d++) {

                                int nx = j + dir[d][0], ny = i + dir[d][1];
                                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                                newTreeCnt[ny][nx] += 1;
                                ++cnt;
                            }
                        }
                        newDeque.offer(curr);
                    }

                    // 양분이 부족한 나무는 사망 처리
                    int tmp = 0;
                    while (!deque.isEmpty()) {

                        int curr = deque.poll();
                        tmp += (int) curr / 2;
                        --cnt;
                    }
                    // 양분 미리 추가
                    map[i][j] += tmp;

                    // 자라난 나무 큐로 변경
                    trees[i][j] = newDeque;
                }
            }

            // 번식한 나무 추가(가을) + 양분 추가(겨울)
            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    for (int t = 0; t < newTreeCnt[i][j]; t++) {

                        if (trees[i][j] == null) {
                            trees[i][j] = new ArrayDeque<>();
                            trees[i][j].add(1);
                        }

                        else {
                            trees[i][j].addFirst(1);
                        }

                    }
                    map[i][j] += food[i][j];
                }
            }

        }

        bw.write(cnt + "");
        bw.flush();
        bw.close();
        br.close();
    }

}
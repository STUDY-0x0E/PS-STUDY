import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    static int size = 9;
    static int[][] map = new int[size][size];
    static List<Point> startPoints = new ArrayList<>(size * size);

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                int value = Integer.parseInt(st.nextToken());

                if (value == 0) startPoints.add(new Point(j, i));
                map[i][j] = value;
            }
        }

        setSudoku(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(map[i][j]).append(' ');
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean setSudoku(int cnt) {

        if (cnt >= startPoints.size()) {
            return true;
        }

        Point curr = startPoints.get(cnt);
        boolean isPossible = false;

        boolean[] isNotPossibleNum = new boolean[10];

        // 가로 검사
        for (int i = 0; i < size; i++) {
            isNotPossibleNum[map[curr.y][i]] = true;
        }

        // 세로 검사
        for (int i = 0; i < size; i++) {
            isNotPossibleNum[map[i][curr.x]] = true;
        }

        // 3 x 3 검사
        int rowNum = (curr.y / 3) * 3;
        int colNum = (curr.x / 3) * 3;
        for (int i = rowNum; i < rowNum + 3; i++) {
            for (int j = colNum; j < colNum + 3; j++) {
                isNotPossibleNum[map[i][j]] = true;
            }
        }

        for (int i = 1; i < isNotPossibleNum.length; i++) {
            if (isNotPossibleNum[i]) continue;

            map[curr.y][curr.x] = i;
            isPossible = isPossible || setSudoku(cnt + 1);
            if (isPossible) break;
            map[curr.y][curr.x] = 0;
        }

        return isPossible;
    }
}
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int result = Integer.MIN_VALUE;
    static int[][] triangle;
    static int[][] visited;
    static int n;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        triangle = new int[n][n];
        visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < i + 1; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        for(int i = 0; i < n; i++) {
            Arrays.fill(visited[i], -1);
        }

        for (int i = 0; i < n; i++) {
            visited[n - 1][i] = triangle[n - 1][i];
        }

        bw.write(findPath(0, 0) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int findPath(int row, int col) {

        if (visited[row][col] >= 0) return visited[row][col];

        int a = triangle[row][col], b = triangle[row][col];

        a += findPath(row + 1, col);
        if (row >= col) b += findPath(row + 1, col + 1);

        visited[row][col] = Math.max(a, b);
        return visited[row][col];
    }
}
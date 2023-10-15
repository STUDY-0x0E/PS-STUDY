import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] cities = new int[n][n];
        for (int i = 0; i < n; i++) {

            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                cities[i][j] = Integer.parseInt(input[j]);
            }
        }

        bw.write(getOriginal(n, cities) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getOriginal(int n, int[][] cities) {

        int[][] answer = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = cities[i][j];
            }
        }


        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (i == k) continue;

                for (int j = 0; j < n; j++) {
                    if (i == j || j == k) continue;

                    if (cities[i][j] > cities[i][k] + cities[k][j]) return -1;
                    if (cities[i][j] == cities[i][k] + cities[k][j]) answer[i][j] = 0;
                }
            }
        }

        int tmp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp += answer[i][j];
            }
        }

        return tmp / 2;
    }
}
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

        int[][] synergy = new int[n][n];
        for(int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                synergy[i][j] = Integer.parseInt(input[j]);
            }
        }

        int[] comb = new int[n];
        for (int i = n - 1; i >= n / 2; i--) {
            comb[i] = 1;
        }

        int result = Integer.MAX_VALUE;

        do {

            int[] teams = new int[2];

            for (int i = 0; i < n; i++) {

                for (int j = i + 1; j < n; j++) {
                    if (comb[i] == comb[j]) {
                        teams[comb[i]] += synergy[i][j];
                        teams[comb[i]] += synergy[j][i];
                    }
                }

            }

            result = Math.min(result, Math.abs(teams[0] - teams[1]));

        } while (np(comb));


        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean np(int[] num) {
        int i = num.length - 1;
        while (i > 0 && num[i - 1] >= num[i]) {
            --i;
        }
        if (i < 1) return false;

        int j = num.length - 1;
        while (num[j] <= num[i - 1]) {
            --j;
        }
        swap(num, i - 1, j);

        int k = num.length - 1;
        while (k > i) {
            swap(num, i++, k--);
        }

        return true;
    }

    public static void swap(int[] num, int a, int b) {
        int tmp = num[a];
        num[a] = num[b];
        num[b] = tmp;
    }

}
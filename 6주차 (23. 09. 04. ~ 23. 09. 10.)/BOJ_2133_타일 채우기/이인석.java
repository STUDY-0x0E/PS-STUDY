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

        if (n % 2 == 1) bw.write("0");
        else {
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[2] = 3;

            for (int i = 4; i <= n; i++) {

                dp[i] += dp[i - 2] * dp[2];
                for (int j = 1; i >= 2 * j + 2; j++) {
                    int tmp = 2 * j + 2;
                    dp[i] += dp[i - tmp] * 2;
                }
            }

            bw.write(dp[n] + "");
        }


        bw.flush();
        bw.close();
        br.close();
    }
}
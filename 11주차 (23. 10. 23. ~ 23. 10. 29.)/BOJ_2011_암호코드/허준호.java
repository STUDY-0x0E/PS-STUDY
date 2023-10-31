import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();

        if (code.charAt(0) == '0') {
            System.out.println(0);
        } else {
            int len = code.length();
            int[] dp = new int[len + 1];
            dp[0] = 1;
            dp[1] = 1;

            for (int i = 2; i <= len; i++) {
                char prevChar = code.charAt(i - 2);
                char currChar = code.charAt(i - 1);

                int num = Integer.parseInt(currChar + "");
                if (num >= 1 && num <= 9) {
                    dp[i] += dp[i - 1];
                }

                int prevNum = Integer.parseInt(prevChar + "" + currChar);
                if (prevNum >= 10 && prevNum <= 26) {
                    dp[i] += dp[i - 2];
                }

                dp[i] %= 1000000;
            }

            System.out.println(dp[len]);
        }
    }
}

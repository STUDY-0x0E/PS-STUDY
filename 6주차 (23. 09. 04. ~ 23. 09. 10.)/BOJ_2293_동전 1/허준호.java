import java.io.*;
import java.util.*;

/*      1  2  3  4  5  6  7  8  9  10
 *   1  1  1  1  1  1  1  1  1  1  1  
 *   2  0  1  1  2  2  3  3  4  4  5
 *   5  0  0  0  0  1  1  2  2  3  4
 *  sum 1  2  2  3  4  5  6  9  8  *10*
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()); // 동전의 종류 수
        int k = Integer.parseInt(st.nextToken()); // 목표 금액
        int[] coinValues = new int[n]; // 동전의 가치

        for (int i = 0; i < n; i++) {
            coinValues[i] = Integer.parseInt(br.readLine());
        }
        
        long[] dp = new long[k + 1];// i원을 만드는 경우의 수
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = coinValues[i]; j <= k; j++) {
                dp[j] += dp[j - coinValues[i]];
            }
        }

        System.out.println(dp[k]);
    }
}
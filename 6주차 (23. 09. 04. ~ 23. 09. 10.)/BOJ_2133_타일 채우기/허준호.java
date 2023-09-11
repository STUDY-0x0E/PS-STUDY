import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] dp = new long[n + 1];

        dp[0] = 1;//아무것도 안채운 경우
//      dp[1] = 0;//홀수의 경우 못 채움
//      dp[2] = 3;//ㅗ, ㅜ, =
//      dp[3] = 0;//홀수
//      dp[4] = 9;//ㅗㅗ, ㅗㅜ,ㅗ=,ㅜㅗ,ㅜㅜ,ㅜ=,=ㅗ,=ㅜ,==
//      dp[6] = d[4]*3;
//      dp[8] = d[6]*3 + (d[4]+d[2]+d[0])*2;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] * 3; // 이전 결과에 2x1 크기의 타일 3개 추가
            for (int j = i-4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2; // 이전 결과에 2x2 크기의 타일 2개 추가
            }
        }

        System.out.println(dp[n]);
    }
}
 
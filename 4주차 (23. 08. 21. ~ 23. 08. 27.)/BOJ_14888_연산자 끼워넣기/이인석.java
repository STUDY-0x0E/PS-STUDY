import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    static int[] operates;
    static int[] operators;
    static BinaryOperator<Integer>[] operatorArr;

    static int maxResult;
    static int minResult;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        operates = new int[n];
        for (int i = 0; i < n; i++) {
            operates[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        operators = new int[4];
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        maxResult = Integer.MIN_VALUE;
        minResult = Integer.MAX_VALUE;

        operatorArr = new BinaryOperator[4];
        operatorArr[0] = (o1, o2) -> o1 + o2;
        operatorArr[1] = (o1, o2) -> o1 - o2;
        operatorArr[2] = (o1, o2) -> o1 * o2;
        operatorArr[3] = (o1, o2) -> o1 / o2;

        calc(0, operates[0]);

        StringBuilder sb = new StringBuilder();
        sb.append(maxResult).append('\n').append(minResult);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static public void calc(int cnt, int result) {

        if (cnt >= operates.length - 1) {
            maxResult = Math.max(maxResult, result);
            minResult = Math.min(minResult, result);
            return ;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] < 1) continue;
            if (i == 3 && operates[cnt + 1] == 0) continue;

            --operators[i];
            calc(cnt + 1, operatorArr[i].apply(result, operates[cnt + 1]));
            ++operators[i];
        }
    }
}
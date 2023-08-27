import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] woods = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            woods[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(woods);
        long front = 1, back = woods[woods.length - 1];
        while (front <= back) {
            long mid = (front + back) / 2L;

            long result = 0;
            for (long wood : woods) {
                long tmp = wood - mid;
                result += tmp > 0 ? tmp : 0;
            }

            // mid는 자르는 높이, 잘린 높이가 아님 => 값이 작아질수록 얻는 나무의 총길이가 커짐
            if (result < m) {
                back = mid - 1;
            }
            else {
                front = mid + 1;
            }
        }

        // 원래는 front를 가져오는게 맞지만 값이 작은쪽이 얻는 자르는 나무 총길이가 큼
        bw.write(back + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
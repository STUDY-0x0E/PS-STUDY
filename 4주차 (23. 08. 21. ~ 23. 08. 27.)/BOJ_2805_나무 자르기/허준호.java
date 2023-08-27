import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());//필요한 나무 높이(long)

        long[] trees = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trees[i] = Long.parseLong(st.nextToken());
        }

        long left = 0; // 최소 절단기 높이
        long right = 2000000000; // 최대 절단기 높이 (10^9 * 2)
        long ans = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;

            for (long tree : trees) {
                if (tree >= mid) {
                    sum += tree - mid;
                }
            }

            if (sum >= m) {
                ans = Math.max(ans, mid);
                left = mid+1;
            } else {
                right = mid -1;
            }
        }

        System.out.println(ans);
    }
}
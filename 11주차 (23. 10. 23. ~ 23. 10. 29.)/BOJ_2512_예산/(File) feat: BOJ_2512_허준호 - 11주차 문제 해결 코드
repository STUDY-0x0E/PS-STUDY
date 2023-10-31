import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] requests = new int[N];
        int maxRequest = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            requests[i] = Integer.parseInt(st.nextToken());
            maxRequest = Math.max(maxRequest, requests[i]);
        }
        long totalBudget = Long.parseLong(br.readLine());

        long left = 1;
        long right = maxRequest;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long total = 0;

            for (int i = 0; i < N; i++) {
                total += Math.min(requests[i], mid);
            }

            if (total <= totalBudget) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}

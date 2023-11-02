import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] request = new int[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            request[i] = Integer.parseInt(input[i]);
        }
        int total = Integer.parseInt(br.readLine());

        Arrays.sort(request);
        int front = 1, end = request[n - 1];

        while (front <= end) {

            int mid = ( front + end ) / 2;
            if (setBudget(request, mid, total)) {
                front = mid + 1;
            }
            else {
                end = mid - 1;
            }

        }

        bw.write(end + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean setBudget(int[] request, int max, int total) {

        long sum = 0;
        for (int i = 0; i < request.length; i++) {
            if (request[i] < max) sum += request[i];
            else sum += max;
        }

        if (sum <= total) return true;
        return false;
    }

}
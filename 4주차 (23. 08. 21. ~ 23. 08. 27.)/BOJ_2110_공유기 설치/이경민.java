import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n,c,answer;
    static long[] house;

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        house = new long[n];
        for(int i=0; i<n; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
    }

    public static void solve() {
        Arrays.sort(house);
        long start = 1;
        long end = house[n-1] - house[0];
        long install = 0;
        while(start<=end) {
            install = 1;
            long mid = (start + end) / 2;
            long gap = house[0];
            for(int i=1; i<n; i++) {
                if(house[i]-gap>=mid) {
                    gap = house[i];
                    ++install;
                }
            }
            if(install>=c) {
                answer = Math.max(answer,(int)mid);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(answer);
    }
}

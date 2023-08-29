import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int size = (int)1e6;

    static int n,m;
    static int[] tree;

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        tree = new int[n];
        for(int i=0; i<n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }
    }

    static boolean isValid(long height) {
        long sum = 0;
        for(int i=0; i<n; i++) {
            if(tree[i] >= height) {
                sum += tree[i];
                sum -= height;
            }
        }
        return sum >= m;
    }

    public static void solve() {
        Arrays.sort(tree);
        long start = 1;
        long end = tree[n-1];
        long answer = 0;
        while(start<=end) {
            long mid = (start+end)/2;
            if(isValid(mid)) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}


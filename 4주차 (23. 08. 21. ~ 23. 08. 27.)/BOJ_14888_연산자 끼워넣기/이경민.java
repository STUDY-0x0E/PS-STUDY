import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, min, max;
    static int[] arr = new int[11];
    static int[] oper = new int[4];

    public static void input() throws IOException {
//        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void process(int sum, int start) {
        if(start==n) {
            min = Math.min(min,sum);
            max = Math.max(max,sum);
            return;
        }
        for(int i=0; i<4; i++) {
            if(oper[i]==0) {
                continue;
            }
            --oper[i];
            int tmp = sum;
            if(i==0) tmp += arr[start];
            else if(i==1) tmp -= arr[start];
            else if(i==2) tmp *= arr[start];
            else tmp /= arr[start];
            process(tmp,start+1);
            ++oper[i];
        }
    }

    public static void solve() {
        max = -Integer.MAX_VALUE;
        min = Integer.MAX_VALUE;
OBOBOB        process(arr[0],1);
        System.out.println(max);
        System.out.println(min);
OBOBOB    }
OBOBOB
OBOBOB    public static void main(String[] args) throws IOException {
OBOBOB        input();
        solve();
    }
}

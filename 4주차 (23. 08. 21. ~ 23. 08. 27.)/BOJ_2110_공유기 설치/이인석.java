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
        int[] houses = new int[n];

        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        // 초기값 : 제일 작은 간격 검색
        int front = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            front = Math.min(houses[i] - houses[i - 1], front);
        }

        // 제일 큰 간격은 양 끝값의 차
        int back = houses[houses.length - 1] - houses[0];
        while (front <= back) {
            int mid = (front + back) / 2;

            if (setRouters(m, houses, mid) > 0) {
                back = mid - 1;
            }
            else {
                front = mid + 1;
            }
        }

        bw.write(front + "");
        bw.flush();
        bw.close();
        br.close();
    }

    // 양수 : 공유기를 모두 두지 못한 경우
    // 음수 : 공유기를 다 두고도 거리가 남는 경우
    public static int setRouters(int routerNum, int[] houses, int distance) {
        int prev = -1 * distance; // 초기값을 음수 거리값으로 둬서 좌표값이 0이 나와도 무조건 거리를 만족하게 함

        for (int i = 0; i < houses.length; i++) {
            int d = houses[i] - prev;

            if (d > distance) {
                prev = houses[i];
                --routerNum;
            }
        }

        return routerNum;
    }
}
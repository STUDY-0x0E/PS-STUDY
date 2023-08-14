import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] durability = new int[2 * n];
        boolean[] isRobotIn = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        int breakNum = 0;
        int cnt = 0;
        while (breakNum < k) {

            // 1. 벨트가 각 칸 위에 있는 로봇과 함께 회전한다.
            rotate(durability);
            rotateRobot(isRobotIn);

            // 2. 가장 먼저 올라간 로봇부터 회전
            isRobotIn[n - 1] = false;
            for (int i = n - 2; i >= 0; i--) {
                if (!isRobotIn[i]) continue;
                if (!isRobotIn[i + 1] && durability[i + 1] > 0) {

                    isRobotIn[i + 1] = true;
                    isRobotIn[i] = false;
                    --durability[i + 1];

                    if (durability[i + 1] < 1) {
                        ++breakNum;
                    }
                }
            }
            isRobotIn[n - 1] = false;

            // 3. 올리는 칸에 올릴 수 있으면 로봇을 올린다.
            if (!isRobotIn[0] && durability[0] > 0) {
                isRobotIn[0] = true;
                --durability[0];

                if (durability[0] < 1) {
                    ++breakNum;
                }
            }

            ++cnt;
        }

        bw.write(cnt + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void rotate(int[] list) {
        int tmp = list[list.length - 1];
        for (int i = list.length - 1; i > 0; i--) {
            list[i] = list[i - 1];
        }
        list[0] = tmp;
    }

    public static void rotateRobot(boolean[] list) {
        for (int i = list.length - 1; i > 0; i--) {
            list[i] = list[i - 1];
        }
        list[0] = false;
    }

}
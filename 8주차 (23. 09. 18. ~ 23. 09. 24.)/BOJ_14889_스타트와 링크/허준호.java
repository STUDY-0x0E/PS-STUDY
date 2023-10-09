import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] S;
    static boolean[] visited;
    static int minDifference = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        divideTeam(0, 0);

        System.out.println(minDifference);
    }

    static void divideTeam(int index, int count) {
        if (count == N / 2) {
            calculateDifference();
            return;
        }

        for (int i = index; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                divideTeam(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }

    static void calculateDifference() {
        int startTeam = 0;
        int linkTeam = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i] && visited[j]) {  // 스타트 팀인 경우
                    startTeam += S[i][j];
                } else if (!visited[i] && !visited[j]) { // 링크 팀인 경우
                    linkTeam += S[i][j];
                }
            }
        }

        int difference = Math.abs(startTeam - linkTeam);
        minDifference = Math.min(minDifference, difference);
    }
}
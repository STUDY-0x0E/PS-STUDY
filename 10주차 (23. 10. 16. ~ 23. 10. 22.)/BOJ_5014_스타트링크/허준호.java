import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());  // 총 층 수
        int S = Integer.parseInt(st.nextToken());  // 시작 층
        int G = Integer.parseInt(st.nextToken());  // 목표 층
        int U = Integer.parseInt(st.nextToken());  // U 버튼으로 올라가는 층 수
        int D = Integer.parseInt(st.nextToken());  // D 버튼으로 내려가는 층 수

        int[] dist = new int[F + 1];

        for (int i = 1; i <= F; i++) {
            dist[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);
        dist[S] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // U 버튼을 누른 경우
            int nextUp = current + U;
            if (nextUp <= F && dist[nextUp] == -1) {
                dist[nextUp] = dist[current] + 1;
                queue.add(nextUp);
            }

            // D 버튼을 누른 경우
            int nextDown = current - D;
            if (nextDown >= 1 && dist[nextDown] == -1) {
                dist[nextDown] = dist[current] + 1;
                queue.add(nextDown);
            }
        }

        if (dist[G] != -1) {
            System.out.println(dist[G]);
        } else {
            System.out.println("use the stairs");
        }
    }
}import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());  // 총 층 수
        int S = Integer.parseInt(st.nextToken());  // 시작 층
        int G = Integer.parseInt(st.nextToken());  // 목표 층
        int U = Integer.parseInt(st.nextToken());  // U 버튼으로 올라가는 층 수
        int D = Integer.parseInt(st.nextToken());  // D 버튼으로 내려가는 층 수

        int[] dist = new int[F + 1];

        for (int i = 1; i <= F; i++) {
            dist[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);
        dist[S] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // U 버튼을 누른 경우
            int nextUp = current + U;
            if (nextUp <= F && dist[nextUp] == -1) {
                dist[nextUp] = dist[current] + 1;
                queue.add(nextUp);
            }

            // D 버튼을 누른 경우
            int nextDown = current - D;
            if (nextDown >= 1 && dist[nextDown] == -1) {
                dist[nextDown] = dist[current] + 1;
                queue.add(nextDown);
            }
        }

        if (dist[G] != -1) {
            System.out.println(dist[G]);
        } else {
            System.out.println("use the stairs");
        }
    }
}

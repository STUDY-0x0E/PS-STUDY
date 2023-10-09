import java.util.*;

class State {
    int a, b, c;

    public State(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();

        boolean[][] visited = new boolean[A + 1][B + 1];
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(0, 0, C));

        int[] result = new int[C + 1];

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            int a = currentState.a;
            int b = currentState.b;
            int c = currentState.c;

            if (visited[a][b]) continue;
            visited[a][b] = true;

            if (a == 0) {
                result[c] = 1;
            }

            // A to B
            if (a + b > B) {
                queue.offer(new State(a + b - B, B, c));
            } else {
                queue.offer(new State(0, a + b, c));
            }

            // A to C
            if (a + c > C) {
                queue.offer(new State(a + c - C, b, C));
            } else {
                queue.offer(new State(0, b, a + c));
            }

            // B to A
            if (b + a > A) {
                queue.offer(new State(A, b + a - A, c));
            } else {
                queue.offer(new State(b + a, 0, c));
            }

            // B to C
            if (b + c > C) {
                queue.offer(new State(a, b + c - C, C));
            } else {
                queue.offer(new State(a, 0, b + c));
            }

            // C to A
            if (c + a > A) {
                queue.offer(new State(A, b, c + a - A));
            } else {
                queue.offer(new State(c + a, b, 0));
            }

            // C to B
            if (c + b > B) {
                queue.offer(new State(a, B, c + b - B));
            } else {
                queue.offer(new State(a, c + b, 0));
            }
        }

        for (int i = 0; i <= C; i++) {
            if (result[i] == 1) {
                System.out.print(i + " ");
            }
        }
    }
}
import java.io.*;
import java.util.*;

class Tree implements Comparable<Tree> {
    int x, y, age;

    public Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }

    @Override
    public int compareTo(Tree o) {
        return Integer.compare(this.age, o.age);
    }
}

public class Main {
    private static int N, M, K;
    private static int[][] A;
    private static int[][] ground;
    private static List<Tree> trees = new ArrayList<>();

    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());// NxN 크기의 땅
        M = Integer.parseInt(st.nextToken());// M개의 나무
        K = Integer.parseInt(st.nextToken());// K년이 지난 후

        A = new int[N][N]; // 각 칸에 추가되는 양분의 양
        ground = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                ground[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, age));
        }

        Collections.sort(trees);

        for (int year = 0; year < K; year++) {
            springAndSummer();
            autumn();
            winter();
        }

        System.out.println(trees.size());
    }

    private static void springAndSummer() {
        ArrayList<Tree> newTrees = new ArrayList<>();
        ArrayList<Tree> deadTrees = new ArrayList<>();

        for (Tree tree : trees) {
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;

            if (ground[x][y] >= age) {
                ground[x][y] -= age;
                newTrees.add(new Tree(x, y, age + 1));
            } else {
                deadTrees.add(tree);
            }
        }

        for (Tree tree : deadTrees) {
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;

            ground[x][y] += age / 2;
        }

        trees.clear();
        trees.addAll(newTrees);
    }

    private static void autumn() {
        ArrayList<Tree> newTrees = new ArrayList<>();

        for (Tree tree : trees) {
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;

            if (age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        newTrees.add(new Tree(nx, ny, 1));
                    }
                }
            }
        }

        trees.addAll(0, newTrees);
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ground[i][j] += A[i][j];
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static int result = Integer.MIN_VALUE;
    static ArrayList<LinkedList<Integer>> tree;
    static int[] weight;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        // 간선 정보 저장
        tree = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            tree.add(new LinkedList<>());
        }
        // 가중치 -> 부모 노드와의 간선은 무조건 1개
        weight = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            String[] input = br.readLine().split(" ");

            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);

            tree.get(start).add(end);
            weight[end] = Integer.parseInt(input[2]);
        }

        getDiameter(1);

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getDiameter(int idx) {

        LinkedList<Integer> children = tree.get(idx);

        // 자식 노드 중에서 가장 긴 편향되게 연결한 길이를 구함
        int[] max = new int[2];
        for (int i = 0; i < children.size(); i++) {

            int childLength = getDiameter(children.get(i));

            if (childLength > max[0]) {
                max[1] = max[0];
                max[0] = childLength;
            }
            else if (childLength > max[1]) {
                max[1] = childLength;
            }
        }

        result = Math.max(result, max[0] + max[1]);
        return max[0] + weight[idx];

    }

}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int size = Integer.parseInt(br.readLine());
            minHeap.add(size);
        }

        int totalComparisons = 0;

        while (minHeap.size() > 1) {
            int a = minHeap.poll();
            int b = minHeap.poll();
            int sum = a + b;
            totalComparisons += sum;
            minHeap.add(sum);
        }

        System.out.println(totalComparisons);
    }
}

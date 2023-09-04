package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 여러 개의 다리가 존재한다..!!
 */
public class p1939 {
    static int n, m;
    static int a, b, c;
    static ArrayList<ArrayList<Edge>> edgeList;
    static boolean[] visit;
    static int answer;

    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        visit = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            edgeList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            edgeList.get(a).add(new Edge(b, c));
            edgeList.get(b).add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int left = 1;
        int right = 1_000_000_000;
        int mid = (left + right) / 2;

        while (left <= right) {
            // start에서 end로 가는 다리 중 최소 비용을 출력한다.
            Queue<Integer> q = new ArrayDeque<>();
            q.add(start);
            visit = new boolean[n + 1];
            visit[start] = true;

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int i = 0; i < edgeList.get(cur).size(); i++) {
                    // 방문했던 정점이라면
                    if (visit[edgeList.get(cur).get(i).to]) continue;
                    if (edgeList.get(cur).get(i).weight < mid) continue;

                    q.add(edgeList.get(cur).get(i).to);
                    visit[edgeList.get(cur).get(i).to] = true;
                }
            }

            // 마지막 점에 방문했다면 ? -> 더 큰 곳으로 변경
            if (visit[end]) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            }
            // 마지막 점에 방문할 수 없었다면? -> 더 작은 곳으로 변경
            else {
                right = mid - 1;
            }
            mid = (left + right) / 2;
        }

        System.out.println(answer);
    }
}

package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class p4195 {
    static int t, n;
    static HashMap<String, Integer> map;
    static int[] parents;
    static String s1, s2;
    static int index;
    static int[] count;

    static int makeSet(String name) {
        // 처음 등장한 사람
        if (!map.containsKey(name)) {
            map.put(name, index);
            parents[index] = index;
            return index++;
        }
        // 이전 등장 이력
        else {
            return map.get(name);
        }
    }

    static int find(int a) {
        if (parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

    // b -> a
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;

        count[aRoot] += count[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int a = 1; a <= t; a++) {
            n = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            parents = new int[n * 2];
            count = new int[n*2];
            Arrays.fill(count, 1);
            index = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                s1 = st.nextToken();
                s2 = st.nextToken();
                int b = makeSet(s1);
                int c = makeSet(s2);
                union(b, c);
                System.out.println(count[find(b)]);
            }
        }
    }
}

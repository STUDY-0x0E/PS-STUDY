import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static Map<String, String> friends;
    static Map<String, Integer> networkNum;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {

            // 이름, 부모 인덱스 매칭
            friends = new HashMap<>();
            networkNum = new HashMap<>();

            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                
                String a = st.nextToken();
                String b = st.nextToken();

                if (friends.get(a) == null) make(a);
                if (friends.get(b) == null) make(b);

                bw.write(networkNum.get(union(a, b)) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void make(String friend) {
        friends.put(friend, friend);
        networkNum.put(friend, 1);
    }

    public static String find(String friend) {
        if (friends.get(friend).equals(friend)) return friend;

        String network = find(friends.get(friend));
        friends.put(friend, network);

        return network;
    }

    public static String union(String a, String b) {
        String aRoot = find(a);
        String bRoot = find(b);

        if (aRoot.equals(bRoot)) return aRoot;
        friends.put(bRoot, aRoot);

        networkNum.put(aRoot, networkNum.get(aRoot) + networkNum.get(bRoot));
        return aRoot;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class FriendNetwork {
    private Map<String, String> parent;
    private Map<String, Integer> networkSize;

    public FriendNetwork() {
        parent = new HashMap<>();//부모노드
        networkSize = new HashMap<>();//네트워크의 크기
    }

    public String find(String user) {//union find
        if (!parent.containsKey(user)) {//처음 넣는 경우
            parent.put(user, user);
            networkSize.put(user, 1);
            return user;
        }

        if (parent.get(user).equals(user)) {//같은 경우
            return user;
        }

        String root = find(parent.get(user));//갱신
        parent.put(user, root);
        return root;
    }

    public void union(String user1, String user2) {//연결 후 네트워크 크기 갱신
        String root1 = find(user1);
        String root2 = find(user2);

        if (!root1.equals(root2)) {
            parent.put(root1, root2);
            int size1 = networkSize.get(root1);
            int size2 = networkSize.get(root2);
            networkSize.put(root2, size1 + size2);
        }
    }

    public int getNetworkSize(String user) {//네트워크의 크기 반환
        String root = find(user);
        return networkSize.get(root);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            int F = Integer.parseInt(br.readLine());
            FriendNetwork friendNetwork = new FriendNetwork();

            for (int i = 0; i < F; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
                String user1 = st.nextToken();
                String user2 = st.nextToken();

                friendNetwork.union(user1, user2);
                int networkSize = friendNetwork.getNetworkSize(user1);
                System.out.println(networkSize);
            }
        }
    }
}

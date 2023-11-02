import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    
    static final int MOD = 1000000;
    static Map<String, Integer> map = new HashMap<String, Integer>();

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine().trim();
        
        getCount(input);
        
        bw.write(map.get(input) + "");
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static int getCount(String str) {
    	if (str.length() < 1) return 1;
    	if (map.containsKey(str)) return map.get(str);
    	
    	int cnt = 0;
    	
    	// 1개짜리 => 0을 제외하고 가능
    	int tmp = Integer.parseInt(str.substring(0, 1));
    	if (tmp > 0) cnt = getCount(str.substring(1)) % MOD;
    	
    	if (str.length() > 1 && !str.startsWith("0")) {
    		tmp = Integer.parseInt(str.substring(0, 2));
        	if (tmp >= 1 && tmp <= 26) {
        		cnt += getCount(str.substring(2)) % MOD;
        		cnt %= MOD;
        	}
    	}
    	
    	map.put(str, cnt);
    	return cnt;
    }

}
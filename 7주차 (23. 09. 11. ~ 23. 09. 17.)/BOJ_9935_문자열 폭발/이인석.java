import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine().trim();
        String boom = br.readLine().trim();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {

            stack.add(str.charAt(i));

            int findIdx = boom.length() - 1;
            Stack<Character> tmp = new Stack<>();
            while (findIdx >= 0 && !stack.isEmpty() && stack.peek() == boom.charAt(findIdx)) {
                tmp.add(stack.pop());
                --findIdx;
            }

            // 폭발 문자열 길이만큼 스택에 들어간 경우
            if (findIdx < 0) tmp.clear();

            while (!tmp.isEmpty()) {
                stack.add(tmp.pop());
            }
        }

        if (stack.isEmpty()) bw.write("FRULA");
        else {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            sb.reverse();
            bw.write(sb.toString());
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
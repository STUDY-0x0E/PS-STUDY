import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String explosion = br.readLine();
        String result = explode(input, explosion);
        System.out.println(result);
    }

    public static String explode(String input, String explosion) {
        int explosionLength = explosion.length();
        char[] result = new char[input.length()]; // 폭발 후 남은 문자열을 저장할 배열
        int idx = 0; // 결과 문자열의 인덱스

        for (int i = 0; i < input.length(); i++) {
            result[idx] = input.charAt(i);
            idx++;

            if (idx >= explosionLength) {
                // 폭발 문자열이 가능한지 검사
                boolean isExplosion = true;
                for (int j = 0; j < explosionLength; j++) {
                    if (result[idx - explosionLength + j] != explosion.charAt(j)) {
                        isExplosion = false;
                        break;
                    }
                }

                if (isExplosion) {
                    // 폭발 문자열이 있는 경우 폭발
                    idx -= explosionLength;
                }
            }
        }

        if (idx == 0) {
            return "FRULA"; // 남아있는 문자가 없는 경우
        }

        return new String(result, 0, idx);
    }
}
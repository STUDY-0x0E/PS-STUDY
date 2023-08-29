import java.io.*;
import java.util.*;

import static java.lang.System.exit;

public class Main {

    static class Pair<A,B> {
        A x;
        B y;
        Pair(A x,B y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[][] map = new int[9][9];
    static Pair<Integer,Integer>[] block = new Pair[81];
    static boolean flag = false;
    static int blockSize = 0;

    public static void input() throws IOException {
        for(int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==0) {
                    block[blockSize++]=new Pair(i,j);
                }
            }
        }
    }

    static boolean isValid(int x, int y, int num) {
        for(int i=0; i<9; i++) {
            if(map[x][i]==num) {
                return false;
            }
            if(map[i][y]==num) {
                return false;
            }
        }
        x /= 3; y /= 3; x *= 3; y *= 3;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(map[x+i][y+j]==num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void solve(int count) {
        if(flag) {
            return;
        }
        if(count==blockSize) {
            print();
            flag = true;
            return;
        }
        int nx = block[count].x;
        int ny = block[count].y;
        for(int i=1; i<10; i++) {
            if(isValid(nx,ny,i)) {
                map[nx][ny] = i;
                solve(count+1);
                map[nx][ny] = 0;
            }
        }
    }

    static void print() {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve(0);
    }
}

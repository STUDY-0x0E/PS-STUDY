import java.io.*;
import java.util.*;

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

    static final int inf = (int)1e9;
    static final char wall = '1';
    static int n, m,answer;
    static char[][] map;
    static int[][] fdist, bdist;
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        fdist = new int[n][m];
        bdist = new int[n][m];
        for(int i=0; i<n; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }

    static boolean checkRange(int x, int y) {
        return x>=0 && y>=0 && x<n && y<m;
    }

    static void getFrontDist() {
        ArrayDeque<Pair<Integer, Integer>> ad = new ArrayDeque<>();
        ad.push(new Pair(0,0));
        fdist[0][0] = 1;
        while(!ad.isEmpty()) {
            Pair<Integer, Integer> f = ad.pollFirst();
            for(int i=0; i<4; i++) {
                int nx = f.x + dx[i];
                int ny = f.y + dy[i];
                if(!checkRange(nx,ny)) {
                    continue;
                }
                if(map[nx][ny]==wall) {
                    continue;
                }
                if(fdist[nx][ny]>0) {
                    continue;
                }
                ad.addLast(new Pair(nx,ny));
                fdist[nx][ny] = fdist[f.x][f.y] + 1;
            }
        }
    }

    static void getBackDist() {
        ArrayDeque<Pair<Integer, Integer>> ad = new ArrayDeque<>();
        ad.push(new Pair(n-1,m-1));
        bdist[n-1][m-1] = 1;
        while(!ad.isEmpty()) {
            Pair<Integer, Integer> f = ad.pollFirst();
            for(int i=0; i<4; i++) {
                int nx = f.x + dx[i];
                int ny = f.y + dy[i];
                if(!checkRange(nx,ny)) {
                    continue;
                }
                if(map[nx][ny]==wall) {
                    continue;
                }
                if(bdist[nx][ny]>0) {
                    continue;
                }
                ad.addLast(new Pair(nx,ny));
                bdist[nx][ny] = bdist[f.x][f.y] + 1;
            }
        }
    }

    static void getMinDist() {
        if(fdist[n-1][m-1]!=0) {
            answer = fdist[n - 1][m - 1];
        } else {
            answer = inf;
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j]!=wall) {
                    continue;
                }
                int front,back;
                front = back = (int)1e9;
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(!checkRange(nx,ny)) {
                        continue;
                    }
                    if(fdist[nx][ny]!=0){
                        front = Math.min(front,fdist[nx][ny]);
                    }
                    if(bdist[nx][ny]!=0) {
                        back = Math.min(back,bdist[nx][ny]);
                    }
                }
                answer = Math.min(answer, front+back+1);
            }
        }
        if(answer==inf) {
            answer = -1;
        }
    }

    public static void solve() {
        getFrontDist();
        getBackDist();
        getMinDist();
        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}

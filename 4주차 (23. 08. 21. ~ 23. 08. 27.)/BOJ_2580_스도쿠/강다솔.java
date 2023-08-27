package baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon_2580 {
    
    private static int blankNum, board[][];
    private static boolean flag;
    private static final int SIZE = 9;
    private static List<int[]> blank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[SIZE][SIZE];
        blank = new ArrayList<>();
        for (int i=0;i<SIZE;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<SIZE;j++) {
                board[i][j]=Integer.parseInt(st.nextToken());
                if (board[i][j]==0) {
                    blank.add(new int[]{i, j});
                    blankNum++;
                }
            }
        }

        flag=false;
        fillSudoku(0);
    }

    private static void fillSudoku(int index) {
        if (index==blankNum) {
            // 모든 빈칸 다 채웠을 때
            for (int i=0;i<SIZE;i++) {
                for (int j=0;j<SIZE;j++) {
                    System.out.print(board[i][j]+" ");
                }
                System.out.println();
            }
            flag=true;
            return ;
        }

        if (flag) return ;
        // 가로 세로 확인
        int[] curFill = blank.get(index);
        int x = curFill[0], y=curFill[1];
        int[] numbers = new int[SIZE+1];
        for (int i=0;i<SIZE;i++) {
            numbers[board[x][i]]=1;
            numbers[board[i][y]]=1;
        }

        // 3*3 확인
        int curx=x/3*3;
        int cury=y/3*3;
        for (int i=curx;i<curx+3;i++) {
            for (int j=cury;j<cury+3;j++) {
                numbers[board[i][j]]=1;
            }
        }
        for (int i=1;i<SIZE+1;i++) {
            if (numbers[i]==0 && !flag) {
                board[x][y]=i;
                fillSudoku(index+1);
                board[x][y]=0;
            }
        }
    }
}
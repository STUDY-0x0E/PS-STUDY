package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Snake {
	static class node{
		int x;
		int y;
		node front;
		node(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		node(int x, int y, node next){
			this(x, y);
			next.front = this;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		
		boolean[][] isApple = new boolean[N][N];
		
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			isApple[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = true;
		}
		
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		
		int[] cmdT = new int[L];
		String[] cmd = new String[L];
		
		for (int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			cmdT[i] = Integer.parseInt(st.nextToken());
			cmd[i] = st.nextToken();
		}
		
		boolean[][] isSnake = new boolean[N][N];
		isSnake[0][0] = true;
		
		node head = new node(0, 0);
		node tail = head;
		int[][] direc = {{0,1},{1,0},{0,-1},{-1,0}};
		int curd = 0;
		
		
		int t = 0;
		int p = 0;
		while(true) {
			if (p<L && cmdT[p]==t) {
				if (cmd[p].equals("D")) {
					if (curd==3) curd = 0;
					else curd++;
				}
				else {
					if (curd==0) curd = 3;
					else curd--;
				}
				p++;
			}
			t++;
			int x = head.x + direc[curd][0];
			int y = head.y + direc[curd][1];
		
			if (x<0 || x>=N || y<0 || y>=N) break;
			
			if (isApple[x][y]) {
				isApple[x][y] = false;
				if (isSnake[x][y]) break;
				isSnake[x][y] = true;
				head = new node(x, y, head);
			} else {
				if (isSnake[x][y]) break;
				isSnake[tail.x][tail.y] = false;
				isSnake[x][y] = true;
				head = new node(x, y, head);
				tail = tail.front;
			}
		}
		System.out.println(t);
	}

}

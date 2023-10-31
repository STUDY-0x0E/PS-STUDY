import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
	public static int N, m[][], friends[][];
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, -1, 0, 1 };
	public static Point selectedLocation;
	
	 public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	        N = Integer.parseInt(br.readLine());
	        m = new int[N][N];
	        friends = new int[N * N + 1][4];

	        for (int n = 0; n < N * N; n++) {
	            String[] input = br.readLine().split(" ");
	            int student = Integer.parseInt(input[0]);
	            for (int f = 0; f < 4; f++) {
	                friends[student][f] = Integer.parseInt(input[f + 1]);
	            }

	            selectedLocation = new Point(-1, -1);
	            findBestLocation(student);
	            m[selectedLocation.x][selectedLocation.y] = student;
	        }

	        int answer = calculateAnswer();
	        System.out.println(answer);
	    }

	    private static void findBestLocation(int student) {
	        int maxFriend = -1;
	        int maxBlank = -1;

	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if (m[i][j] == 0) {
	                    int friendCount = 0;
	                    int blankCount = 0;

	                    for (int d = 0; d < 4; d++) {
	                        int nx = i + dx[d];
	                        int ny = j + dy[d];

	                        if (inRange(nx, ny)) {
	                            if (contains(friends[student], m[nx][ny])) {
	                                friendCount++;
	                            } else if (m[nx][ny] == 0) {
	                                blankCount++;
	                            }
	                        }
	                    }

	                    if (friendCount > maxFriend || (friendCount == maxFriend && blankCount > maxBlank)) {
	                        maxFriend = friendCount;
	                        maxBlank = blankCount;
	                        selectedLocation.setLocation(i, j);
	                    }
	                }
	            }
	        }
	    }

	    private static int calculateAnswer() {
	        int satisfaction = 0;

	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                int student = m[i][j];
	                int friendCount = 0;

	                for (int d = 0; d < 4; d++) {
	                    int nx = i + dx[d];
	                    int ny = j + dy[d];

	                    if (inRange(nx, ny) && contains(friends[student], m[nx][ny])) {
	                        friendCount++;
	                    }
	                }

	                satisfaction += Math.pow(10, friendCount-1);
	            }
	        }

	        return satisfaction;
	    }
	    
	    private static boolean inRange(int nx, int ny) {
	        return nx >= 0 && ny >= 0 && nx < N && ny < N;
	    }

	    private static boolean contains(int[] arr, int value) {
	        for (int i = 0; i < arr.length; i++) {
	            if (arr[i] == value) {
	                return true;
	            }
	        }
	        return false;
	    }
	}

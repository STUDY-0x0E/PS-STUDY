import java.io.*;
import java.util.*;

class Area {
	int durability;
	boolean hasBox;

	Area(int durability) {
		this.durability = durability;
	}
}

public class B20055 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 벨트 길이 2N
		int k = Integer.parseInt(st.nextToken());// 내구도 0인 칸의 최대개수 제한

		List<Area> belt = new LinkedList<>();// belt durability
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * n; i++) {// n+1부터 아래쪽 벨트
			Area a = new Area(Integer.parseInt(st.nextToken()));
			belt.add(a);
		}

		int step = 0;
		int k_cnt = 0;// 내구도가 0인 칸의 개수
		while (k_cnt < k) {
			belt.add(0, belt.remove(belt.size() - 1));// 1. 원형큐->1칸 회전
			Area end = belt.get(n - 1);
			if (end.hasBox)// n에 도달하면 즉시 내린다.
				end.hasBox = false;

			for (int i = n - 1; i > 0; i--) {// 2.올려놓은 로봇 스스로 한칸 이동
				Area beltI = belt.get(i);
				Area beltI_1 = belt.get(i - 1);
				if (beltI_1.hasBox && !beltI.hasBox && beltI.durability > 0) {
					beltI_1.hasBox = false;
					beltI.hasBox = true;
					if (--beltI.durability == 0)
						++k_cnt;
					end = belt.get(n - 1);
					if (end.hasBox)
						end.hasBox = false;
				}
			}
			Area first = belt.get(0);
			if (first.durability > 0) {// 3.로봇 올리기
				first.hasBox = true;
				if (--first.durability == 0)
					++k_cnt;
			}
			step++;
		}
		System.out.println(step);// 진행 중이던 단계 출력
	}
}

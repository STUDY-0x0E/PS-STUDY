#include<bits/stdc++.h>
using namespace std;
int n, k;
int l;
// 시간에 이루어지는 방향전환
queue<pair<int, char>> q;
bool board[101][101];
queue<pair<int, int>> snake;

// 동 남 서 북
int dx[4] = { 0,1,0,-1 };
int dy[4] = { 1, 0,-1,0 };

int main() {
	ios::sync_with_stdio(0);
	cin.tie(NULL);
	cin >> n >> k;

	// 사과 위치
	for (int i = 0; i < k; i++) {
		int row, col;
		cin >> row >> col;
		board[row][col] = 1;
	}

	// 방향전환
	cin >> l;

	int t;
	char c;

	for (int i = 0; i < l; i++) {
		cin >> t >> c;
		q.push({ t,c });
	}

	// 시간과 처음 위치
	int answer = 0;
	snake.push({ 1,1 });

	// 뱀의 현재위치
	int x = 1;
	int y = 1;
	// 뱀의 이동방향 ( 동 )
	int dir = 0;
	// 뱀이 이동합니다.
	while (true) {
		// 방향전환이 남아있으면
		if (!q.empty()) {
			if (answer == q.front().first) {
				// 왼쪽 90도
				if (q.front().second == 'L') dir = (dir + 3) % 4;
				// 오른쪽 90도
				else if (q.front().second == 'D') dir = (dir + 1) % 4;

				q.pop();
			}
		}

		x = x + dx[dir];
		y = y + dy[dir];
		int snakeLength = snake.size();
		// 벽에 부딪힌다.
		if (x <= 0 || x > n || y <= 0 || y > n) break;
		// 본인의 몸에 부딪힌다.
		bool flag = false;
		for (int i = 0; i < snakeLength; i++) {
			pair<int, int> point = snake.front();
			snake.pop();
			if (x == point.first && y == point.second) flag = 1;
			snake.push(point);
		}
		if (flag) break;

		// 사과가 움직인 칸에 있는 지 확인
		if (!board[x][y]) snake.pop();
		else board[x][y] = 0;

		snake.push({ x,y });
		answer++;
	}

	cout << answer + 1;
}
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <climits>
using namespace std;

int dx[4] = {-1, 0, 0, 1};
int dy[4] = {0, -1, 1, 0};
int arr[21][21];
int SharkSize;
int n;
int minMove;
int answer;

// 현재 상어의 위치에서 우선너비탐색을 실시한다.
pair<int, int> BFS(int x, int y)
{
    // 움직인 거리, x y
    queue<pair<int, pair<int, int>>> q;

    // 방문확인
    bool Visit[21][21];

    // 최소 움직임으로 도달 가능한 먹이 위치
    vector<pair<int, int>> v;

    // 방문 확인 초기화
    for (int i = 0; i < 21; i++)
        for (int j = 0; j < 21; j++)
            Visit[i][j] = 0;

    arr[x][y] = 0;
    q.push({0, {x, y}});
    Visit[x][y] = 1;

    // 최소거리에 있는 모든 먹을 수 있는 먹이를 vector에 삽입
    while (!q.empty())
    {
        pair<int, int> cur = q.front().second;
        int move = q.front().first;
        int cur_x = cur.first;
        int cur_y = cur.second;
    
        // 움직인 거리가 먹을 수 있는 먹이의 거리보다 멀다.
        if (move == minMove)
            break;

        q.pop();

        for (int i = 0; i < 4; i++)
        {
            int next_x = cur_x + dx[i];
            int next_y = cur_y + dy[i];

            // 맵을 벗어남
            if (next_x < 0 || next_x >= n || next_y < 0 || next_y >= n)
                continue;

            // 이미 방문함
            if (Visit[next_x][next_y])
                continue;
            
            // 지나갈 수 없음 && 먹을 수 없음 ( 상어 크기보다 큰 물고기가 위치함 )
            if (arr[next_x][next_y] > SharkSize)
                continue;

            // 상어의 크기와 같거나 빈 칸임
            if (arr[next_x][next_y] == SharkSize || arr[next_x][next_y] == 0)
            {
                q.push({move + 1, {next_x, next_y}});
                Visit[next_x][next_y] = 1;
                continue;
            }

            // 먹을 수 있는 먹이임
            if (arr[next_x][next_y] < SharkSize && arr[next_x][next_y] != 0)
            {
                v.push_back({next_x, next_y});
                Visit[next_x][next_y] = 1;
                minMove = move + 1;
                continue;
            }
        }
    }

    // vector 안에 있는 먹이 중 ( 모두 거리는 같음 ) 가장 상단, 가장 왼쪽에 있는 먹이를 찾는다.
    int temp_x = 21;
    int temp_y = 21;

    for (int i = 0; i < v.size(); i++)
    {
        // 위쪽에 있는 경우 -> 무조건 우선
        if (temp_x > v[i].first)
        {
            temp_x = v[i].first;
            temp_y = v[i].second;
        }
        // 같은 높이에 있는 경우
        else if(temp_x == v[i].first){
            // 왼쪽에 있는 게 우선
            if(temp_y > v[i].second)
            {
                temp_x = v[i].first;
                temp_y = v[i].second;
            }
        }
    }
    return {temp_x, temp_y};
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(NULL);
    cin >> n;

    int x, y;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> arr[i][j];
            if (arr[i][j] == 9)
            {
                x = i;
                y = j;
            }
        }
    }

    SharkSize = 2;
    int Count = 0;
    minMove = INT_MAX;

    while (true)
    {
        pair<int, int> position = BFS(x, y);
        x = position.first;
        y = position.second;

        // 먹을 수 있는 먹이가 이제 없다.
        if (x == 21 || y == 21)
            break;

        // 먹으러 온 거리만큼 더 해준다.
        answer += minMove;
        minMove = INT_MAX;

        // 상어 위치
        arr[x][y] = 9;
        Count++;

        if (Count == SharkSize)
        {
            SharkSize++;
            Count = 0;
        }
    }

    cout << answer;

    // BFS를 수행, 1칸, 2칸 움직일 때 마다 먹을 수 있는 것이 적립되는 지 체크,
    // 먹을 수 있는 것이 적립된다면 거기서 BFS 종료. 이후 가장 가까운, 가장 위에, 가장 왼쪽에 있는 것을 먼저 먹는다.
}
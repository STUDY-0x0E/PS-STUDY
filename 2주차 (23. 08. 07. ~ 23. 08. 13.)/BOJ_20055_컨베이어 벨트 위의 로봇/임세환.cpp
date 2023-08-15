#include <iostream>
#include <vector>
using namespace std;

// 내구도, 로봇 존재 유무
vector<pair<int, bool>> con;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(NULL);
    int n, k;
    int num;
    cin >> n >> k;

    // 컨베이어 벨트 상단의 가장 좌측과 우측의 좌표값
    int left = 0;
    int right = n - 1;

    // 컨베이어 벨트의 내구도와 로봇 존재 여부
    for (int i = 0; i < n * 2; i++)
    {
        cin >> num;
        con.push_back({num, 0});
    }

    // 매 단계마다 행동은 다음과 같다.
    // 0. 로봇이 내리는 위치에 도달하면 그 즉시 내린다. 로봇이 올라가거나 이동하면 그 칸의 내구도가 1 감소한다.
    // 1. 각 칸이 한 칸씩 이동한다.
    // 2. 칸 위에 존재하는 각 로봇이 한 칸 씩 앞으로 이동한다. 로봇이 앞에 없어야하고, 내구도가 1 이상 남아있어야 한다.
    // 3. 올리는 위치 (left)에 로봇을 올려놓는다.
    // 4. 내구도가 0인 칸의 개수가 k개면 단계를 종료한다. 그렇지 않으면 반복한다.

    // 내구도가 0이 된 칸의 개수, 단계
    int answer = 0;
    int step = 0;

    while (answer < k)
    {
        step++;
        // 1. 각 칸이 한 칸씩 이동한다.
        // 나머지 연산에서 -1 % n은 n-1 % n과 같다.
        left = (left + 2 * n - 1) % (2 * n);
        right = (right + 2 * n - 1) % (2 * n);

        // 내리는 칸에 로봇이 있는 지 확인한다.
        if (con[right].second == 1)
        {
            // 로봇이 있다면 그 즉시 내린다.
            con[right].second = 0;
        }

        // 2 칸 위의 로봇이 한 칸씩 이동한다.
        // 가장 앞에 있는 로봇의 위치부터 앞으로 한 칸씩 이동한다. 이동이 가능하다면
        for (int i = (right + 2 * n - 1) % (2 * n); i != left; i = (i + 2 * n - 1) % (2 * n))
        {
            // 로봇이 존재한다면!?
            if (con[i].second)
            {
                // 다음 칸에 로봇이 존재하는 지 확인한다. 존재한다면 이동불가
                if (con[(i + 1) % (2 * n)].second)
                {
                    continue;
                }
                // 존재하지 않는다면
                else
                {
                    // 이동하려는 칸의 내구도가 1 이상이어야 이동가능하다.
                    if (con[(i + 1) % (2 * n)].first >= 1)
                    {
                        con[i].second = 0;
                        con[(i + 1) % (2 * n)].second = 1;
                        con[(i + 1) % (2 * n)].first--;
                        // 내구도가 0이 되었다면 카운트 추가한다.
                        if (con[(i + 1) % (2 * n)].first == 0)
                        {
                            answer++;
                        }
                    }
                    // 이동할 수 없으면
                    else
                    {
                        continue;
                    }
                }
            }
            // 로봇이 존재하지 않는다면?
            else
            {
                continue;
            }
        }

        // 마지막칸으로 이동한 경우, 그 즉시 내린다.
        if (con[right].second == 1)
        {
            con[right].second = 0;
        }

        // 3. 올리는 위치에 로봇을 올려놓는다.
        if (con[left].first >= 1)
        {
            con[left].second = 1;
            con[left].first--;
            // 내구도가 0이 되었다면 카운트 추가한다. 또한 로봇을 제거한다.
            if (con[left].first == 0)
            {
                answer++;
            }
        }
    }

    cout << step;
}
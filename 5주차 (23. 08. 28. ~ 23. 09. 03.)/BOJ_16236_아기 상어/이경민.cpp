#include<iostream>
#include<queue>
using namespace std;

int n;
int map[20][20];
bool visited[20][20];
pair<int,int> shark;
int shark_size = 2;
int shark_eat = 0;
int hour;
vector<pair<int,pair<int,int>>> fish;

void input() {
    cin>>n;
    for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++) {
            cin>>map[i][j];
            if(map[i][j]==9) {
                shark = {i,j};
            }
        }
    }
}

bool checkRange(int x,int y) {
    return x>=0 && y>=0 && x<n && y<n;
}

void bfs() {
    fill(&visited[0][0],&visited[19][20],false);
    queue<pair<pair<int,int>,int>> q;
    q.push({shark,0});
    visited[shark.first][shark.second] = true;
    map[shark.first][shark.second] = 0;
    fish.clear();

    while(!q.empty()) {
        int fx = q.front().first.first;
        int fy = q.front().first.second;
        int count = q.front().second;
        q.pop();

        for(int i=0; i<4; i++) {
            int nx = fx + "2011"[i] - '1';
            int ny = fy + "1120"[i] - '1';

            if(!checkRange(nx,ny)) {
                continue;
            }
            if(visited[nx][ny]) {
                continue;
            }
            if(map[nx][ny]>shark_size) {
                continue;
            }
            visited[nx][ny] = true;
            q.push({{nx,ny},count+1});
            if(map[nx][ny]<shark_size && map[nx][ny]!=0) {
                fish.push_back({count + 1,{nx, ny}});
            }
        }
    }
}

void solution() {
    while(1) {
        bfs();
        if(fish.empty()) {
            break;
        }
        sort(fish.begin(),fish.end());
        pair<int,int> next_pos = fish.front().second;
        int time = fish.front().first;
        map[next_pos.first][next_pos.second] = 0;
        hour += time;
        shark = next_pos;
        ++shark_eat;
        if(shark_eat == shark_size) {
            ++shark_size;
            shark_eat = 0;
        }
    }
    cout<<hour;
}

void solve() {
    input();
    solution();
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    ios_base::sync_with_stdio(false);

    solve();
}

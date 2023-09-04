#include<iostream>
#include<queue>
using namespace std;

struct edge {
    int x,y,weight;

    bool operator<(const edge & b) const {
        return weight > b.weight;
    }
};

const int inf = 2e9;
const int sz = 125;
int n,answer;
int map[sz][sz];
int dist[sz][sz];

bool checkRange(int x,int y) {
    return x>=0 && y>=0 && x<n && y<n;
}

void solution() {
    fill(&dist[0][0],&dist[sz-1][sz],inf);
    priority_queue<edge> pq;
    dist[0][0] = map[0][0];
    pq.push({0,0,dist[0][0]});

    while(!pq.empty()) {
        int fx = pq.top().x;
        int fy = pq.top().y;
        int weight = pq.top().weight;
        pq.pop();

        if(dist[fx][fy] < weight) {
            continue;
        }
        for(int i=0; i<4; i++) {
            int nx = fx + "2011"[i] - '1';
            int ny = fy + "1120"[i] - '1';

            if(!checkRange(nx,ny)) {
                continue;
            }

            if(dist[nx][ny]>weight + map[nx][ny]) {
                dist[nx][ny] = weight + map[nx][ny];
                pq.push({nx,ny,dist[nx][ny]});
            }
        }
    }
    answer=dist[n-1][n-1];
}

void solve() {
    int tc = 1;
    cin>>n;
    while(n) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                cin>>map[i][j];
            }
        }
        solution();
        cout<<"Problem "<<tc++<<": "<<answer<<'\n';
        cin>>n;
    }
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    ios_base::sync_with_stdio(false);

    solve();
}

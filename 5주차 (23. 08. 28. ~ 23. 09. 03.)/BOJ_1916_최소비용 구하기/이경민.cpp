#include<iostream>
#include<queue>
using namespace std;

struct edge {
    int dest,weight;

    bool operator<(const edge & b) const {
        return weight > b.weight;
    }
};

const int inf = 2e9;
const int sz = 1e3 + 1;
int n,m,s,d;
vector<edge> graph[sz];
int dist[sz];

void input() {
    cin>>n;
    cin>>m;
    int a,b,c;
    while(m--) {
        cin>>a>>b>>c;
        graph[a].push_back({b,c});
    }
    cin>>s>>d;
}

void solution() {
    fill(dist,dist+sz,inf);
    priority_queue<edge> pq;
    pq.push({s,0});
    dist[s] = 0;

    while(!pq.empty()) {
        edge now = pq.top();
        pq.pop();

        if(dist[now.dest] < now.weight) {
            continue;
        }

        for(auto g:graph[now.dest]) {
            if(g.weight+now.weight < dist[g.dest]) {
                dist[g.dest] = g.weight + now.weight;
                pq.push({g.dest, dist[g.dest]});
            }
        }
    }
    cout<<dist[d];
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

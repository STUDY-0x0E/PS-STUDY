#include<iostream>
#include<vector>
using namespace std;

struct edge {
    int src,dest,weight;

    bool operator<(const edge & b) const {
        return weight > b.weight;
    }
};

const int sz = 1e4 + 1;
int n,m,s,d;
int par[sz];
vector<edge> edges;

void input() {
    cin>>n>>m;
    int a,b,c;
    while(m--) {
        cin>>a>>b>>c;
        edges.push_back({a,b,c});
    }
    cin>>s>>d;
}

int find(int x) {
    if(par[x]==x) {
        return x;
    } else {
        return par[x] = find(par[x]);
    }
}

bool check(int x,int y) {
    int xroot = find(x);
    int yroot = find(y);
    return xroot == yroot;
}

void merge(int x,int y) {
    int xroot = find(x);
    int yroot = find(y);

    if(xroot == yroot) {
        return;
    } else {
        if(xroot>yroot) {
            swap(xroot,yroot);
        }
        par[xroot] = yroot;
    }
}

void solution() {
    sort(edges.begin(), edges.end());
    for(int i=1; i<=n; i++) {
        par[i] = i;
    }
    int answer = 2e9;
    for(auto e:edges) {
        if(check(e.src,e.dest)) {
            continue;
        }
        answer = e.weight;
        merge(e.dest,e.src);
        if(check(s,d)) {
            break;
        }
    }
    cout<<answer;
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

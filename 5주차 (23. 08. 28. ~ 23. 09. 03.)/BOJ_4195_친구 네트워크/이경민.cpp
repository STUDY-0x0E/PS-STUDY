#include<iostream>
#include<unordered_map>
#include<string>
using namespace std;

const int sz = 2e5 + 1;
int par[sz];
int people[sz];
int n;
string a, b;
int group_index = 0;
unordered_map<string, int> network;

void init() {
	group_index = 0;
	network.clear();
	fill(people, people + sz, 0);
	for (int i = 0; i < sz; i++) {
		par[i] = i;
	}
}

int find(int x) {
	if (par[x] == x) {
		return x;
	} else {
		return par[x] = find(par[x]);
	}
}

void merge(int x, int y) {
	int xroot = find(x);
	int yroot = find(y);
	if (xroot == yroot) {
		return;
	} else {
		if (xroot < yroot) {
			swap(xroot, yroot);
		}
		par[xroot] = yroot;
		people[yroot] += people[xroot];
		people[xroot] = 0;
	}
}

void solution() {
	cin >> n;
	while (n--) {
		cin >> a >> b;
		int a_index, b_index;
		a_index = network[a];
		b_index = network[b];
		if (!a_index && !b_index) {
			network[a] = ++group_index;
			network[b] = group_index;
			a_index = b_index = group_index;
			people[group_index] = 2;
		} else if (!a_index) {
			network[a] = ++group_index;
			people[group_index] = 1;
			a_index = group_index;
		} else if (!b_index) {
			network[b] = ++group_index;
			people[group_index] = 1;
			b_index = group_index;
		}
		merge(a_index, b_index);
		cout << people[find(a_index)] << '\n';
	}
}

void solve() {
	int t;
	cin >> t;
	while (t--) {
		init();
		solution();
	}
}

int main() {
	cin.tie(NULL);
	cout.tie(NULL);
	ios_base::sync_with_stdio(false);

	solve();
}

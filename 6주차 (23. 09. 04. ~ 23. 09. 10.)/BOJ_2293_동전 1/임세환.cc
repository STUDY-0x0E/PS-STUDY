#include<iostream>

using namespace std;

int n,k;
int coins[101];
int price[10001];

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);

    cin >> n >> k;
    for(int i =0;i<n;i++){
        cin >> coins[i];
    }

    price[0] = 1;
    for(int i =0;i<n;i++){
        for(int j =coins[i];j<=k;j++){
            price[j] += price[j-coins[i]];
        }
    }

    cout << price[k];
}
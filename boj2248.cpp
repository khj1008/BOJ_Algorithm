#include <iostream>
#include <algorithm>
#include <vector>
#include <cstdio>
#include <cstring>
using namespace std;
int N, L;
long long int I;
long long int dp[32][32];
vector<int> result;

long long int binary(int n, int m) {
	long long int &ret = dp[n][m];
	if (ret != -1)return ret;
	if (m == 0 || n == 0)return ret = 1;
	ret = binary(n - 1, m);
	if (m > 0)ret += binary(n - 1, m - 1);
	return ret;
}

void skip(int n, int m, long long int k) {
	if (n == 0)return;
	if (m == 0) {
		for (int i = 0; i < n; i++) {
			result.push_back(0);
		}
		return;
	}
	long long int pivot = binary(n - 1, m);
	if (k < pivot) {
		result.push_back(0);
		skip(n - 1, m, k);
	}
	else {
		result.push_back(1);
		skip(n - 1, m - 1, k - pivot);
	}
}




int main() {
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	cin >> N >> L >> I;
	memset(dp, -1, sizeof(dp));
	//binary(N, L);
	skip(N, L, I - 1);
	for (int i = 0; i < N; i++) {
		cout << result[i];
	}
	cout << "\n";
	return 0;
}
#include <iostream>
#include <functional>
#include <algorithm>
#include <vector>
#include <queue>
#include <string>
#include <set>
using namespace std;
int dp[101][101];
int go(int n, int m) {
	if (n < 0 || m < 0)return 0;
	if (dp[n][m] != -1)return dp[n][m];
	if (n + m == 1)return 1;
	dp[n][m] = 0;
	dp[n][m] = go(n - 1, m) + go(n, m - 1);
	if (dp[n][m] >= 1000000001)dp[n][m] = 1000000001;
	return dp[n][m];
}

void solution(int n, int m, int k) {
	for (int i = 0; i <= n; i++) {
		fill(dp[i], dp[i] + m + 1, -1);
	}
	string answer = "";
	if (go(n, m) < k) {
		cout << answer << "\n";
		return;
	}
	k -= 1;
	int an = n;
	int bn = m;
	string aa = "(";
	string bb = ")";
	for (int i = 0; i < n + m; i++) {
		if (bn == 0) {
			answer.append(aa);
			an--;
			continue;
		}
		if (an == 0) {
			answer.append(bb);
			continue;
		}
		int add = go(an - 1, bn);
		if (add <= k) {
			k -= add;
			bn--;
			answer.append(bb);
		}
		else {
			an--;
			answer.append(aa);
		}
	}
	cout << answer << "\n";

}


int main() {
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	int n, m, k;
	cin >> n >> m >> k;
	solution(n, m, k);
	return 0;
	
}
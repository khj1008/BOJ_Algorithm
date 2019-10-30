#include <iostream>
#include <algorithm>
using namespace std;
bool arr[300000];
int N;
int solve() {
	int result = 0;
	for (int i = 2; i <= 2 * N; i++) {
		for (int j = i * 2; j <= 2 * N; j += i) {
			if (!arr[j]) {
				arr[j] = true;
			}
		}
	}
	for (int i = N + 1; i <= 2 * N; i++) {
		if (!arr[i])result++;
	}
	return result;
}

int main() {
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	while (true) {
		cin >> N;
		if (N == 0)break;
		else cout<<solve()<<"\n";
	}
	return 0;
}
#include <iostream>
#include <algorithm>
using namespace std;
bool arr[1001];
int N, K;

int solve() {
	int k = 1;
	for (int i = 2; i <= N; i++) {
			for (int j = i; j <= N; j += i) {
				if (!arr[j]) {
					arr[j] = true;
					if (k == K)return j;
					k++;
				}
			}
	}
}

int main() {
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	cin >> N >> K;
	cout << solve() << "\n";
	return 0;
}
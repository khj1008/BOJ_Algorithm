#include <iostream>
#include <functional>
#include <algorithm>
#include <vector>
#include <queue>
#include <string>
#include <set>
using namespace std;

void solution(vector<vector<int>> matrix, int r) {
	int N = matrix.size();
	r = r % 4;
	for (int rot = 0; rot < r; rot++) {
		for (int i = 0; i < N / 2; i++) {
			vector<int> temp = matrix[i];
			for (int j = i; j < N - i; j++) {
				matrix[i][N - 1 - j] = matrix[j][i];
			}
			for (int j = i; j < N - i; j++) {
				matrix[j][i] = matrix[N - 1 - i][j];
			}
			for (int j = i; j < N - i; j++) {
				matrix[N - 1 - i][j] = matrix[N - 1 - j][N - 1 - i];
			}
			for (int j = i; j < N - i; j++) {
				matrix[N - 1 - j][N - 1 - i] = temp[N - 1 - j];
			}
		}
	}
	for (int i = 0; i < matrix.size(); i++) {
		for (int j = 0; j < matrix[i].size(); j++) {
			cout << matrix[i][j] << " ";
		}
		cout << "\n";
	}
}


int main() {
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	int N, R;
	cin >> N>>R;
	vector<vector<int>> tmp;
	tmp.resize(N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			int n;
			cin >> n;
			tmp[i].push_back(n);
		}
	}
	solution(tmp, R);
	return 0;
	
}
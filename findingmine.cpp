#include <iostream>
#include <functional>
#include <algorithm>
#include <vector>
#include <queue>
#include <string>
#include <set>
using namespace std;


void print(vector<string> map) {
	for (int i = 0; i < map.size(); i++) {
		for (int j = 0; j < map[i].size(); j++) {
			cout << map[i][j];
		}
		cout << "\n";
	}
}

void solution(vector<string> map, int x, int y) {
	int num[100][100];
	int N = map.size();
	int M = map[0].size();
	int dx[] = { -1,-1,-1,0,1,1,1,0 };
	int dy[] = { -1,0,1,1,1,0,-1,-1 };
	if (map[x][y] == 'M') {
		map[x][y] = 'X';
		print(map);
		return;
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			fill(num[i], num[i] + M, 0);
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j] == 'M') {
				num[i][j] = -1;
				continue;
			}
			for (int k = 0; k < 8; k++) {
				int nx = i + dx[k];
				int ny = j + dy[k];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if (map[nx][ny] == 'M')num[i][j]++;
				}
			}
		}
	}

	if (num[x][y] > 0) {
		for (int i = 0; i < map.size(); i++) {
			for (int j = 0; j < map[i].size(); j++) {
				map[i][j] = 'E';
			}
		}
		map[x][y] = (char)('0' + num[x][y]);
		print(map);
		return;
	}
	queue<pair<int, int>> q;
	bool visited[100][100];
	for (int i = 0; i < N; i++) {
		fill(visited[i], visited[i] + M, false);
	}
	visited[x][y] = true;
	map[x][y] = 'B';
	q.push(pair<int, int>(x, y));
	while (!q.empty()) {
		int qsize = q.size();
		for (int i = 0; i < qsize; i++) {
			pair<int, int> p = q.front(); q.pop();
			for (int j = 0; j < 8; j++) {
				int nx = p.first + dx[j];
				int ny = p.second + dy[j];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if (num[nx][ny] == 0 && !visited[nx][ny]) {
						map[nx][ny] = 'B';
						q.push(pair<int, int>(nx, ny));
						visited[nx][ny] = true;
					}
					else if (!visited[nx][ny]) {
						map[nx][ny] = (char)('0' + num[nx][ny]);
						visited[nx][ny] = true;
					}
				}
			}
		}
	}
	print(map);

}


int main() {
	cin.tie(NULL);
	ios::sync_with_stdio(false);
	int N, x, y;
	cin >> N >> x >> y;
	vector<string> tmp;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;
		tmp.push_back(s);
	}
	solution(tmp,x,y);
	return 0;
	
}
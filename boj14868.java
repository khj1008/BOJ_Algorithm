import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class pair implements Comparable{
	int first;
	int second;
	public pair(int f, int s) {
		first=f;
		second=s;
	}
	
	static pair of(int L,int R) {
		return new pair(L,R);
	}
	@Override
	public int compareTo(Object obj) {
		pair tmp=(pair)obj;
		if(first>tmp.first)return 1;
		else if(first==tmp.first) {
			if(second>tmp.second)return 1;
			else if(second==tmp.second)return 0;
			else return -1;
		}
		else return -1;
	}	
	
	
}

public class Main {
	static int N, K;
	static int[][] map=new int[2001][2001];
	static int[] p =new int[100001];
	static Queue<pair> q=new LinkedList<pair>();
	static int[] dx= {0,0,1,-1};
	static int[] dy= {1,-1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		for(int i=0; i<K; i++) {
			st=new StringTokenizer(br.readLine());
			int x,y;
			x=Integer.parseInt(st.nextToken())-1;
			y=Integer.parseInt(st.nextToken())-1;
			map[x][y]=i+1;
			q.offer(pair.of(x, y));
			p[i+1]=-1;
		}
		bfs();
	}
	
	static int find(int n) { //루트 찾는 함수
		if(p[n]<0)return n;
		p[n]=find(p[n]);
		return p[n];
	}
	
	static void merge(int a, int b) { //하나의 그룹으로 통합
		a=find(a);
		b=find(b);
		if(a==b)return;
		p[a]+=p[b];
		p[b]=a;
	}
	
	
	static void first() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]!=0) {
					for(int k=0; k<4; k++) {
						int nx=i+dx[k];
						int ny=j+dy[k];
						if(nx>=0 && nx<N && ny>=0 && ny<N) {
							if(map[nx][ny]!=0 && map[i][j]!=map[nx][ny]) {
								merge(map[i][j],map[nx][ny]);
							}
						}
					}
				}
			}
		}
	}
	
	static void edge() {
		int qsize=q.size();
		for(int i=0; i<qsize; i++) {
			pair curr=q.poll();
			for(int k=0; k<4; k++) {
				int nx=curr.first+dx[k];
				int ny=curr.second+dy[k];
				if(nx>=0 && nx<N && ny>=0 && ny<N) {
					if(map[nx][ny]!=0 && map[curr.first][curr.second]!=map[nx][ny]) {
						merge(map[curr.first][curr.second],map[nx][ny]);
					}
				}
			}
			q.offer(curr);
		}
	}
	
	static void bfs() {
		int result=0;
		while(!q.isEmpty()) {
			edge();
			if(p[find(1)]==-K) {
				System.out.println(result);
				return;
			}
			result++;
			int qsize=q.size();
			for(int i=0; i<qsize; i++) {
				pair curr=q.poll();
				
				for(int j=0; j<4; j++) {
					int nx=curr.first+dx[j];
					int ny=curr.second+dy[j];
					if(nx>=0 && nx<N && ny>=0 && ny<N) {
						if(map[nx][ny]==0) {
							map[nx][ny]=map[curr.first][curr.second];
							q.offer(pair.of(nx, ny));
						}
						else {
							merge(map[curr.first][curr.second],map[nx][ny]);
						}
					}
				}
			}
			
			
			
		}
		System.out.println(result);
	}
	
}

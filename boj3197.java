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
	static int R, C;
	static char[][] map=new char[1500][1500];
	static int[][] sector=new int[1500][1500];
	static int[] dx= {0,0,1,-1};
	static int[] dy= {1,-1,0,0};
	static int[] p=new int[2250000];
	static int A,B;
	static boolean[][] visited=new boolean[1500][1500];
	static Queue<pair> w=new LinkedList<pair>();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		p[0]=-2;
		for(int i=0; i<R; i++) {
			st=new StringTokenizer(br.readLine());
			String tmp=st.nextToken();
			for(int j=0; j<C; j++) {
				map[i][j]=tmp.charAt(j);
			}
		}
		bfsAll();
		System.out.println(spreadwater());
		
	}
	
	static void bfsAll() {
		int sec=1;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(!visited[i][j] && map[i][j]!='X') {
					bfs(i,j,sec);
					p[sec]=-1;
					sec++;
					
				}
				if(map[i][j]!='X') {
					for(int d=0; d<4; d++) {
						int nx=i+dx[d];
						int ny=j+dy[d];
						if(nx>=0 && nx<R && ny>=0 && ny<C) {
							if(map[nx][ny]=='X') {
								w.offer(pair.of(i, j));
								break;
							}
						}
					}
				}
			}
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]=='L') {
					if(A==0)A=sector[i][j];
					else B=sector[i][j];
				}
			}
		}
	}
	
	static void bfs(int x, int y, int sec) {
		Queue<pair> q=new LinkedList<pair>();
		q.offer(pair.of(x, y));
		visited[x][y]=true;
		sector[x][y]=sec;
		while(!q.isEmpty()) {
			int qsize=q.size();
			for(int i=0; i<qsize; i++) {
				pair curr=q.poll();
				for(int j=0; j<4; j++) {
					int nx = curr.first+dx[j];
					int ny = curr.second+dy[j];
					if(nx>=0 && nx<R && ny>=0 && ny<C){
						if(!visited[nx][ny] && map[nx][ny]!='X') {
							visited[nx][ny]=true;
							q.offer(pair.of(nx, ny));
							sector[nx][ny]=sec;
						}
					}
				}
			}
		}
	}
	
	static int spreadwater() {
		int result=0;
		while(!w.isEmpty()) {
			check();
			if(find(A)==find(B))break;
			result++;
			int qsize=w.size();
			for(int i=0; i<qsize; i++) {
				pair curr=w.poll();
				for(int j=0; j<4; j++) {
					int nx= curr.first+dx[j];
					int ny=curr.second+dy[j];
					if(nx>=0 && nx<R && ny>=0 && ny<C){
						if(map[nx][ny]=='X') {
							w.offer(pair.of(nx, ny));
							map[nx][ny]='.';
							sector[nx][ny]=sector[curr.first][curr.second];
						}
						else {
							if(sector[curr.first][curr.second]!=sector[nx][ny] && find(sector[curr.first][curr.second]) != find(sector[nx][ny])) {
								merge(sector[curr.first][curr.second],sector[nx][ny]);
							}
						}
					}
				}
			}
		
		}
		return result;
	}
	
	static void check() {
		int qsize=w.size();
		for(int i=0; i<qsize; i++) {
			pair curr=w.poll();
			for(int j=0; j<4; j++) {
				int nx= curr.first+dx[j];
				int ny=curr.second+dy[j];
				if(nx>=0 && nx<R && ny>=0 && ny<C){
					if(map[nx][ny]!='X'&&find(sector[curr.first][curr.second])!=find(sector[nx][ny])) {
						merge(sector[curr.first][curr.second],sector[nx][ny]);
					}
				}
			}
			w.offer(curr);
		}
	}
	
	static int find(int n) {
		if(p[n]<0)return n;
		p[n]=find(p[n]);
		return p[n];
	}
	
	static void merge(int a, int b) {
		a=find(a);
		b=find(b);
		if(a==b)return;
		p[b]=a;
	}

}

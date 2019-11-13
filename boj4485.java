import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class pair {
	int left;
	int right;
	public pair(int l,int r) {
		left=l;
		right=r;
	}
	static pair of(int l, int r) {
		return new pair(l,r);
	}
}

public class Main {
	final static int INF=150000;
	static int N;
	static int[][] cost=new int[126][126];
	static int[] dx= {0,0,-1,1};
	static int[] dy= {1,-1,0,0};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int p=1;
		while(true) {
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			if(N==0)break;
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					cost[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("Problem "+p+": "+solve());
			p++;
		}
	}
	
	static int solve() {
		int[][] dist=new int[N][N];
		for(int i=0; i<N; i++) {
			Arrays.fill(dist[i], INF);
		}
		boolean[][] visited=new boolean[N][N];
		Queue<pair> q=new LinkedList<pair>();
		dist[0][0]=cost[0][0];
		q.offer(pair.of(0, 0));
		while(!q.isEmpty()) {
			pair curr;
			while(true) {
				curr=q.poll();
				if(q.isEmpty() || !visited[curr.left][curr.right])break;
			}
			if(visited[curr.left][curr.right])break;
			visited[curr.left][curr.right]=true;
			for(int i=0; i<4; i++) {
				int nx=curr.left + dx[i];
				int ny=curr.right + dy[i];
				if(nx>=0 && nx<N && ny>=0 && ny<N) {
					if(dist[nx][ny]>dist[curr.left][curr.right]+cost[nx][ny]) {
						dist[nx][ny]=dist[curr.left][curr.right]+cost[nx][ny];
						visited[nx][ny]=false;
						q.offer(pair.of(nx, ny));
					}
				}
			}
		}
		return dist[N-1][N-1];
		
	}
	
}

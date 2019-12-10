import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

class pair implements Comparable{
	int first;
	int second;
	int dist;
	
	public pair(int f,int s, int d) {
		first=f;
		second=s;
		dist=d;
	}
	static pair of(int f, int s, int d) {
		return new pair(f, s, d);
	}
	@Override
	public int compareTo(Object obj) {
		pair tmp=(pair)obj;
		if(dist>tmp.dist)return 1;
		else if(dist==tmp.dist)return 0;
		else return -1;
	}
	
	
}


public class Main {
	static int N,M;
	final static int INF=1000000000;
	static int[][] dist=new int[101][101];
	static ArrayList<ArrayList<Integer>> adj=new ArrayList<ArrayList<Integer>>();
	static ArrayList<Integer> result=new ArrayList<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int a,b;
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			dist[a][b]=1;
			dist[b][a]=1;
		}
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					dist[i][j]=Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
			}
		}
		
		boolean[] visited=new boolean[N+1];
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				ArrayList<Integer> tmp=new ArrayList<Integer>();
				tmp.add(i);
				visited[i]=true;
				for(int j=1; j<=N; j++) {
					if(i!=j && dist[i][j]!=INF) {
						tmp.add(j);
						visited[j]=true;
					}
				}
				adj.add(tmp);
			}
		}
		
		for(int i=0; i<adj.size(); i++) {
			int MIN=INF;
			int present=0;
			for(int curr:adj.get(i)) {
				int MAX=0;
				for(int j=1; j<=N; j++) {
					if(dist[curr][j]!=INF)MAX=Math.max(MAX, dist[curr][j]);
				}
				if(MIN>MAX) {
					MIN=MAX;
					present=curr;
				}
			}
			result.add(present);
		}
		
		System.out.println(adj.size());
		result.sort(null);
		for(int i=0; i<result.size(); i++) {
			System.out.println(result.get(i));
		}
	
	}
	
	static void init() {
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				if(i==j)dist[i][j]=0;
				else dist[i][j]=INF;
			}
		}
	}
	
	

	
	
}

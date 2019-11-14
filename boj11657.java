import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class pair implements Comparable{
	int first;
	int second;
	public pair(int a,int b) {
		first=a;
		second=b;
	}
	static pair of(int f,int s) {
		return new pair(f,s);
	}
	
	@Override
	public int compareTo(Object o) {
		pair p=(pair)o;
		if(this.first>p.first)return 1;
		else if(this.first==p.first) {
			if(this.second>p.second)return 1;
			else if(this.second==p.second)return 0;
			else return -1;
		}
		else return -1;
	}
	
	
}
public class Main {
	static int N,M;
	static ArrayList<ArrayList<pair>> adj=new ArrayList<ArrayList<pair>>();
	final static int INF=5000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		for(int i=0; i<=N; i++) {
			adj.add(new ArrayList<pair>());
		}
		M=Integer.parseInt(st.nextToken());
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int a,b,c;
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			adj.get(a).add(pair.of(b, c));
		}
		solve();
	}
	
	static void solve() {
		int[] dist=new int[N+1];
		Arrays.fill(dist, INF);
		boolean minus=false;
		dist[1]=0;
		for(int i=0; i<N; i++) {
			for(int j=1; j<=N; j++) {
				for(pair p:adj.get(j)) {
					int next=p.first,d=p.second;
					if(dist[j]!=INF && dist[next]>dist[j]+d) {
						dist[next]=dist[j]+d;
						if(i==N-1)minus=true;
					}
				}
			}
		}
		
		if(minus)System.out.println(-1);
		else {
			for(int i=2; i<=N; i++) {
				if(dist[i]==INF)System.out.println(-1);
				else System.out.println(dist[i]);
			}
		}
		
	}
	
	
	
	
	
}

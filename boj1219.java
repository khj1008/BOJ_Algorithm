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
	static int N, M, S,E;
	final static int INF=-100000000;
	static ArrayList<ArrayList<pair>> adj=new ArrayList<ArrayList<pair>>();
	static int[] cost=new int[100];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		for(int i=0; i<N;i++) {
			adj.add(new ArrayList<pair>());
		}
		S=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int a,b,c;
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			adj.get(a).add(pair.of(b, c));
		}
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			cost[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<adj.get(i).size(); j++) {
				int dest=adj.get(i).get(j).first;
				adj.get(i).get(j).second=cost[dest]-adj.get(i).get(j).second;
			}
		}
		solution();
		
	}
	
	static void solution() {
		boolean isGee=false;
		long[] dist=new long[N];
		Arrays.fill(dist, INF);
		dist[S]=cost[S];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(pair p:adj.get(j)) {
					int next=p.first,d=p.second;
					if(dist[j]!=INF && dist[next]<dist[j]+d) {
						dist[next]=dist[j]+d;
						if(i==N-1 && solution2(j,E)) {
							isGee=true;
							break;
						}
					}
				}
			}
		}
		if(dist[E]==INF)System.out.println("gg");
		else if(isGee)System.out.println("Gee");
		else System.out.println(dist[E]);
	}
	static boolean solution2(int s, int e) {
		long[] dist=new long[N];
		Arrays.fill(dist, INF);
		dist[s]=cost[s];
		for(int i=0; i<N-1; i++) {
			for(int j=0; j<N; j++) {
				for(pair p:adj.get(j)) {
					int next=p.first,d=p.second;
					if(dist[j]!=INF && dist[next]<dist[j]+d) {
						dist[next]=dist[j]+d;
					}
				}
			}
		}
		if(dist[e]!=INF)return true;
		else return false;
	}
	
	
	
}

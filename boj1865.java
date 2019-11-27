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
	static int N, M, W;
	final static int INF=5000000;
	static ArrayList<ArrayList<pair>> adj;
	static boolean[] visited=new boolean[501];
	public static void main(String[] args) throws IOException {
		int TC;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		TC=Integer.parseInt(st.nextToken());
		for(int i=0; i<TC; i++) {
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			adj=new ArrayList<ArrayList<pair>>();
			for(int j=0; j<=N; j++) {
				adj.add(new ArrayList<pair>());
			}
			M=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			int S,E,T;
			for(int j=0; j<M; j++) {
				st=new StringTokenizer(br.readLine());
				S=Integer.parseInt(st.nextToken());
				E=Integer.parseInt(st.nextToken());
				T=Integer.parseInt(st.nextToken());
				adj.get(S).add(pair.of(E,T));
				adj.get(E).add(pair.of(S, T));
			}
			for(int j=0; j<W; j++) {
				st=new StringTokenizer(br.readLine());
				S=Integer.parseInt(st.nextToken());
				E=Integer.parseInt(st.nextToken());
				T=Integer.parseInt(st.nextToken());
				adj.get(S).add(pair.of(E, -T));
			}
			boolean result=false;
			Arrays.fill(visited, false);
			for(int j=1; j<=N; j++) {
				if(!visited[j])result=solution(j);
				if(result) {
					System.out.println("YES");
					break;
				}
			}
			if(!result)System.out.println("NO");
			
		}
		
	}
	
	static boolean solution(int n) {
		
		int[] dist=new int[N+1];
		Arrays.fill(dist, INF);
		dist[n]=0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				visited[j]=true;
				for(pair p:adj.get(j)) {
					int next=p.first,d=p.second;
					if(dist[j]!=INF && dist[next]>dist[j]+d) {
						dist[next]=dist[j]+d;
						if(i==N)return true;
					}
				}
			}
		}
		if(dist[n]<0)return true;
		else return false;
	}
	
	
	
}

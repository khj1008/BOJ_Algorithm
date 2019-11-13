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
	static int N,M,X;
	static ArrayList<ArrayList<pair>> adj=new ArrayList<ArrayList<pair>>();
	final static int INF=100000;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		for(int i=0; i<=N; i++) {
			adj.add(new ArrayList<pair>());
		}
		M=Integer.parseInt(st.nextToken());
		X=Integer.parseInt(st.nextToken());
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int a, b, c;
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			adj.get(a).add(pair.of(b, c));
		}
		int result=0;
		for(int i=1; i<=N; i++) {
			result=Math.max(solve(i,X)+solve(X,i), result);
		}
		System.out.println(result);
		
		
	}
	
	
	static int solve(int s, int e) {
		int[] dist=new int[N+1];
		boolean[] visited=new boolean[N+1];
		Arrays.fill(dist, INF);
		PriorityQueue<pair> pq=new PriorityQueue<pair>();
		dist[s]=0;
		pq.offer(pair.of(0, s));
		while(!pq.isEmpty()) {
			int curr;
			while(true) {
				curr=pq.poll().second;
				if(pq.isEmpty() || !visited[curr])break;
			}
			if(visited[curr])break;
			visited[curr]=true;
			for(pair p:adj.get(curr)) {
				int next=p.first,d=p.second;
				if(dist[next]>dist[curr]+d) {
					dist[next]=dist[curr]+d;
					pq.offer(pair.of(dist[next], next));
				}
			}
		}
		return dist[e];
		
	}
	
	
}

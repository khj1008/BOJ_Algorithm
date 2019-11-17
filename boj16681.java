import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class pair implements Comparable{
	long first;
	int second;
	public pair(long a,int b) {
		first=a;
		second=b;
	}
	static pair of(long f,int s) {
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
	final static long INF=1000000000000L;
	static int N,M,D,E;
	static int[] hi=new int[100001];
	static long[] go=new long[100001];
	static long[] down=new long[100001];
	static ArrayList<ArrayList<pair>> adj=new ArrayList<ArrayList<pair>>();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		for(int i=0; i<=N; i++) {
			adj.add(new ArrayList<pair>());
		}
		M=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			hi[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			adj.get(a).add(pair.of(b, c));
			adj.get(b).add(pair.of(a, c));
		}
		Arrays.fill(go, INF);
		Arrays.fill(down, INF);
		solve();
	}
	
	static void solve() {
		getDist1(1);
		getDist2(N);
		long result=-INF;
		for(int i=2; i<N; i++) {
			if(go[i]==INF || down[i]==INF)continue;
			result=Math.max(result, hi[i]*E-(go[i]+down[i]));
		}
		if(result==-INF)System.out.println("Impossible");
		else System.out.printf("%d",result);
	}
	
	
	static void getDist1(int start) {
		boolean[] visited=new boolean[N+1];
		PriorityQueue<pair> pq=new PriorityQueue<pair>();
		go[start]=0;
		pq.offer(pair.of(0, start));
		while(!pq.isEmpty()) {
			pair curr;
			while(true) {
				curr=pq.poll();
				if(pq.isEmpty() || !visited[curr.second])break;
			}
			if(visited[curr.second])break;
			visited[curr.second]=true;
			for(pair p:adj.get(curr.second)) {
				int next=(int)p.first, d=p.second;
				if(hi[curr.second]<hi[next]) {
					if(go[next]>go[curr.second]+d*D) {
						go[next]=go[curr.second]+d*D;
						pq.offer(pair.of(go[next], next));
					}
				}
			}
		}
	}
	
	static void getDist2(int start) {
		boolean[] visited=new boolean[N+1];
		PriorityQueue<pair> pq=new PriorityQueue<pair>();
		down[start]=0;
		pq.offer(pair.of(0, start));
		while(!pq.isEmpty()) {
			pair curr;
			while(true) {
				curr=pq.poll();
				if(pq.isEmpty() || !visited[curr.second])break;
			}
			if(visited[curr.second])break;
			visited[curr.second]=true;
			for(pair p:adj.get(curr.second)) {
				int next=(int)p.first, d=p.second;
				if(hi[curr.second]<hi[next]) {
					if(down[next]>down[curr.second]+d*D) {
						down[next]=down[curr.second]+d*D;
						pq.offer(pair.of(down[next], next));
					}
				}
			}
			
		}
	}
	
}

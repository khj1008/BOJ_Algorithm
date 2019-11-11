import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class pair implements Comparable<pair>{
	int left;
	int right;
	public pair(int l,int r) {
		left=l;
		right=r;
	}
	static pair of(int l, int r) {
		return new pair(l,r);
	}
	
	@Override
	public int compareTo(pair p) {
		if(left>p.left)return 1;
		else if(left==p.left) {
			if(right>p.right)return 1;
			else if(right==p.right)return 0;
			else return -1;
		}
		else return -1;
	}
}

public class Main {
	final static int MAX_V=20000;
	final static int INF=1000000;
	
	public static void main(String[] args) throws IOException {
		int N,M;
		int S,D;
		ArrayList<ArrayList<pair>> adj=new ArrayList<ArrayList<pair>>();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		for(int i=0; i<=N; i++) {
			adj.add(new ArrayList<pair>());
		}
		st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int u,v,w;
			u=Integer.parseInt(st.nextToken());
			v=Integer.parseInt(st.nextToken());
			w=Integer.parseInt(st.nextToken());
			adj.get(u).add(pair.of(v, w));
		}
		st=new StringTokenizer(br.readLine());
		S=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		
		int[] dist=new int[N+1];
		Arrays.fill(dist,INF);
		boolean[] visited=new boolean[N+1];
		PriorityQueue<pair> pq=new PriorityQueue<pair>();
		dist[S]=0;
		pq.offer(pair.of(0, S));
		while(!pq.isEmpty()) {
			int curr;
			while(true) {
				curr=pq.poll().right;
				if(pq.isEmpty() || !visited[curr])break;
			}
			if(visited[curr])break;
			visited[curr]=true;
			for(pair p:adj.get(curr)) {
				int next=p.left,d=p.right;
				if(dist[next]>dist[curr]+d) {
					dist[next]=dist[curr]+d;
					pq.offer(pair.of(dist[next], next));
				}
			}
		}
		
		System.out.println(dist[D]);
		
	}
	

	
	
}

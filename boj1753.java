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
	final static int INF=1000000000;
	
	public static void main(String[] args) throws IOException {
		int V,E,K;
		ArrayList<ArrayList<pair>> adj=new ArrayList<ArrayList<pair>>();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		K=Integer.parseInt(st.nextToken());
		for(int i=0; i<=V; i++) {
			adj.add(new ArrayList<pair>());
		}
		for(int i=0; i<E; i++) {
			st=new StringTokenizer(br.readLine());
			int u,v,w;
			u=Integer.parseInt(st.nextToken());
			v=Integer.parseInt(st.nextToken());
			w=Integer.parseInt(st.nextToken());
			adj.get(u).add(pair.of(v, w));
		}
		
		int[] dist=new int[V+1];
		Arrays.fill(dist, INF);
		boolean[] visited=new boolean[V+1];
		PriorityQueue<pair> pq=new PriorityQueue<pair>();
		dist[K]=0;
		pq.offer(pair.of(0, K));
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
		for(int i=1; i<=V; i++) {
			if(dist[i]==INF)System.out.println("INF");
			else System.out.println(dist[i]);
		}
		
		
	}
	

	
	
}

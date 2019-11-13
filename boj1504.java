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
	final static int INF=799001;
	static ArrayList<ArrayList<pair>> adj=new ArrayList<ArrayList<pair>>();
	public static void main(String[] args) throws IOException {
		int N,E;
		int A,B;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		for(int i=0; i<=N; i++) {
			adj.add(new ArrayList<pair>());
		}
		E=Integer.parseInt(st.nextToken());
		for(int i=0; i<E; i++) {
			st=new StringTokenizer(br.readLine());
			int a,b,c;
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			adj.get(a).add(pair.of(b, c));
			adj.get(b).add(pair.of(a, c));
		}
		st=new StringTokenizer(br.readLine());
		A=Integer.parseInt(st.nextToken());
		B=Integer.parseInt(st.nextToken());
		int result=Math.min(solve(1,A)+solve(A,B)+solve(B,N), solve(1,B)+solve(B,A)+solve(A,N));
		if(result>INF)System.out.println(-1);
		else System.out.println(result);
	}
	
	static int solve(int s, int e) {
		int[] dist=new int[801];
		Arrays.fill(dist,INF);
		boolean[] visited=new boolean[801];
		PriorityQueue<pair> pq=new PriorityQueue<pair>();
		dist[s]=0;
		pq.offer(pair.of(0, s));
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
		return dist[e];
	}
	
	
}

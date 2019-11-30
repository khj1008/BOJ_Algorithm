import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class pair implements Comparable{
	int first;
	int second;
	int distance;
	public pair(int f, int s, int dis) {
		first=f;
		second=s;
		distance=dis;
	}
	
	static pair of(int L,int R,int D) {
		return new pair(L,R,D);
	}
	@Override
	public int compareTo(Object obj) {
		pair tmp=(pair)obj;
		if(distance<tmp.distance)return 1;
		else return -1;
	}	
	
	
}

public class Main {
	static int P,W;
	static int C,V;
	static int[] p=new int[1001];
	static PriorityQueue<pair> pq=new PriorityQueue<pair>();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		P=Integer.parseInt(st.nextToken());
		W=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		C=Integer.parseInt(st.nextToken());
		V=Integer.parseInt(st.nextToken());
		Arrays.fill(p, -1);
		for(int i=0; i<W; i++) {
			int a,b,c;
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			pq.offer(pair.of(a,b,c));
		}
		solve();
	}
	
	static void solve() {
		while(!pq.isEmpty()) {
			pair curr=pq.poll();
			merge(curr.first,curr.second);
			if(find(C)==find(V)) {
				System.out.println(curr.distance);
				break;
			}
		}
	}
	
	static void merge(int a, int b) {
		a=find(a);
		b=find(b);
		if(a==b)return;
		p[b]=a;
	}
	
	static int find(int n) {
		if(p[n]<0)return n;
		p[n]=find(p[n]);
		return p[n];
	}
	

}

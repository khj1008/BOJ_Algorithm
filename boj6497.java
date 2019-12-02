import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
	static int[] p=new int[200001];
	static PriorityQueue<pair> pq=new PriorityQueue<pair>();
	static int MAX;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			Arrays.fill(p, -1);
			pq.clear();
			if(N+M==0)return;
			MAX=0;
			for(int i=0; i<M; i++) {
				st=new StringTokenizer(br.readLine());
				int a,b,c;
				a=Integer.parseInt(st.nextToken());
				b=Integer.parseInt(st.nextToken());
				c=Integer.parseInt(st.nextToken());
				MAX+=c;
				pq.offer(pair.of(a, b, c));
			}
			solve();
		}
	}
	static void solve() {
		int sum=0;
		while(!pq.isEmpty()) {
			pair curr=pq.poll();
			if(merge(curr.first,curr.second))sum+=curr.dist;
		}
		System.out.println(MAX-sum);
	}
	
	static int find(int n) {
		if(p[n]<0)return n;
		p[n]=find(p[n]);
		return p[n];
	}
	
	static boolean merge(int a, int b) {
		a=find(a);
		b=find(b);
		if(a==b)return false;
		p[b]=a;
		return true;
		
	}

}

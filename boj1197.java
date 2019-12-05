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
	static int V,E;
	static int[] p=new int[10001];
	static PriorityQueue<pair> pq=new PriorityQueue<pair>();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		V=Integer.parseInt(st.nextToken());
		Arrays.fill(p,-1);
		E=Integer.parseInt(st.nextToken());
		for(int i=0; i<E; i++) {
			st=new StringTokenizer(br.readLine());
			int a,b,c;
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			pq.offer(pair.of(a, b, c));
		}
		int result=0;
		int n=0;
		while(!pq.isEmpty()) {
			pair curr=pq.poll();
			if(merge(curr.first,curr.second)) {
				n++;
				result+=curr.dist;
			}
			if(n==V-1)break;
		}
		System.out.println(result);
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

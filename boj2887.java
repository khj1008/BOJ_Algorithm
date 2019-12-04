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

class planet implements Comparable{
	int number;
	int pos;
	
	public planet(int n, int p) {
		number=n;
		pos=p;
	}
	static planet of(int n, int p) {
		return new planet(n,p);
	}
	@Override
	public int compareTo(Object obj) {
		planet tmp=(planet)obj;
		if(pos>tmp.pos)return 1;
		else if(pos==tmp.pos)return 0;
		else return -1;
	}
	
	
}

public class Main {
	static int N;
	static int[] p=new int[100001];
	static int[][] star=new int[3][100001];
	static PriorityQueue<pair> pq= new PriorityQueue<pair>();
	static PriorityQueue<planet> xq= new PriorityQueue<planet>();
	static PriorityQueue<planet> yq= new PriorityQueue<planet>();
	static PriorityQueue<planet> zq= new PriorityQueue<planet>();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		Arrays.fill(p, -1);
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			star[0][i]=Integer.parseInt(st.nextToken());
			star[1][i]=Integer.parseInt(st.nextToken());
			star[2][i]=Integer.parseInt(st.nextToken());
		}
		sortPlanet();
		makeAdj();
		int n=0;
		long result=0;
		while(!pq.isEmpty()) {
			pair curr=pq.poll();
			if(merge(curr.first,curr.second)) {
				result+=curr.dist;
				n++;
			}
			if(n==N-1)break;
		}
		System.out.println(result);
		
	}
	
	static void sortPlanet() {
		for(int i=0; i<N; i++) {
			xq.offer(planet.of(i, star[0][i]));
			yq.offer(planet.of(i, star[1][i]));
			zq.offer(planet.of(i, star[2][i]));
		}
	}
	
	static void makeAdj() {
		planet tmp=xq.poll();
		while(!xq.isEmpty()) {
			planet curr=xq.poll();
			pq.offer(pair.of(tmp.number, curr.number, Math.abs(tmp.pos-curr.pos)));
			tmp=curr;
		}
		tmp=yq.poll();
		while(!yq.isEmpty()) {
			planet curr=yq.poll();
			pq.offer(pair.of(tmp.number, curr.number, Math.abs(tmp.pos-curr.pos)));
			tmp=curr;
		}
		tmp=zq.poll();
		while(!zq.isEmpty()) {
			planet curr=zq.poll();
			pq.offer(pair.of(tmp.number, curr.number, Math.abs(tmp.pos-curr.pos)));
			tmp=curr;
		}
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

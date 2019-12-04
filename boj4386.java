import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class pair implements Comparable{
	int first;
	int second;
	double dist;
	
	public pair(int f,int s, double d) {
		first=f;
		second=s;
		dist=d;
	}
	static pair of(int f, int s, double d) {
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
	static int N;
	static int[] p=new int[100001];
	static double[][] star=new double[2][100];
	static PriorityQueue<pair> pq= new PriorityQueue<pair>();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		Arrays.fill(p, -1);
		for(int i=0; i<N; i++) {
			double a,b;
			st=new StringTokenizer(br.readLine());
			star[0][i]=Double.valueOf(st.nextToken());
			star[1][i]=Double.valueOf(st.nextToken());
		}
		makeAdj();
		double result=0;
		int n=0;
		while(!pq.isEmpty()) {
			pair curr=pq.poll();
			if(merge(curr.first,curr.second)) {
				result+=curr.dist;
				n++;
			}
			if(n==N-1)break;
		}
		System.out.printf("%.2f\n",result);
		
	}
	
	static void makeAdj() {
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				double s1=star[0][i];
				double s2=star[1][i];
				double e1=star[0][j];
				double e2=star[1][j];
				pq.offer(pair.of(i, j, Math.sqrt(Math.pow(s1-e1, 2)+Math.pow(s2-e2, 2))));
			}
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

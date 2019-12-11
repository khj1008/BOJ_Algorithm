import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

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
	final static int INF=1000000000;
	static int[][] dist=new int[401][401];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<E; i++) {
			st=new StringTokenizer(br.readLine());
			int a,b,c;
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken());
			dist[a][b]=c;
		}
		
		for(int k=1; k<=V; k++) {
			for(int i=1; i<=V; i++) {
				for(int j=1; j<=V; j++) {
					dist[i][j]=Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
			}
		}
		
		int result=INF;
		for(int i=1;i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i!=j)result=Math.min(result, dist[i][j]+dist[j][i]);
			}
		}
		if(result==INF)System.out.println(-1);
		else System.out.println(result);
		
		
		
	
	}
	
	static void init() {
		for(int i=0; i<=V; i++) {
			for(int j=0; j<=V; j++) {
				if(i==j)dist[i][j]=0;
				else dist[i][j]=INF;
			}
		}
	}
	
	

	
	
}

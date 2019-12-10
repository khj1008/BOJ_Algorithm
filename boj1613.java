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
	static int N,K,S;
	final static int INF=1000000000;
	static int[][] dist=new int[401][401];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<K; i++) {
			st=new StringTokenizer(br.readLine());
			int a,b;
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			dist[a][b]=1;
		}
		
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					dist[i][j]=Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
			}
		}
		st=new StringTokenizer(br.readLine());
		S=Integer.parseInt(st.nextToken());
		for(int i=0; i<S; i++) {
			st=new StringTokenizer(br.readLine());
			int a,b;
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			if(dist[a][b]==INF && dist[b][a]==INF)System.out.println(0);
			else if(dist[a][b]!=INF)System.out.println(-1);
			else if(dist[a][b]==INF && dist[b][a]!=INF)System.out.println(1);
		}

	
	}
	
	static void init() {
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				if(i==j)dist[i][j]=0;
				else dist[i][j]=INF;
			}
		}
	}

	
	
}

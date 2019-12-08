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
	final static int INF=1000000;
	static int[][] dist=new int[101][101];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int a,b;
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			dist[a][b]=1;
			dist[b][a]=1;
		}
		
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					dist[i][j]=Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
			}
		}
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(dist[i][j]+" ");
			}
			System.out.println();
		}
		int num=INF;
		int result=0;
		for(int i=1; i<=N; i++) {
			int sum=0;
			for(int j=1; j<=N; j++) {
				if(dist[i][j]<INF)sum+=dist[i][j];
			}
			if(num>sum) {
				num=sum;
				result=i;
			}
			else if(num==sum)result=Math.min(result, i);
		}
		System.out.println(result);

	
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

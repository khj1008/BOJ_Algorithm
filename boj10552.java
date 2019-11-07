import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, M, P;
	static int[] arr=new int[100001];
	static boolean[] visited=new boolean[100001];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		P=Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			int a,b;
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			if(arr[b]==0)arr[b]=a;
		}
		int result=dfs(P);
		result=result<0?-1:result-1;
		System.out.println(result);
	}
	
	static int dfs(int n) {
		int ret=1;
		int next=arr[n];
		if(next==0)return ret;
		visited[n]=true;
		if(visited[next])return -1;
		if(!visited[next]) {
			int tmp=dfs(next);
			if(tmp==-1)return -1;
			else ret+=tmp;
		}
		return ret;
	}

}

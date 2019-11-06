import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N;
	static int[] v=new int[101];
	static boolean[] visited = new boolean[101];
	static boolean[] finished = new boolean[101];
	static ArrayList<Integer> result=new ArrayList<Integer>();
	static int cnt;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());		
		N=Integer.parseInt(st.nextToken());
		cnt=0;
		
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine());
			v[i]=Integer.parseInt(st.nextToken());
		}
		dfsAll();
		result.sort(null);
		System.out.println(cnt);
		for(int i=0; i<result.size(); i++) {
			System.out.println(result.get(i));
		}
		
	}
	static void dfsAll() {
		for(int i=1; i<=N; i++) {
			if(!visited[i])dfs(i);
		}
	}
	
	static void dfs(int n) {
		visited[n]=true;
		int next=v[n];
		if(visited[next]) {
			if(!finished[next]) {
				for(int temp=next; temp!=n; temp=v[temp]) {
					result.add(temp);
					cnt++;
				}
				result.add(n);
				cnt++;
			}
		}
		else dfs(next);
		finished[n]=true;
	}
	
	
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class pair implements Comparable{
	int first;
	int second;
	public pair(int f, int s) {
		first=f;
		second=s;
	}
	
	static pair of(int L,int R) {
		return new pair(L,R);
	}
	@Override
	public int compareTo(Object obj) {
		pair tmp=(pair)obj;
		if(first>tmp.first)return 1;
		else if(first==tmp.first) {
			if(second>tmp.second)return 1;
			else if(second==tmp.second)return 0;
			else return -1;
		}
		else return -1;
	}	
	
	
}

public class Main {
	static int N, M;
	static int[] indegree=new int[32001];
	static ArrayList<ArrayList<Integer>> adj=new ArrayList<ArrayList<Integer>>();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		for(int i=0; i<=N; i++) {
			adj.add(new ArrayList<Integer>());
		}
		M=Integer.parseInt(st.nextToken());
		for(int i=0; i<M; i++) {
			int a,b;
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			adj.get(a).add(b);
			indegree[b]++;
			
		}
		solve();

		
	}
	
	static void solve() {
		ArrayList<Integer> result=new ArrayList<Integer>();
		Queue<Integer> q=new LinkedList<Integer>();
		for(int i=1; i<=N; i++) {
			if(indegree[i]==0) {
				q.offer(i);
				result.add(i);
			}
		}
		
		for(int i=1; i<=N; i++) {
			int curr=q.poll();
			for(int next:adj.get(curr)) {
				indegree[next]--;
				if(indegree[next]==0) {
					q.offer(next);
					result.add(next);
				}
			}
		}
		for(int i=0; i<result.size(); i++) {
			System.out.print(result.get(i)+" ");
		}
	}


}

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
	static int N,M;
	static int[] map=new int[1001];
	static int[] m=new int[1001];
	static int pos;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		pos=1;
		for(int i=1;i<=N;i++) {
			st=new StringTokenizer(br.readLine());
			map[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			m[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=M; i++) {
			pos+=m[i-1];
			if(pos>=N) {
				result=i;
				break;
			}
			if(map[pos]!=0)pos+=map[pos];
			if(pos>=N) {
				result=i;
				break;
			}
		}
		System.out.println(result);
	}
	

}

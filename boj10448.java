import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
	static List<Integer> list=new ArrayList<Integer>();
	public static void main(String[] args) throws IOException {
		int T,K;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		T=Integer.parseInt(st.nextToken());
		int tmp=1;
		while(true) {
			int n=tmp*(tmp+1)/2;
			list.add(n);
			tmp++;
			if(n>1000)break;
		}
		
		for(int i=0; i<T; i++) {
			st=new StringTokenizer(br.readLine());
			K=Integer.parseInt(st.nextToken());
			if(check(K))System.out.println(1);
			else System.out.println(0);
		}
	}
	
	static boolean check(int n) {
		boolean result=false;
		for(int i=0; i<list.size(); i++) {
			for(int j=i; j<list.size(); j++) {
				for(int k=j; k<list.size(); k++) {
					if(list.get(i)+list.get(j)+list.get(k)==n)return true;
				}
			}
		}
		return result;
	}
	
}

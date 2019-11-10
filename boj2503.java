import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
	static int N;
	static Set<Integer> result=new HashSet<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			int quess,strike,ball;
			st=new StringTokenizer(br.readLine());
			quess=Integer.parseInt(st.nextToken());
			strike=Integer.parseInt(st.nextToken());
			ball=Integer.parseInt(st.nextToken());
			if(i==0)first(quess,strike, ball);
			else solve(quess,strike, ball);
		}
		System.out.println(result.size());
		
	}
	
	static void first(int quess, int strike, int ball) {
		for(int i=1; i<10; i++) {
			for(int j=1; j<10; j++) {
				if(i==j)continue;
				for(int k=1; k<10; k++) {
					if(i==k || j == k)continue;
					int tmp=100*i+10*j+k;
					if(strike==isStrike(quess,tmp) && ball == isBall(quess,tmp))result.add(tmp);
				}
			}
		}
	}
	
	static void solve(int quess, int strike, int ball) {
		Set<Integer> tmp=new HashSet<Integer>();
		tmp.addAll(result);
		Iterator it=tmp.iterator();
		result.clear();
		while(it.hasNext()) {
			int n=(int)it.next();
			if(strike==isStrike(quess,n) && ball == isBall(quess,n))result.add(n);
		}
	}
	
	static int isStrike(int a, int b) {
		String s1=Integer.toString(a);
		String s2=Integer.toString(b);
		int ret=0;
		for(int i=0; i<3; i++) {
			if(s1.charAt(i)==s2.charAt(i))ret++;
		}
		return ret;
	}
	
	static int isBall(int a, int b) {
		String s1=Integer.toString(a);
		String s2=Integer.toString(b);
		int ret=0;
		if(s1.charAt(0)!=s2.charAt(0) && s1.charAt(0)==s2.charAt(1))ret++;
		if(s1.charAt(0)!=s2.charAt(0) && s1.charAt(0)==s2.charAt(2))ret++;
		if(s1.charAt(1)!=s2.charAt(1) && s1.charAt(1)==s2.charAt(0))ret++;
		if(s1.charAt(1)!=s2.charAt(1) && s1.charAt(1)==s2.charAt(2))ret++;
		if(s1.charAt(2)!=s2.charAt(2) && s1.charAt(2)==s2.charAt(0))ret++;
		if(s1.charAt(2)!=s2.charAt(2) && s1.charAt(2)==s2.charAt(1))ret++;
		return ret;
	}
	
	
}

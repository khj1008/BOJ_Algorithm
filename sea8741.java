import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		for(int t=1; t<=T; t++) {
			String s=br.readLine();
			System.out.print("#"+t+" ");
			solve(s);
		}
	}
	
	static void solve(String s) {
		StringTokenizer st=new StringTokenizer(s);
		for(int i=0; i<3; i++) {
			System.out.print(st.nextToken().toUpperCase().charAt(0));
		}
		System.out.println();
	}
	
	
}


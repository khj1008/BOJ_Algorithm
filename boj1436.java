import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
	
	public static void main(String[] args) throws IOException {
		int N;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		int n=666;
		int i=0;
		while(true) {
			String tmp=Integer.toString(n);
			for(int j=tmp.length()-1; j>1; j--) {
				if(tmp.charAt(j)=='6' && tmp.charAt(j-1)=='6' && tmp.charAt(j-2)=='6') {
					i++;
					System.out.println(tmp);
					break;
				}
			}
			if(i==N)break;
			n++;
		}
		System.out.println(n);
	}
	
	
	
}

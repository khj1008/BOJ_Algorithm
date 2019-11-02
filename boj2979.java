import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int A,B,C;
	static int[] sum=new int[101];
	public static void main(String[] args) throws IOException{
		int MAX=0;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		A=Integer.parseInt(st.nextToken());
		B=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		for(int i=0; i<3; i++) {
			st=new StringTokenizer(br.readLine());
			int start,end;
			start=Integer.parseInt(st.nextToken());
			end=Integer.parseInt(st.nextToken());
			MAX=Math.max(MAX,end);
			for(int j=start; j<end; j++) {
				sum[j]++;
			}
		}
		int result = 0;
		for(int i=1; i<MAX; i++) {
			switch(sum[i]) {
			case 1:
				result+=A;
				break;
			case 2:
				result+=B * 2;
				break;
			case 3:
				result+=C * 3;
				break;
			}
		}
		System.out.println(result);
	}
	
}


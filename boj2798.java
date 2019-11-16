import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		int N,M;
		int[] arr=new int[100];
		int result=0;
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				for(int k=j+1; k<N; k++) {
					if(arr[i]+arr[j]+arr[k]<=M)result=Math.max(result, arr[i]+arr[j]+arr[k]);
				}
			}
		}
			
		System.out.println(result);
	}
	
	
	
	
	
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N,M,L;
	static int[] arr=new int[1001];
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		int result=0;
		arr[1]++;
		int curr=1;
		while(true) {
			if(arr[curr]==M)break;
			if(arr[curr]%2 == 0) {
				if((N+curr-L)%N==0) {
					curr=N;
				}
				else {
					curr=(N+curr-L)%N;
				}
			}
			else {
				if((curr+L)%N==0) {
					curr=N;
				}else {
					curr=(curr+L)%N;
				}
			}
			arr[curr]++;
			result++;
		}
		System.out.println(result);
	}
	
}


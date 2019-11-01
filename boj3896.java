import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static boolean[] isPrime = new boolean[12399710];
	static int k;
	static final int MAX=12399709; 
	public static void main(String[] args) {
		int T;
		Scanner sc=new Scanner(System.in);
		Arrays.fill(isPrime,true);
		T=sc.nextInt();
		selectPrime();
	
		for(int t=0; t<T; t++) {
			k=sc.nextInt();
			if(isPrime[k])System.out.println(0);
			else System.out.println(solve());
		}
	}
	
	static int solve() {
		int result=0;
		for(int i=k+1; i<MAX; i++) {
			if(isPrime[i])break;
			result++;
		}
		for(int i=k-1; i>=2; i--) {
			if(isPrime[i])break;
			result++;
		}
		return result+2;
	}
	
	static void selectPrime() {
		for(int i=2; i<=MAX; i++) {
			if(isPrime[i])isPrime[i]=true;
			for(int j=i*2; j<=MAX; j+=i) {
				isPrime[j]=false;
			}
		}
	}

}


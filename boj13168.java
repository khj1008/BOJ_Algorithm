import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N,R,M,K;
	final static int INF=1000000000;
	static HashMap<String, Integer> city=new HashMap<String,Integer>();
	static ArrayList<Integer> visit=new ArrayList<Integer>();
	static int[][] discount=new int[100][100];
	static int[][] normal=new int[100][100];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		init();
		for(int i=0; i<N; i++) {
			city.put(st.nextToken(),i);
		}
		st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<M; i++) {
			visit.add(city.get(st.nextToken()));
		}
		st=new StringTokenizer(br.readLine());
		K=Integer.parseInt(st.nextToken());
		for(int i=0; i<K; i++) {
			st=new StringTokenizer(br.readLine());
			String by;
			int s,e,n;
			by=st.nextToken();
			s=city.get(st.nextToken());
			e=city.get(st.nextToken());
			n=Integer.parseInt(st.nextToken());
			normal[s][e]=Math.min(normal[s][e], n);
			normal[e][s]=Math.min(normal[e][s], n);
			if(by.equals("ITX-Saemaeul") || by.equals("ITX-Cheongchun") || by.equals("Mugunghwa")) {
				discount[s][e]=Math.min(discount[s][e],0);
				discount[e][s]=Math.min(discount[e][s],0);
			}
			else if(by.equals("S-Train") || by.equals("V-Train")) {
				discount[s][e]=Math.min(discount[s][e],n/2);
				discount[e][s]=Math.min(discount[e][s],n/2);
			}
			else {
				discount[s][e]=Math.min(discount[s][e], n);
				discount[e][s]=Math.min(discount[e][s], n);
			}
		}
		solve();
	}
	
	static void solve() {
		int no=0;
		int yes=R;
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					normal[i][j]=Math.min(normal[i][j], normal[i][k]+normal[k][j]);
					discount[i][j]=Math.min(discount[i][j],discount[i][k]+discount[k][j]);
					
				}
			}
		}
		for(int i=0; i<visit.size()-1; i++) {
			int curr,next;
			curr=visit.get(i);
			next=visit.get(i+1);
			no+=normal[curr][next];
			yes+=discount[curr][next];
		}
		
		if(no==yes)System.out.println("No");
		else if(no>yes)System.out.println("Yes");
		else System.out.println("No");
		
	}
	
	
	static void init() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j){
					normal[i][j]=0;
					discount[i][j]=0;
				}
				else { 
					normal[i][j]=INF;
					discount[i][j]=INF;
				}
			}
		}
	}
	
	

	
	
}

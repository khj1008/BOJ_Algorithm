import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class str implements Comparable<str>{
	String s;
	
	public str(String _s) {
		super();
		this.s=_s;
	}
	@Override
	public int compareTo(str tmp) {
		if(s.length()<tmp.s.length())return -1;
		else if(s.length()==tmp.s.length()) return s.compareTo(tmp.s);
		else return 1;
	}
}

public class Main {
	static int N;
	static PriorityQueue<str> pq=new PriorityQueue<str>();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			str tmp=new str(st.nextToken());
			pq.offer(tmp);
		}
		LinkedHashSet<String> result=new LinkedHashSet<String>();
		while(!pq.isEmpty()) {
			result.add(pq.poll().s);
			
		}
		Iterator it=result.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
	}
	
	
}

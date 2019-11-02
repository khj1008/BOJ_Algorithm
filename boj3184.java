import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class pair<L,R>{
	final L left;
	final R right;
	public pair(L left, R right) {
		this.left=left;
		this.right=right;
	}
	static <L,R> pair<L,R> of(L left, R right){
		return new pair<L,R>(left, right);
	}
}

public class Main {
	static char[][] map=new char[251][251];
	static boolean[][] visited=new boolean[251][251];
	static int R,C;
	static int[] dx=new int[] {1,-1,0,0};
	static int[] dy=new int[] {0,0,1,-1};
	static int resultO;
	static int resultV;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		for(int i=0; i<R; i++) {
			String s=br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j]=s.charAt(j);
			}
		}
		resultO=0;
		resultV=0;
		solve();
		br.close();
	}
	
	static void bfs(pair<Integer,Integer> p) {
		int o=0,v=0;
		Queue q= new LinkedList();
		q.offer(p);
		visited[p.left][p.right]=true;
		if(map[p.left][p.right]=='o')o++;
		if(map[p.left][p.right]=='v')v++;
		while(!q.isEmpty()) {
			int qsize=q.size();
			for(int i=0; i<qsize; i++) {
				pair<Integer,Integer> tmp=(pair<Integer, Integer>) q.poll();
				for(int j=0; j<4; j++) {
					int nx=tmp.left+dx[j];
					int ny=tmp.right+dy[j];
					if(nx>=0 && nx<R && ny>=0 && ny<C) {
						if(!visited[nx][ny] && map[nx][ny]!='#') {
							visited[nx][ny]=true;
							q.offer(pair.of(nx,ny));
							if(map[nx][ny]=='o')o++;
							if(map[nx][ny]=='v')v++;
						}
					}
				}
			}
		}
		if(o>v)resultO+=o;
		else resultV+=v;
	}
	
	static void solve() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(!visited[i][j] && map[i][j]!='#') {
					bfs(pair.of(i, j));
				}
			}
		}
		System.out.print(resultO+" "+resultV+"\n");
	}
	
	
}


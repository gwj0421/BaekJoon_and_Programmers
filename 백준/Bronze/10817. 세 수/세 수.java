import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int q=Integer.parseInt(st.nextToken());
		int w=Integer.parseInt(st.nextToken());
		int e=Integer.parseInt(st.nextToken());
		int middle=q<e?(w>e?e:w>q?w:q):(w>q?q:w>e?w:e);
		System.out.println(middle);
			
			//e q
	}
}

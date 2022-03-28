import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		String[]a=br.readLine().split(" ");
		int b=Integer.parseInt(a[1]);
		StringTokenizer st=new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			String w=st.nextToken();
			int q=Integer.parseInt(w);
			if(q<b)wr.write(w+" ");
		}
		wr.flush();
		wr.close();
		br.close();
			
	}
}
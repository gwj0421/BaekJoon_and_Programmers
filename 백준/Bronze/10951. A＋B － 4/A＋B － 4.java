import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	String q;
		while (( q=br.readLine())!=null) {
			String[] a = q.split(" ");
			int e = Integer.parseInt(a[0]);
			int w = Integer.parseInt(a[1]);
				wr.write((e + w) + "\n");
			
		}
		
		wr.flush();
        wr.close();
		br.close();
		
		
	}

}
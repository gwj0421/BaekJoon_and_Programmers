import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		int q = Integer.parseInt(br.readLine());
		int sum, num;
		for (int i = 0; i < q; i++) {
			sum=0;num=0;
			String w=br.readLine();
			for(int e=0;e<w.length();e++) {
				if(w.charAt(e)=='O')sum+=++num;
				else if(w.charAt(e)=='X')num=0;
			}
			wr.write(sum + "\n");
		}
		wr.flush();
		wr.close();
		br.close();
	}

}

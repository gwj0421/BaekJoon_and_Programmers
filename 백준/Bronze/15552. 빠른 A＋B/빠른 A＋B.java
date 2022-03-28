import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		int i = Integer.parseInt(br.readLine());
		for (int a = 1; a <= i; a++) {
			String []q1=br.readLine().split(" ");
			int q=Integer.parseInt(q1[0]);
			int w=Integer.parseInt(q1[1]);
			wr.write(q+w + "\n");
		}

		wr.flush();
		wr.close();
		br.close();

	}
}
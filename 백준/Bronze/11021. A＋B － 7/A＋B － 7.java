import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		int a = Integer.parseInt(br.readLine());
		for (int i = 1; i <= a; i++) {
			String[] w = br.readLine().split(" ");
			int q = Integer.parseInt(w[0]);
			int e = Integer.parseInt(w[1]);
			wr.write("Case #" + i + ": " + (q + e) + "\n");
		}
		wr.flush();
		wr.close();
		br.close();
	}
}
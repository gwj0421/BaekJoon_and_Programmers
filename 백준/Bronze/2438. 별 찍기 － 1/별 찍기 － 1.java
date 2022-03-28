import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		int a = Integer.parseInt(br.readLine());
		for (int i = 0; i < a; i++) {
			for (int q =0; q <= i; q++)
				wr.write("*");
			wr.newLine();
		}
		wr.flush();
		wr.close();
		br.close();
	}
}
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		int a = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		char[] b = new char[a];
		for (int i = 0; i < a; i++)
			b[i] = ' ';
		for (int i = 0; i < a; i++) {
			b[a - i - 1] = '*';
			sb.append(b).append("\n");
		}
		wr.write(sb.toString());
		br.close();
		wr.flush();
		wr.close();
	}
}
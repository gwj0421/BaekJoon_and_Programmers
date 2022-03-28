import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

		int[] q = new int[9];
		for (int i = 0; i < q.length; i++)
			q[i] = Integer.parseInt(br.readLine());
		int max = Integer.MIN_VALUE;
		int num=0;
		for (int i = 0; i < q.length; i++) {
			if (max < q[i]) {
				max = q[i];
				num = i;
			}
		}
		wr.write(max + "\n" + (num+1) + "\n");
		wr.flush();
		br.close();
		wr.close();
	}
}

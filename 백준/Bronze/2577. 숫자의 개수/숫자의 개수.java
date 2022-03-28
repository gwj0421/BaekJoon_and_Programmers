import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] q = new int[10];
		int sum = 1;
		int num;
		for (int i = 0; i < 3; i++)
			sum *= Integer.parseInt(br.readLine());
		for (int i = 0;; i++) {
			if(sum==0)break;
			num = sum % 10;
			q[num]++;
			sum/=10;
		}
		for(int i=0;i<q.length;i++)
			wr.write(q[i]+"\n");
		wr.flush();
		br.close();
		wr.close();
	}
}

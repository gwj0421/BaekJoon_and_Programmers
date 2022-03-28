import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine().toString());
		int[] answer = new int[num];
		int temp;
		StringTokenizer st;
		String line;
		for (int i = 0; i < num; i++) {
			temp = 0;
			line = br.readLine();
			st = new StringTokenizer(line);
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			while (num2 - num1 > 0) {
				temp++;
				num2 -= temp;
				num1 += temp;
			}
			if (num1 == num2|| num1 - num2 < temp)
				answer[i] = temp * 2;
			else
				answer[i] = temp * 2 - 1;
		}
		for (int i = 0; i < num; i++)
			System.out.println(answer[i]);
	}

}

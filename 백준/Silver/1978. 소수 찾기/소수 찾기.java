import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int temp = 0, count = 0;
		boolean flag;
		for (int i = 0; i < a; i++) {
			flag = true;
			temp = Integer.parseInt(st.nextToken().toString());
			if (temp == 2)
				count++;
			else if (temp != 1) {
				for (int j = 2; j < temp; j++) {
					if (temp % j == 0)
						flag = false;
				}
				if (flag)
					count++;
			}
		}
		System.out.println(count);
	}
}

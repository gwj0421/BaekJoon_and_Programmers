import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb0 = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		int q = Integer.parseInt(br.readLine());
		sb0.append("*");
		sb1.append(" ");
		for (int w = 2; w <= q; w++) {
			if (w % 2 == 0) // 짝
			{sb0.append(" ");
				sb1.append("*");
			} else// 홀
			{sb1.append(" ");
				sb0.append("*");
			}
		}

		for (int i = 0; i < q; i++) {
			System.out.println(sb0.toString());
			System.out.println(sb1.toString());
		}

		br.close();
	}
}

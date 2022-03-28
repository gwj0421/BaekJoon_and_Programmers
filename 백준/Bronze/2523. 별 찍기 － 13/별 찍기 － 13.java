import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int q = Integer.parseInt(br.readLine());
		for (int i = 0; i < q; i++) {
			for (int w = 0; w <= i; w++)
				sb.append("*");
			sb.append("\n");
		}
		for (int i = q - 1; i > 0; i--) {
			for (int w = 0; w < i; w++)
				sb.append("*");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
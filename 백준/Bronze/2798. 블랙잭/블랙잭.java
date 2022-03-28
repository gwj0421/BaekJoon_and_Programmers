import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int n = Integer.parseInt(st.nextToken().toString());
		int m = Integer.parseInt(st.nextToken().toString());
		int max = 0;
		ArrayList<Integer> number = new ArrayList<Integer>();
		line = br.readLine();
		st = new StringTokenizer(line);
		for (int i = 0; i < n; i++) {
			number.add(Integer.parseInt(st.nextToken().toString()));
		}

		for (int i = 0; i < n-2; i++) {
			for (int j = i+1; j < n-1; j++) {
				for (int k = i+2; k < n; k++) {
					if (i!=j&&j!=k&&i!=k)
						if (number.get(i) + number.get(j) + number.get(k) <= m) {
							if (max < number.get(i) + number.get(j) + number.get(k))
								max = number.get(i) + number.get(j) + number.get(k);
						}
				}
			}
		}
		System.out.println(max);
	}
}

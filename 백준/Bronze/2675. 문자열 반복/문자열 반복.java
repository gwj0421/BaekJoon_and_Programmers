import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(br.readLine());
		for (int i = 0; i < num; i++) {
			String a = br.readLine();
			String[] arr = a.split(" ");
			for (int j = 0; j < arr[1].length(); j++) {
				for (int w = 0; w < Integer.parseInt(arr[0]); w++) {
					bw.write(arr[1].charAt(j));
				}

			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int floar, room;
		int[] num = new int[n];
		int i;
		int[][]apart;
		for (i = 0; i < n; i++) {
			floar = Integer.parseInt(br.readLine())+1;
			room = Integer.parseInt(br.readLine());
			apart = new int[floar][room];
			for (int j = 0; j < room; j++) {
				apart[0][j] = j + 1;
			}
			for (int k = 1; k < floar; k++) {
				apart[k][0] = 1;
			}
			for (int f = 1; f < floar; f++) {
				for (int r = 1; r < room; r++) {
					for (int temp = 0; temp <= r; temp++)
						apart[f][r] += apart[f - 1][temp];
				}
			}
			num[i] = apart[floar-1][room - 1];
		}
		for(int a=0;a<i;a++)
			System.out.println(num[a]);
	}
}

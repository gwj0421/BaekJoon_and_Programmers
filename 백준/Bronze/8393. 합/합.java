import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		int i = Integer.parseInt(br.readLine());
		int sum = 0;
		for (int a = 1; a <= i; a++)
			sum += a;
		/*
		 * BufferedWriter.writer(int값);처럼 쓰면 int 값이 아닌 int 값에 해당하는 아스키코드에 따른 char형 값이
		 * 출력됨 BufferedWriter.writer(int값+"\n");나
		 * BufferedWriter.writer(String.valueOf(int값))으로 쓰면됨.
		 */
		wr.write(sum + "\n");
		// flush()는 마지막에 한번에 버퍼에 쓰여진 값을 출력하고 지우는 것임
		wr.flush();
		// BufferedReader나 BufferedWriter는 다쓰고 종료해줘야함
		wr.close();
		br.close();

	}
}
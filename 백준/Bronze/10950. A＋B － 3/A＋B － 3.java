import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// N을 입력받은 뒤, 구구단 N단을 출력하는 프로그램을 작성하시오. 출력 형식에 맞춰서 출력하면 된다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 출력을 위한 클래스
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		int x = Integer.parseInt(br.readLine());
		for (int i = 0; i < x; i++) {
			String[] z = br.readLine().split(" ");
			int y = Integer.parseInt(z[0]);
			int q = Integer.parseInt(z[1]);
			// 바로 출력하는것이 아닌 버퍼에 입력하는것
			wr.write((y + q) + "\n");

		}
		br.close();
		// 버퍼에 저장된 데이터들을 모두 출력하고 삭제
		wr.flush();
		wr.close();	
	}
}
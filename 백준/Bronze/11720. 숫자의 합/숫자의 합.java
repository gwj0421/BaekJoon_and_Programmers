import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		String num = br.readLine();
		String[] st = num.split("");
		int sum = 0;
		for (int i = 0; i < st.length; i++) {
			sum += Integer.parseInt(st[i]);
		}
		System.out.println(sum);

	}
}

/*
 * N개의 숫자가 공백 없이 쓰여있다. 이 숫자를 모두 합해서 출력하는 프로그램을 작성하시오.
 */
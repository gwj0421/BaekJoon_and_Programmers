import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Main {
	public static void main(String []args) throws IOException {
		//첫째 줄에 (A+B)%C, 둘째 줄에 ((A%C) + (B%C))%C, 셋째 줄에 (A×B)%C, 넷째 줄에 ((A%C) × (B%C))%C를 출력한다.
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String a=br.readLine();
		String []str=a.split(" ");
		int c=Integer.parseInt(str[0]);
		int d=Integer.parseInt(str[1]);
		int e=Integer.parseInt(str[2]);
		System.out.println((c+d)%e);
		System.out.println(((c%e)+(d%e))%e);
		System.out.println((c*d)%e);
		System.out.println(((c%e)*(d%e))%e);

		br.close();
	}
}

import java.io.*;

public class Main{
	public static void main(String []args) throws IOException {
		//N을 입력받은 뒤, 구구단 N단을 출력하는 프로그램을 작성하시오. 출력 형식에 맞춰서 출력하면 된다.
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int x=Integer.parseInt(br.readLine());
		for(int i=1;i<10;i++)
			System.out.println(x+" * "+i+ " = "+(x*i));

	}
}
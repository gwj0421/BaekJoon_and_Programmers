import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Main {
	public static void main(String []args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int c=Integer.parseInt(br.readLine());
		int d=Integer.parseInt(br.readLine());
		System.out.println(c*(d%10));
		System.out.println(c*(d%100/10));
		System.out.println(c*(d/100));
		System.out.println(c*(d%10)+10*c*(d%100/10)+c*(d/100)*100);
		br.close();
	}
}



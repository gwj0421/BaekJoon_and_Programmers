import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
	public static void main(String []args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		String a=br.readLine();
		String[]wr=a.split(" ");
		double q=Double.parseDouble(wr[0]);
		double w=Double.parseDouble(wr[1]);
		System.out.println(q/w);
		
	}
}
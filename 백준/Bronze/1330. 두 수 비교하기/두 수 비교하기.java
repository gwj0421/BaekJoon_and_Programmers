import java.io.*;

public class Main{
	public static void main(String []args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String []q=br.readLine().split(" ");
		int a=Integer.parseInt(q[0]);
		int b=Integer.parseInt(q[1]);
System.out.println(a==b?"==":a>b?">":"<");
		
	}
}
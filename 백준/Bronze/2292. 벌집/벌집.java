import java.io.*;
public class Main {
	public static void main(String []args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int temp=2,count=1;
		if(n==1)count=1;
		else
		while(n>=temp) {
			temp+=6*count++;
		}
		System.out.println(count);
	}
}

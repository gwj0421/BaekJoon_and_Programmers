import java.io.*;

public class Main {
	public static void main(String []args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int a=Integer.parseInt(br.readLine().toString());
		while(a>0) {
			if(a==2) {
				System.out.println(2);
				break;
			}
			for(int i=2;i<=a;i++) {
				if(a%i==0) {
					System.out.println(i);
					a/=i;
					break;
				}
			}
			if(a==1)
				break;
		}
	}
}

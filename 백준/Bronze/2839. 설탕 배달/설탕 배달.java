import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		int su5 = a / 5;
		int su3 = 0;
		boolean result=true;

		while(su5>=0) {
			if((a-su5*5)%3==0)
			{
				su3=(a-su5*5)/3;
				System.out.println(su5+su3);
				result=false;
				break;
			}
			
			su5--;
		}
if(result)
	System.out.println(-1);
	}
}

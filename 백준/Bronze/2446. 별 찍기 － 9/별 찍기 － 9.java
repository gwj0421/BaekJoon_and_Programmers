import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int q = Integer.parseInt(br.readLine());
		for(int i=0;i<q;i++) {
			for(int w=0;w<i;w++)
				System.out.print(" ");
			for(int e=0;e<2*q-1-2*i;e++)
				System.out.print("*");
			System.out.println("");
		}
		for(int i=1;i<q;i++)
		{
			for(int w=1;w<q-i;w++)
				System.out.print(" ");
			for(int e=0;e<2*q-1-2*(q-1)+2*i;e++)
				System.out.print("*");
			System.out.println("");
			
		}

		
	}
}

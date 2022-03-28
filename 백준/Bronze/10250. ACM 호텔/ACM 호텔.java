import java.io.*;
import java.util.StringTokenizer;
public class Main {
	public static void main(String []args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int []number=new int[n];
		StringTokenizer st;

		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int floar=Integer.parseInt(st.nextToken());
			int roomnum=Integer.parseInt(st.nextToken());
			int num=Integer.parseInt(st.nextToken());
			int temp1=num%floar;
			if(temp1==0)temp1=floar;
			int temp2=num/floar;
			if(num%floar==0)temp2--;
			number[i]=temp1*100+temp2+1;
		}
		for(int i=0;i<n;i++) {
			System.out.println(number[i]);
		}
	}
}


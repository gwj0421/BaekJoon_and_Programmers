import java.io.*;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String a = st.nextToken();
		String b = st.nextToken();

		int max = a.length();
		if (max < b.length())
			max = b.length();
		
		int[] number1 = new int[max + 1];
		int[] number2 = new int[max + 1];
		
		for(int i=a.length()-1, temp=0;i>-1;i--,temp++) {
			number1[temp]=a.charAt(i)-'0';
		}	
		for(int i=b.length()-1, temp=0;i>-1;i--,temp++) {
			number2[temp]=b.charAt(i)-'0';
		}
		
		for(int i=0;i<max;i++) {
			int sum=number1[i]+number2[i];
			number1[i]=sum%10;
			number1[i+1]+=sum/10;
		}
		
		StringBuilder sb=new StringBuilder();
		if(number1[max]!=0)
			sb.append(number1[max]);
		for(int i=max-1;i>=0;i--)
			sb.append(number1[i]);
		System.out.println(sb);
	}
}

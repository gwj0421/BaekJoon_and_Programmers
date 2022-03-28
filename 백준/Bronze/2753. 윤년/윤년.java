import java.io.*;

public class Main{
	public static void main(String []args) throws IOException {
		//윤년은 연도가 4의 배수이면서, 100의 배수가 아닐 때 또는 400의 배수일 때이다.연도가 주어졌을 때, 윤년이면 1, 아니면 0을 출력하
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int a=Integer.parseInt(br.readLine());
System.out.println((a%4==0&&a%100!=0)||a%400==0?"1":"0");
		
	}
}
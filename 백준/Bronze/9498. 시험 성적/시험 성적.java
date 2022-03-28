import java.io.*;

public class Main{
	public static void main(String []args) throws IOException {
		//시험 점수를 입력받아 90 ~ 100점은 A, 80 ~ 89점은 B, 70 ~ 79점은 C, 60 ~ 69점은 D, 나머지 점수는 F를 출력하는 프로그램을 작성하시오.
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int a=Integer.parseInt(br.readLine());
System.out.println(a>=90&&a<=100?"A":a>=80?"B":a>=70?"C":a>=60?"D":"F");
		
	}
}
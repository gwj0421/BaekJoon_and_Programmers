import java.io.*;



public class Main{
	public static void main(String []args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int sum=0,number=5;
		int point;
		for(int i=0;i<number;i++) {
		point=Integer.parseInt(br.readLine());
		if(point<40)point=40;
		sum+=point;
		}
		System.out.println(sum/number);
		br.close();
	}
}
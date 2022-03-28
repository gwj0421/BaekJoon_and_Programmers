import java.io.*;



public class Main{
	public static void main(String []args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int []money=new int [5];
		int max=0;
		for(int i=0;i<money.length;i++) {
			money[i]=Integer.parseInt(br.readLine());
			max+=money[i];
		}
		int summoney=0;
		for(int i=0;i<3;i++)
			for(int q=3;q<5;q++) {
				summoney=money[i]+money[q];
				if(max>summoney)max=summoney;
			}
		System.out.println(max-50);
		br.close();
	}
}
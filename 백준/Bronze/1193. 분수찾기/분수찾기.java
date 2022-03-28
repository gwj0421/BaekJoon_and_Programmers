import java.io.*;

public class Main {
	public static void main(String []args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int count=2,no=2,line=2;
		int answer1=0,answer2=0;
		int n=Integer.parseInt(br.readLine());
		if(n==1) {
			answer1=1;
			answer2=1;
		}
		else
			while(no<=n) {
			if(no==line) {
				if(count%2==0) {
					answer1=1;
					answer2=count;
					line+=count;
				}
				else if(count%2==1) {
					answer1=count;
					answer2=1;
					line+=count;
				}
			}
			else if(count%2==0) {
				answer1++;
				answer2--;
				if(answer1==count)count++;
			}
			else if(count%2==1) {
				answer1--;
				answer2++;
				if(answer2==count)count++;
			}
			no++;
		}
		System.out.println(answer1 +"/"+answer2);
	}
}

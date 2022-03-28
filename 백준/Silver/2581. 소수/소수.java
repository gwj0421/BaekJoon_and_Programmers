import java.io.*;
public class Main {
	public static void main(String []args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int sum=0,MIN=10001;
		boolean flag;
		int first=Integer.parseInt(br.readLine().toString());
		int last=Integer.parseInt(br.readLine().toString());
		while(first<=last) {
			flag=true;
			if(first==1)
				flag=false;
			else if(first>2) {
				for(int i=2;i<first;i++) {
					if(first%i==0)
						flag=false;
				}
			}
			if(flag) {
				if(MIN>first)MIN=first;
				sum+=first;
			}
			first++;
		}
		
		if(sum!=0) {
			System.out.println(sum);
			System.out.println(MIN);
		}
		else
			System.out.println(-1);
		
	}
}

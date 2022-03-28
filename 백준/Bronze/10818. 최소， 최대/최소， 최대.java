import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr= new BufferedWriter(new OutputStreamWriter(System.out));
		int q=Integer.parseInt(br.readLine());
		int num=0,min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;
		int sign=1;
		for(int i:br.readLine().getBytes()) {
			if(i=='-')
				sign=-1;
			else if(i>='0'&&i<='9')
				num=num*10+i-'0';
			else if(i==' ') {
				num *=sign;
				min=Math.min(min, num);
				max=Math.max(max, num);
				num=0;
				sign=1;
			}
		}
		num*=sign;
		min=Math.min(min, num);
		max=Math.max(max, num);
		wr.write(min +" "+max);
		wr.flush();
		wr.close();
		br.close();
		
	}
}
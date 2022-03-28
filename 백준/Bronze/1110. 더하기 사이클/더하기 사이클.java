import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out)); 
int num=Integer.parseInt(br.readLine());
int lastnum=num;
int cnt=0;
do {
	lastnum=lastnum%10*10+(lastnum/10+lastnum%10)%10;
	cnt++;
}while(num!=lastnum);	
wr.write(cnt+"\n");
		wr.flush();
br.close();
wr.close();
			
	}
}
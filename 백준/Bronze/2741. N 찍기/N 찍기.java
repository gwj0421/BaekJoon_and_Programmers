import java.io.*;


public class Main{
	public static void main(String []args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr=new BufferedWriter(new OutputStreamWriter(System.out));
		int a=Integer.parseInt(br.readLine());
	for(int i=1;i<a+1;i++)
		wr.write(i+"\n");
		
	br.close();
	wr.flush();
	wr.close();
	}
}
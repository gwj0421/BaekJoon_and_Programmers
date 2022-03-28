import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
while(true) {
	String[]a=br.readLine().split(" ");
	int A=(int)Integer.parseInt(a[0]);
	int B=(int)Integer.parseInt(a[1]);
	if(A==0&&B==0)break;
	wr.write((A+B)+"\n");
}
wr.flush();
br.close();
wr.close();
			
	}
}
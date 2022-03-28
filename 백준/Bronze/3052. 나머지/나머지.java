import java.io.*;
import java.util.ArrayList;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		ArrayList array=new ArrayList();
		array.add(Integer.parseInt(br.readLine())%42);
		for(int i=1;i<10;i++) {
			int q=Integer.parseInt(br.readLine())%42;
			if(!array.contains(q))
				array.add(q);
		}
		wr.write(array.size()+"\n");
			
		wr.flush();
		br.close();
		wr.close();
	}
}

import java.io.*;

public class Main {
	public static void main(String []args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int count[]=new int [26];
		int max=0;
		char result='?';
		String eng=br.readLine().toUpperCase();
		for(int i=0;i<eng.length();i++) {
			count[eng.charAt(i)-'A']++;
			if(max<count[eng.charAt(i)-'A']) {
				max=count[eng.charAt(i)-'A'];
				result=eng.charAt(i);
			}
			else if(max==count[eng.charAt(i)-'A'])
				result='?';
		}
		System.out.println(result);
		br.close();
		bw.close();
	}
}

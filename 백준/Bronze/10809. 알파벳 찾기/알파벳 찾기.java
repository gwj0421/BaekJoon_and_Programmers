import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		int []num=new int[26];
		for(int i=0;i<num.length;i++) {
			num[i]=-1;
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String eng=br.readLine();
		for(int i=0;i<eng.length();i++) {
			char a=(char)eng.charAt(i);
			int q=(int)a-'a';
			if(num[q]!=-1)continue;
			num[q]=i;
		}
		for(int i=0;i<num.length;i++) {
			System.out.print(num[i]+" ");
		}
	}
}

/*
 * N개의 숫자가 공백 없이 쓰여있다. 이 숫자를 모두 합해서 출력하는 프로그램을 작성하시오.
 */
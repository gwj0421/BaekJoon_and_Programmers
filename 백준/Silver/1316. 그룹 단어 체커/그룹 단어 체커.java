import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num,temp,count=0;
		String name;
		num=Integer.parseInt(br.readLine());
		boolean result;
		for(int i=0;i<num;i++) {
		name=br.readLine();
		int []line=new int[26];
		result=true;
		for(int j=0;j<name.length();j++) {
			temp=(int)name.charAt(j)-97;
			line[temp]++;
			if(line[temp]>1&&j>0) {
				if(name.charAt(j)!=name.charAt(j-1))
					result=false;
			}
		}
		if(result)
			count++;
		}
		System.out.println(count);

	}
}

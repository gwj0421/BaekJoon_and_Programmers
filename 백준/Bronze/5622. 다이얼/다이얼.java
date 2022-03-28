import java.io.*;
public class Main  {
	public static void main (String []args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int time=0;
		int temp=0;
		char [] line=br.readLine().toCharArray();
		for(int i=0;i<line.length;i++) {
			temp=(line[i]);
			if(temp>86)
				time+=10;
			else if(temp>83)
				time+=9;
			else if(temp>=80)
				time+=8;
			else if(temp>=77)
				time+=7;
			else if(temp>=74)
				time+=6;
			else if(temp>=71)
				time+=5;
			else if(temp>=68)
				time+=4;
			else if(temp>=65)
				time+=3;
		}
		System.out.println(time);
	    }
}

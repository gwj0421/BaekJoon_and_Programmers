import java.util.Scanner;
public class Main {
	public static void main(String []args){
		 Scanner sc=new Scanner(System.in);
		 int a,b=0;
		 int ac,bc=0;
		 a=sc.nextInt();
		 b=sc.nextInt();
		 ac=a/100+a%100/10*10+a%10*100;
		 bc=b/100+b%100/10*10+b%10*100;
 if(ac>bc)
		 System.out.println(ac);
	 else
		 System.out.println(bc);
	    }
}

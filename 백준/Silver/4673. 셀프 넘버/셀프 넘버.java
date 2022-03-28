public class Main {

	
	public static void main(String[] args) {
		int num[]=new int [10001];
		
		
		for(int i=1;i<=num.length;i++)
			{
			int result=cal(i);
			if(result<=10000)
				num[result]=1;
			}
		
		for(int i=1;i<num.length;i++)
			if(num[i]!=1)
				System.out.println(i);
		
	}
	
	public static int cal(int value) {
		int result=value;
		while(value>0) {
			result+=value%10;
			value/=10;
		}
		return result;
	}

}
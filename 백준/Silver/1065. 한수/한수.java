import java.util.*;

/*
 * 어떤 양의 정수 X의 각 자리가 등차수열을 이룬다면, 그 수를 한수라고 한다. 등차수열은 연속된 두 개의 수의 차이가 일정한 수열을 말한다. N이 주어졌을 때, 1보다 크거나 같고, N보다 작거나 같은 한수의 개수를 출력하는 프로그램을 작성하시오. 
*/
class cal {
	private int a;

	public cal(int a) {
		this.a = a;
	}

	public int in() {
		int num = 0;
		for (int i = 1; i <= a; i++) {
			if (i < 100)
				num++;
			else if (i >= 100 && i < 1000) {
				int re = i;
				int q = i % 10;
				i /= 10;
				int w = i % 10;
				i /= 10;
				int e = i % 10;
				if (q - w == w - e)
					num++;
				i = re;
			} else {
				int re = i;
				int q = i % 10;
				i /= 10;
				int w = i % 10;
				i /= 10;
				int e = i % 10;
				i /= 10;
				int f = i % 10;
				if (q - w == w - e && w - e == e - f)
					num++;
				i = re;
			}

		}
		return num;
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		cal cl = new cal(sc.nextInt());
		System.out.println(cl.in());

	}
}
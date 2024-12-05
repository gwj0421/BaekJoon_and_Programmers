import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //    1	0001
//    2	0010
//    3	0011
//    4	0100
//    5	0101
//    6	0110
//    7	0111
//    8	1000
//    9	1001
//    10	1010
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        long N = Long.parseLong(br.readLine());
        int cnt = 0;
        long n = N;
        while (n > 0) {
            n /= 2;
            cnt++;
        }
        long num = 1;
        for (int i = 0; i < cnt; i++) {
            num *= 2;
        }
        num--;
        if (N == num) {
            System.out.println(1);
            System.out.println(N);
        } else {
            System.out.println(2);
            System.out.println((num - N) + " " + N);
        }
    }
}



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

//        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(br.readLine());
        long x = Long.parseLong(br.readLine());

        pow(a % MOD, x);
    }

    public static void pow(long a, long x) {
        long answer = 1L;
        while (x > 0) {
            if ((x & 1) == 1) {
                answer = answer * a % MOD;
            }
            x >>= 1;
            a = a * a % MOD;
        }
        System.out.println(answer);
    }

}



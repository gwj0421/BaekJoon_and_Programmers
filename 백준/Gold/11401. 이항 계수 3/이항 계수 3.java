import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Fermat fermat = new Fermat();
        System.out.println(fermat.calculate(n, k));;
    }

    static class Fermat {
        private static final int MOD = 1_000_000_007;
        private static final int MAX_N = 4_000_000;
        private final long[] factorial;

        /**
         * a^(p-1) === 1 (mod p)
         * a^(p-2) === a^(-1) (mod p)
         *
         * nCk  = n! / (k! * (n-k)!)
         *      = n! * (k!)^(p-2) * ((n-k)!)^(p-2)
         *
         * */
        public Fermat() {
            long[] factorial = new long[MAX_N + 1];
            factorial[0] = 1;
            for (int i = 1; i <= MAX_N; i++) {
                factorial[i] = factorial[i - 1] * i % MOD;
            }
            this.factorial = factorial;
        }

        public long calculate(int n, int k) {
            return factorial[n] * pow(factorial[k], MOD - 2) % MOD * pow(factorial[n - k], MOD - 2) % MOD;
        }

        private long pow(long a, int b) {
            if (b == 1) {
                return a % MOD;
            }
            long temp = pow(a, b / 2);
            if (b % 2 == 0) {
                return temp *temp %MOD;
            }
            return temp * temp % MOD * a % MOD;
        }
    }
}



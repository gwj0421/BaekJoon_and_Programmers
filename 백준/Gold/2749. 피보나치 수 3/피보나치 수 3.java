import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        Fibonacci fibonacci = new Fibonacci();
        fibonacci.showFibonacci(n);
    }

    static class Fibonacci {
        private int pisanoCycle;

        public Fibonacci() {
            double k = Math.log10(1000000);
            this.pisanoCycle = 15 * (int) Math.pow(10, k - 1);
        }

        public int getPisanoCycle() {
            return pisanoCycle;
        }

        public void showFibonacci(long n) {
            long[] dp = new long[getPisanoCycle() + 1];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i < getPisanoCycle() + 1; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
            }
            System.out.println(dp[(int) (n % getPisanoCycle())]);
        }
    }
}

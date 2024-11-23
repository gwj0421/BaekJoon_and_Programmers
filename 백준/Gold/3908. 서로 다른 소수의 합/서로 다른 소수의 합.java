import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        Calculator calculator = new Calculator();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            calculator.showCombCnt(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

    }

    static class Calculator {
        private static final int MAX_N = 1120;
        private static final int MAX_K = 14;
        private int[][] dp;

        public Calculator() {
            boolean[] isPrime = eratosthenes(MAX_N);
            List<Integer> primeNumbers = extractPrimeNumber(isPrime);
            int[][] dp = new int[MAX_N + 1][MAX_K + 1];
            dp[0][0] = 1;

            for (Integer primeNumber : primeNumbers) {
                for (int number = MAX_N; number >= primeNumber; number--) {
                    for (int k = 1; k <= MAX_K; k++) {
                        dp[number][k] += dp[number - primeNumber][k - 1];
                    }
                }
            }

            this.dp = dp;
        }

        private boolean[] eratosthenes(int length) {
            boolean[] isPrime = new boolean[length + 1];
            Arrays.fill(isPrime, true);
            isPrime[0] = false;
            isPrime[1] = false;
            for (int i = 2; i <= length; i++) {
                if (isPrime[i]) {
                    for (int j = i * i; j <= length; j += i) {
                        isPrime[j] = false;
                    }
                }
            }

            return isPrime;
        }

        private List<Integer> extractPrimeNumber(boolean[] isPrimes) {
            List<Integer> primeNumbers = new ArrayList<>();
            for (int i = 0; i < isPrimes.length; i++) {
                if (isPrimes[i]) {
                    primeNumbers.add(i);
                }
            }
            return primeNumbers;
        }

        public void showCombCnt(int num, int combCnt) {
            System.out.println(dp[num][combCnt]);
        }
    }
}



import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    private static final int MAX_N = 10_000;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        BigInteger[] dp = new BigInteger[MAX_N + 1];
        dp[3] = BigInteger.valueOf(4);
        dp[4] = BigInteger.valueOf(7);
        for (int i = 5; i <= MAX_N; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2]);
        }
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            System.out.println(dp[sc.nextInt()]);
        }
    }
}



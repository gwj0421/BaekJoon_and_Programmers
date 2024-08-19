import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final int MOD = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int[] password = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        if (password[0] == 0 || (password.length > 1 && password[0] > 2 && password[1] == 0)) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[password.length];
        dp[0] = 1;
        if (password.length > 1) {
            if (password[0] * 10 + password[1] < 27 && password[0] * 10 + password[1] != 10 && password[0] * 10 + password[1] != 20) {
                dp[1] = 2;
            } else {
                dp[1] = 1;
            }
        }
        for (int i = 2; i < password.length; i++) {
            if (password[i] > 0) {
                dp[i] = dp[i - 1];
            }
            if (checkCombinedNumber(password[i - 1], password[i])) {
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }
        }
        System.out.println(dp[password.length - 1]);
    }

    public static boolean checkCombinedNumber(int num1, int num2) {
        return 0 < num1 && num1 < 3 && 10 * num1 + num2 < 27;
    }
}


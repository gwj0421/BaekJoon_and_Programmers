import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 0 1 2 3 4 5 6 7 8 9
        // 0 1 1 1 1 1 1 1 1 1
        // 1 1 2 2 2 2 2 2 2 1
        // 1 3 3 4 4 4 4 4 3 2
        int[][] dp = new int[101][10];
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i < n + 1; i++) {
            dp[i][0] = dp[i - 1][1];
            dp[i][9] = dp[i - 1][8];
            for (int j = 1; j < 9; j++) {
                dp[i][j] = (dp[i - 1][j - 1]+ dp[i - 1][j + 1]) % MOD;
            }
        }

        int sum = 0;
        for (int val : dp[n]) {
            sum = (sum +val) % MOD;
        }
        System.out.println(sum);
    }
}


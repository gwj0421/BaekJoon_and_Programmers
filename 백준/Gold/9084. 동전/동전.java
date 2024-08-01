import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] coin = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int target = Integer.parseInt(br.readLine());

            int[][] dp = new int[n][target + 1];
            for (int j = 0; j < target + 1; j++) {
                if (j % coin[0] == 0) {
                    dp[0][j]++;
                }
            }
            for (int j = 1; j < n; j++) {
                for (int k = 0; k < target + 1; k++) {
                    if (k >= coin[j]) {
                        dp[j][k] = dp[j - 1][k] + dp[j][k - coin[j]];
                    } else {
                        dp[j][k] = dp[j - 1][k];
                    }
                }
            }
            System.out.println(dp[n - 1][target]);
        }
    }
}

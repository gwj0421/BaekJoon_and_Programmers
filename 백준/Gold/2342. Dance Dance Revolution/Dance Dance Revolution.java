import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final int MAX = 400001;
    private static final int[][] MOVE_COST = new int[][]{{0, 2, 2, 2, 2},
            {0, 1, 3, 4, 3}, {0, 3, 1, 3, 4}, {0, 4, 3, 1, 3}, {0, 3, 4, 3, 1}};

    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] stepInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][][] dp = new int[stepInfo.length][5][5];
        for (int i = 0; i < stepInfo.length; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], MAX);
            }
        }
        dp[0][stepInfo[0]][0] = 2;
        dp[0][0][stepInfo[0]] = 2;

        for (int depth = 1; depth < stepInfo.length; depth++) {
            int step = stepInfo[depth];
            for (int left = 0; left < 5; left++) {
                for (int right = 0; right < 5; right++) {
                    if (step != right) {
                        dp[depth][step][right] = Math.min(dp[depth][step][right], dp[depth - 1][left][right] + MOVE_COST[left][step]);
                    }
                    if (step != left) {
                        dp[depth][left][step] = Math.min(dp[depth][left][step], dp[depth - 1][left][right] + MOVE_COST[right][step]);
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(ans, dp[stepInfo.length - 1][i][j]);
            }
        }
        System.out.println(ans);


    }
}

import java.util.Arrays;

class Solution {
    private final int INF = 1000;
    public int solution(int[][] info, int n, int m) {
        int size = info.length;
        int[][] dp = new int[size + 1][m];
        for (int i = 0; i <= size; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= size; i++) {
            int pickA = info[i - 1][0];
            int pickB = info[i - 1][1];
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + pickA);
                if (j + pickB < m) {
                    dp[i][j + pickB] = Math.min(dp[i][j + pickB], dp[i - 1][j]);
                }
            }
        }
        int ans = INF;
        for (int i = 0; i < m; i++) {
            ans = Math.min(ans, dp[size][i]);
        }
        return ans < n ? ans : -1;
    }
}
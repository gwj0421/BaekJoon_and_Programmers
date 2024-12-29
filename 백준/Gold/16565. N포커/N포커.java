import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final long MOD = 1_000_000_007;

    // 이기는 경우의 수 = 1로 이기는 수 + 2로 이기는 수 ... - 겹치는 경우의 수
    // 겹치는 경우의 수 = 1,2로 이기는 수 + 1,3로 이기는 수 ...
    // 겹칠수 있는 타입이 2 타입일 경우 = 1,2 + 2,3 ..
    // 겹칠수 있는 타입이 3 타입일 경우 = 1,2,3 + 2,3 ..

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        Poker poker = new Poker();
        poker.pickCard(n);

    }

    static class Poker {
        private static final int CARD_CNT = 52;
        private static final int CARD_TYPE_CNT = 4;
        private static final int MOD = 10_007;

        private final long[][] dp;

        public Poker() {
            long[][] dp = new long[CARD_CNT+1][CARD_CNT+1];
            dp[1][1] = 1;
            // 4장 중 2장 뽑는 경우의수 = 3장 중 1장 뽑는 경우의 수 + 3장 중 2장 뽑는 경우의 수
            for (int i = 0; i <= CARD_CNT; i++) {
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || i == j) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    }
                }
            }
            this.dp = dp;
        }

        public void pickCard(int n) {
            int fourCardTypeCnt = n / 4;
            long ans = 0;
            for (int i = 1; i <= fourCardTypeCnt; i++) {
                long cnt = dp[CARD_CNT - 4 * i][n - 4 * i] * dp[13][i];
                ans = i % 2 == 1 ? (ans + cnt) % MOD : (ans - cnt % MOD + MOD) % MOD;
            }
            System.out.println(ans);
        }
    }
}
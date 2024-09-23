import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // A B C D E ... X  Y  Z
        // 0 1 2 3 4 ... 23 24 25

        Map<Integer, List<Integer>> nextWords = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            nextWords.put(i, new ArrayList<>());
        }
        for (int i = 0; i < 26; i++) {
            for (int j = i + n; j < 26; j++) {
                if (i == j) {
                    nextWords.get(i).add(j);
                } else {
                    nextWords.get(i).add(j);
                    nextWords.get(j).add(i);
                }
            }
        }
        int[][] dp = new int[m][26];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < 26; j++) {
                for (int nextWord : nextWords.get(j)) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][nextWord]) % MOD;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans = (ans + dp[m - 1][i]) % MOD;
        }
        System.out.println(ans);
    }
}
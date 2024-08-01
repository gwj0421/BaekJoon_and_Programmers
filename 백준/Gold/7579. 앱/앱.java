import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] memories = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] costs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] dp = new int[n][10001];
        for (int i = costs[0]; i < 10001; i++) {
            dp[0][i] = memories[0];
        }
        int ans;
        if (memories[0] >= m) {
            ans = costs[0];
        } else {
            ans = Integer.MAX_VALUE;
        } 
        for (int i = 1; i < n; i++) {
            int memory = memories[i];
            int cost = costs[i];
            for (int j = 0; j < 10001; j++) {
                if (j >= cost) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost] + memory);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
                if (dp[i][j] >= m) {
                    ans = Math.min(j, ans);
                }
            }
        }
        System.out.println(ans);
    }
}

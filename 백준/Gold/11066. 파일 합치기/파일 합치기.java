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
            int k = Integer.parseInt(br.readLine());
            int[] fileSize = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] subtotal = new int[k+1];
            int[][] dp = new int[k][k];
            for (int j = 0; j < k; j++) {
                subtotal[j + 1] += subtotal[j] + fileSize[j];
            }
            // dp[i][j] = dp[i][m] + dp[m+1][j]
            for (int tmpSize = 2; tmpSize < k + 1; tmpSize++) {
                for (int startIdx = 0; startIdx < k - tmpSize + 1; startIdx++) {
                    int endIdx = startIdx + tmpSize - 1;
                    dp[startIdx][endIdx] = Integer.MAX_VALUE;
                    for (int midIdx = startIdx; midIdx < endIdx; midIdx++) {
                        dp[startIdx][endIdx] = Math.min(dp[startIdx][endIdx],
                                dp[startIdx][midIdx] + dp[midIdx + 1][endIdx] + subtotal[endIdx+1] - subtotal[startIdx]);
                    }
                }
            }
            System.out.println(dp[0][k-1]);
        }
    }
}

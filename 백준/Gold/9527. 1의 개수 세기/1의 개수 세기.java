import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long[] dp = setDp();
        System.out.println(calculate(b, dp) - calculate(a-1, dp));
    }

    static long[] setDp() {
        long[] dp = new long[55];
        dp[0] = 1;
        for (int i = 1; i < 55; i++) {
            dp[i] = (dp[i - 1] << 1) + (1L << i);
        }
        return dp;
    }

    static long calculate(long num,long[] dp) {
        long cnt = num & 1;
        int size = (int) (Math.log(num) / Math.log(2));
        for (int i = size; i > 0; i--) {
            if ((num & (1L << i)) != 0L) {
                cnt += dp[i - 1] + (num - (1L << i) + 1);
                num -= (1L << i);
            }
        }
        return cnt;
    }
}
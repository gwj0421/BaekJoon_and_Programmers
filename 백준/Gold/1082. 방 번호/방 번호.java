import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[]cost = new int[n];
        String[] dp = new String[51];
        Arrays.fill(dp,"*");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int minCostNum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int pi = Integer.parseInt(st.nextToken());
            cost[i] = pi;
            dp[pi] = Integer.toString(i);
            minCostNum = Math.min(minCostNum, pi);
        }
        int m = Integer.parseInt(br.readLine());
        for (int i = minCostNum; i < m + 1; i++) {
            if (compareTo(dp[i - 1],dp[i])) {
                dp[i] = dp[i - 1];
            }
            for (int j = 0; j < n; j++) {
                if (i - cost[j] > -1 && !dp[i - cost[j]].equals("*") && !dp[i - cost[j]].equals("0")) {
                    String next = dp[i - cost[j]] + dp[cost[j]];
                    if (compareTo(next,dp[i])) {
                        dp[i] = next;
                    }
                }
            }
        }
        System.out.println(dp[m]);
    }

    private static boolean compareTo(String a, String b) {
        if (a.length() > b.length()) {
            return true;
        } else if (a.length() == b.length()) {
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) > b.charAt(i)) {
                    return true;
                } else if (a.charAt(i) < b.charAt(i)) {
                    break;
                }
            }
            return false;
        } else {
            return false;
        }
    }

}
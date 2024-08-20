import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[] dp = new int[n + 1];
        for (int day = 1; day < n + 1; day++) {
            st = new StringTokenizer(br.readLine());
            int term = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            int endDay = day + term - 1;
            if (endDay < n + 1) {
                dp[endDay] = Math.max(dp[endDay], dp[day - 1] + cost);
            }
            dp[day] = Math.max(dp[day], dp[day - 1]);
        }
        System.out.println(dp[n]);
    }
}


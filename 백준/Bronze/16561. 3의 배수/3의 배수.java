import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 1 3 6 10 15 21

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[3001];
        dp[9] = 1;
        int mid = 2;
        for (int i = 12; i <= 3000; i += 3) {
            dp[i] = dp[i - 3] + mid++;
        }
        System.out.println(dp[n]);
    }
}



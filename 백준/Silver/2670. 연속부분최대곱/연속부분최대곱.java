import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int n = Integer.parseInt(br.readLine());
        double[] sequence = new double[n + 1];
        double[] dp = new double[n + 1];
        double maxVal = 0;
        for (int i = 1; i < n + 1; i++) {
            sequence[i] = Double.parseDouble(br.readLine());
            dp[i] = Math.max(sequence[i], dp[i - 1] * sequence[i]);
            maxVal = Math.max(maxVal, dp[i]);
        }
        System.out.println(String.format("%.3f", maxVal));

    }
}
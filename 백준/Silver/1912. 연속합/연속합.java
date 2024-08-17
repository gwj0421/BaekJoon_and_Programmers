import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static final int[][] MOVE = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[n];
        dp[0] = numbers[0];
        int maxValue = dp[0];
        for (int i = 1; i < n; i++) {
            int val = Math.max(dp[i - 1] + numbers[i], numbers[i]);
            maxValue = Math.max(maxValue, val);
            dp[i] = val;
        }
        System.out.println(maxValue);
    }

}


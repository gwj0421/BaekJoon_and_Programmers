import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final int[][] MOVE = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] wineglass = new int[n];
        for (int i = 0; i < n; i++) {
            wineglass[i] = Integer.parseInt(br.readLine());
        }

        if (n < 3) {
            int ans = 0;
            for (int i = 0; i < n; i++) {
                ans += wineglass[i];
            }
            System.out.println(ans);
            return;
        }
        
        int[] dp = new int[n];
        dp[0] = wineglass[0];
        dp[1] = wineglass[0] + wineglass[1];
        dp[2] = Math.max(dp[1], wineglass[2] + Math.max(wineglass[0], wineglass[1]));
        for (int i = 3; i < n; i++) {
            int val = Math.max(dp[i - 3] + wineglass[i - 1] + wineglass[i],
                    dp[i - 2] + wineglass[i]);
            dp[i] = Math.max(dp[i - 1], val);
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n - 1]);
    }

}


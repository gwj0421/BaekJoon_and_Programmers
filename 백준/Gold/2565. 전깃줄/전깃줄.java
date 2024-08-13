import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] wires = new int[n][];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            wires[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        Arrays.sort(wires, Comparator.comparingInt((int[] o) -> o[0]));
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (wires[i][1] > wires[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int maxWireCnt = 0;
        for (int i = 0; i < dp.length; i++) {
            maxWireCnt = Math.max(maxWireCnt, dp[i]);
        }
        System.out.println(n - maxWireCnt);
    }
}

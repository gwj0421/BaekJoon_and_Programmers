import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] tips = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
            long ans = 0;
            for (int j = n - 1; j > -1; j--) {
                if (tips[j] <= n - 1 - j) {
                    break;
                }
                ans += tips[j] - (n - 1 - j);
            }
            System.out.println(ans);

        }
    }

}

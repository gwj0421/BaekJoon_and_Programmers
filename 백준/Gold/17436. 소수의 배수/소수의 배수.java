import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final boolean DEBUG_MODE = false;

    // 1-2 , 1-4 , 4-3
    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long[] numbers = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        Algorithm algorithm = new Algorithm(n, m, numbers);
        algorithm.solve();
    }


}

class Algorithm {
    private final int n;
    private final long m;
    private final long[] numbers;

    public Algorithm(int n, long m, long[] numbers) {
        this.n = n;
        this.m = m;
        this.numbers = numbers;
    }

    public void solve() {
        long ans = 0;
        for (int depth = 1; depth <= n; depth++) {
            long temp = dfs(-1, depth, 1);
            ans += depth % 2 == 0 ? -temp : temp;
        }
        System.out.println(ans);
    }

    private long dfs(int prevIdx, int remainDepth, long val) {
        if (remainDepth == 0) {
            return m / val;
        }
        long result = 0;
        for (int i = prevIdx + 1; i < n; i++) {
            result += dfs(i, remainDepth - 1, val * numbers[i]);
        }
        return result;
    }
}
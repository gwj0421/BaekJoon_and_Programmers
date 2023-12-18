import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        boolean[] start = makeBoard(br.readLine());
        boolean[] end = makeBoard(br.readLine());

        boolean[] startWithClick = Arrays.copyOf(start, N);
        startWithClick[0] = !startWithClick[0];
        startWithClick[1] = !startWithClick[1];

        int ans = Math.min(solve(start, end, 0), solve(startWithClick, end, 1));
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);

    }

    private static boolean[] makeBoard(String input) {
        boolean[] result = new boolean[input.length()];
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '0') {
                result[i] = false;
            } else {
                result[i] = true;
            }
        }
        return result;
    }

    private static int solve(boolean[] start, boolean[] end, int cnt) {
        for (int i = 0; i < N - 1; i++) {
            if (start[i] != end[i]) {
                cnt++;
                start[i] = !start[i];
                start[i + 1] = !start[i + 1];
                if (i != N - 2) {
                    start[i + 2] = !start[i + 2];
                }
            }
        }

        if (isSame(start, end)) {
            return cnt;
        } else {
            return Integer.MAX_VALUE;
        }

    }

    private static boolean isSame(boolean[] a, boolean[] b) {
        for (int i = 0; i < N; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
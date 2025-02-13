import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] ans = new int[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(ans[i], 1);
        }
        // 3 3 4 5
        // 3 1 1 1
        // 2 1 1 1
        // 2 1 1 1

        // (1,0) 1 => y>=1&&x>=1 1
        // (2,0) 1 => y>=2&&x>=1 1
        // (0,1) 1 => y>=1&&x>=1 1
        // (0,2) 1 => y>=1&&x>=2 1

        // (2,0) 2 => y>=2&&x>=1 2
        // (a,b) c => y>=a&&x>=b

        // 왼쪽 열 2 => 해당 열 이하는 다 +2
        // 0,0 =>
        int[] growRates;
        int gy, gx;
        for (int i = 0; i < n; i++) {
            growRates = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            gy = m - 1;
            gx = 0;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < growRates[j]; k++) {
                    ans[gy][gx] += j;
                    if (gy == 0) {
                        gx++;
                    } else {
                        gy--;
                    }
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < m; j++) {
                ans[i][j] = Math.max(ans[i][j - 1], Math.max(ans[i - 1][j - 1], ans[i - 1][j]));
            }
        }

        for (int[] ints : ans) {
            System.out.println(Arrays.stream(ints).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }

    }

}
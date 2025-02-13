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

        int[] growRates;
        int[][] grow;
        int gy, gx;
        for (int i = 0; i < n; i++) {
            growRates = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            grow = new int[m][m];
            gy = m - 1;
            gx = 0;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < growRates[j]; k++) {
                    grow[gy][gx] = j;
                    ans[gy][gx] += j;
                    if (gy == 0) {
                        gx++;
                    } else {
                        gy--;
                    }
                }
            }
            for (int j = 1; j < m; j++) {
                for (int k = 1; k < m; k++) {
                    grow[j][k] = Math.max(grow[j][k - 1], Math.max(grow[j - 1][k - 1], grow[j - 1][k]));
                    ans[j][k] += grow[j][k];
                }
            }
        }

        for (int[] ints : ans) {
            System.out.println(Arrays.stream(ints).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }

    }

}
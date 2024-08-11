import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final long MOD = 1000000007;
    private static final long[][] ROUTE = new long[][]{
            {0,1,1,0,0,0,0,0},
            {1,0,1,1,0,0,0,0},
            {1,1,0,1,1,0,0,0},
            {0,1,1,0,1,1,0,0},
            {0,0,1,1,0,1,1,0},
            {0,0,0,1,1,0,0,1},
            {0,0,0,0,1,0,0,1},
            {0,0,0,0,0,1,1,0}
    };
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int d = Integer.parseInt(br.readLine());
        System.out.println(walk(d)[0][0]);
    }

    public static long[][] walk(int cnt) {
        if (cnt == 1) {
            return ROUTE;
        }
        long[][] tmp = walk(cnt / 2);
        tmp = multiply(tmp, tmp);
        if (cnt % 2 == 1) {
            tmp = multiply(tmp,ROUTE);
        }
        return tmp;
    }

    public static long[][] multiply(long[][] a, long[][] b) {
        long[][] result = new long[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return result;
    }
}

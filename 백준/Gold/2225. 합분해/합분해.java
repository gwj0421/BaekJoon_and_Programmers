import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SumUP {
    private final int n;
    private final int k;

    public SumUP(int n, int k) {
        this.n = n;
        this.k = k;
    }

    public int getN() {
        return n;
    }

    public int getK() {
        return k;
    }

    public void calDp() {
        int[][] dp = new int[getK() + 1][getN() + 1];
        for (int i = 0; i < getN() + 1; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i < getK() + 1; i++) {
            dp[i][0] = 1;
            for (int j = 1; j < getN() + 1; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000000;
            }
        }
        System.out.println(dp[getK()][getN()]);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        SumUP sumUP = new SumUP(n, k);
        sumUP.calDp();
    }
}
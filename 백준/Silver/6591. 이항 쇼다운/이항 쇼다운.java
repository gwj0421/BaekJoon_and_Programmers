import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (n == 0 && k == 0) {
                break;
            }
            System.out.println(comb(n, k));
            
        }
    }

    private static BigInteger comb(int n, int r) {
        if (r > n - r) {
            r = n - r;
        }

        BigInteger numerator = BigInteger.ONE;  // 분자 (n! 부분)
        BigInteger denominator = BigInteger.ONE;  // 분모 (r! 부분)

        // C(n, r) = n * (n-1) * ... * (n-r+1) / (1 * 2 * ... * r)
        for (int i = 0; i < r; i++) {
            numerator = numerator.multiply(BigInteger.valueOf(n - i));
            denominator = denominator.multiply(BigInteger.valueOf(i + 1));
        }

        return numerator.divide(denominator);  // 분자 / 분모
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final long MOD = 1_000_000_007;

    /**
     * f(n+2) = f(n+1) + f(n)
     * A =
     * [ f(n+2) ]
     * [ f(n+1) ]
     * B =
     * [ 1 1 ]
     * [ 1 0 ]
     * C =
     * [ f(n+1) ]
     * [ f(n)   ]
     * C' =
     * [ f(n) ]
     * [ f(n-1) ]
     * <p>
     * A = B * C이고 C = B * C'이다. 그렇다면 위 2개의 항등식을 정리하면
     * A = B의 몇승 * 기본값 형태로 수정할 수 있을 것이다.
     * <p>
     * C = B * C' 형태에서
     * n = 1일때 => B의 개수 1
     * n = 2일때 => B의 개수 2
     * n = 3일때 => B의 개수 3
     * n = n일때 => B의 개수 n
     * 이 됨으로 결국
     * <p>
     * L =
     * [ f(1) ]
     * [ f(0) ]
     * C = B * C' = B^(n) * L가 된다
     * <p>
     * 이를 A= B * C에 적용하면 결국
     * A = B * C = B^(n+1) * L가 된다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        long n = Long.parseLong(br.readLine());
        Fibonacci.printFibonacci(n);
    }

    static class Fibonacci {
        private static final int MOD = 1_000_000_007;
        private static final long[][] INIT_FIBONACCI_MATRIX = new long[][]{{1, 1}, {1, 0}};

        /*
         * B =
         * [ 1 1 ]
         * [ 1 0 ]
         * C =
         * [ f(n+1) ]
         * [ f(n)   ]
         * C' =
         * [ f(n) ]
         * [ f(n-1) ]
         * L =
         * [ f(1) ]
         * [ f(0) ]
         *
         * C = B^(n) * L
         */
        public static void printFibonacci(long n) {

            long[][] B = calculateMatrixUsingPower(n);
            long[][] L = new long[][]{{1}, {0}};
            long[][] C = multipleMatrix(B, L);
            System.out.println(C[1][0]);
        }

        private static long[][] calculateMatrixUsingPower(long n) {
            if (n == 1) {
                return INIT_FIBONACCI_MATRIX;
            }
            long[][] matrix = calculateMatrixUsingPower(n / 2);
            matrix = multipleMatrix(matrix, matrix);
            if (n % 2 == 0) {
                return matrix;
            } else {
                return multipleMatrix(matrix, INIT_FIBONACCI_MATRIX);
            }
        }

        private static long[][] multipleMatrix(long[][] a, long[][] b) {
            if (!checkCanMultipleMatrix(a, b)) {
                throw new IllegalArgumentException();
            }
            int m = a.length, n = b.length, r = b[0].length;
            long[][] c = new long[m][r];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < r; j++) {
                    for (int k = 0; k < n; k++) {
                        c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                    }
                }
            }
            return c;
        }

        private static boolean checkCanMultipleMatrix(long[][] a, long[][] b) {
            return a[0].length == b.length;
        }
    }
}
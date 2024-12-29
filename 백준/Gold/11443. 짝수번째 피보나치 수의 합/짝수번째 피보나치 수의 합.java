import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final long MOD = 1_000_000_007;
    
    // f3 = f2 + f1 => f2 = f3 - f1
    // f5 = f4 + f3 => f4 = f5 - f3
    // f7 = f6 + f5 => f6 = f7 - f5 
    
    // f0 = 0
    // f2 = f3 - f1
    // f4 = f5 - f3
    // f6 = f7 - f5
    // fn-1 = fn - fn-2
    // fn = fn+1 - fn-1
    // sigma(짝수 n) = fn+1 - f1
    // sigma(홀수 n) = fn - f1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        long n = Long.parseLong(br.readLine());

        long[][] matrix = getFibonacci(n);
        long f1 = 1;
        
        if (n % 2 == 0) {
            // sigma(짝수 n) = fn+1 - f1
            System.out.println(matrix[0][0] - f1);
        } else {
            // sigma(홀수 n) = fn - f1
            System.out.println(matrix[0][1] - f1);
        }
    }

    private static long[][] getFibonacci(long n) {
        if (n == 1) {
            return new long[][]{{1, 1}, {1, 0}};
        }
        long[][] fibonacci = getFibonacci(n / 2);
        if (n % 2 == 0) {
            return matrixMultiplication(fibonacci, fibonacci);
        } else {
            return matrixMultiplication(matrixMultiplication(fibonacci, fibonacci), new long[][]{{1, 1}, {1, 0}});
        }
    }

    private static long[][] matrixMultiplication(long[][] a, long[][] b) {
        long[][] c = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }
}
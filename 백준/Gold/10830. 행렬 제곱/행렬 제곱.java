import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        int[][] matrix = new int[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        Calculator calculator = new Calculator(n, b, matrix);
        calculator.calculate();
    }

    static class Calculator {
        private static final int MOD = 1000;
        private final int n;
        private final long b;
        private final int[][] matrix;

        public Calculator(int n, long b, int[][] matrix) {
            this.n = n;
            this.b = b;
            this.matrix = matrix;
        }

        public int getN() {
            return n;
        }

        public long getB() {
            return b;
        }

        public int[][] getMatrix() {
            return matrix;
        }

        public void calculate() {
            int[][] result = separate(getB());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < getN(); i++) {
                for (int j = 0; j < getN(); j++) {
                    sb.append(result[i][j] % MOD).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }

        public int[][] separate(long num) {
            if (num == 1) {
                return getMatrix();
            }
            int[][] tmp = separate(num / 2);
            tmp = multiplyMatrix(tmp, tmp);
            if (num % 2 == 1) {
                tmp = multiplyMatrix(tmp, getMatrix());
            }
            return tmp;
        }

        private static int[][] multiplyMatrix(int[][] a, int[][] b) {
            int m = a.length;
            int r = b[0].length;
            if (a[0].length != b.length) {
                throw new IllegalArgumentException();
            }
            int n = b.length;
            int[][] result = new int[m][r];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < r; j++) {
                    for (int k = 0; k < n; k++) {
                        result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % MOD;
                    }
                }
            }
            return result;
        }
    }

}

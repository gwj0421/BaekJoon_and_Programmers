import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static final int[][] MOVE = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][];
        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        Square square = new Square(n, m, board);
        square.search();

    }

    static class Square {
        private final int n;
        private final int m;
        private final int[][] board;
        private int[][] dp;

        public Square(int n, int m, int[][] board) {
            this.n = n;
            this.m = m;
            this.board = board;
            int[][] dp = new int[n + 1][m + 1];
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + board[i - 1][j - 1];
                }
            }
            this.dp = dp;
        }

        public void search() {
            int maxSquareExtent = 0;
            for (int y = 1; y < n + 1; y++) {
                for (int x = 1; x < m + 1; x++) {
                    int squareSize = 1;
                    while (y + squareSize - 1 <= n && x + squareSize - 1 <= m) {
                        int extent = dp[y + squareSize - 1][x + squareSize - 1] - dp[y - 1][x + squareSize - 1] - dp[y + squareSize - 1][x - 1] + dp[y - 1][x - 1];
                        if (extent != squareSize * squareSize) {
                            break;
                        }
                        maxSquareExtent = Math.max(maxSquareExtent, extent);
                        squareSize++;
                    }

                }
            }
            System.out.println(maxSquareExtent);
        }

    }

}


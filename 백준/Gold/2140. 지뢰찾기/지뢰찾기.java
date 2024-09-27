import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 0 1 1 1 1 1 1 0
    // 0 _ b _ _ b _ 0
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        Block block = new Block(n, board);
        block.searchBoom();

    }

    static class Block {
        private static final int[][] MOVE_TOP = new int[][]{{1, -1}, {1, 0}, {1, 1}};
        private static final int[][] MOVE_LEFT_SIDE = new int[][]{{-1, 1}, {0, 1}, {1, 1}};
        private static final int[][] MOVE_RIGHT_SIDE = new int[][]{{-1, -1}, {0, -1}, {1, -1}};
        private static final int[][] MOVE_BOTTOM = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}};
        private final int n;
        private char[][] board;

        public Block(int n, char[][] board) {
            this.n = n;
            this.board = board;
        }

        public void searchBoom() {
            int ny, nx;
            if (n < 3) {
                System.out.println(0);
                return;
            }

            for (int x = 0; x < n; x++) {
                for (int d = 0; d < 3; d++) {
                    nx = x + MOVE_TOP[d][1];
                    if (-1 < nx && nx < n) {
                        judgeBoom(0, x, 1, nx);
                    }
                }
            }
            for (int y = 1; y < n - 1; y++) {
                for (int d = 0; d < 3; d++) {
                    ny = y + MOVE_LEFT_SIDE[d][0];
                    if (-1 < ny && ny < n) {
                        judgeBoom(y, 0, ny, 1);
                    }
                }
                for (int d = 0; d < 3; d++) {
                    ny = y + MOVE_RIGHT_SIDE[d][0];
                    if (-1 < ny && ny < n) {
                        judgeBoom(y, n - 1, ny, n - 2);
                    }
                }
            }
            for (int x = 0; x < n; x++) {
                for (int d = 0; d < 3; d++) {
                    nx = x + MOVE_BOTTOM[d][1];
                    if (-1 < nx && nx < n) {
                        judgeBoom(n - 1, x, n - 2, nx);
                    }
                }
            }

            int ans = 0;
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < n - 1; j++) {
                    if (board[i][j] != 'e') {
                        ans++;
                    }
                }
            }
            System.out.println(ans);
        }

        private void judgeBoom(int y, int x, int ny, int nx) {
            if (board[ny][nx] == 'b') {
                board[y][x]--;
            } else if (board[ny][nx] == '#') {
                if (board[y][x] > '0') {
                    board[ny][nx] = 'b';
                    board[y][x]--;
                } else {
                    board[ny][nx] = 'e';
                }
            }
        }
    }
}
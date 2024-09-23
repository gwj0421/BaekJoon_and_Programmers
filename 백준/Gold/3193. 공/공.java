import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] ball = new int[2];
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (line[j] == 'L') {
                    ball = new int[]{i, j};
                    line[j] = '.';
                }
            }
            board[i] = line;
        }
        char[] orders = new char[k];
        for (int i = 0; i < k; i++) {
            orders[i] = br.readLine().charAt(0);
        }
        Game game = new Game(n, k, orders, ball, board);
        game.start();
    }

    static class Game {
        private static final int[][] MOVE = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        private final int n;
        private final int k;
        private final char[] orders;
        private int[] ball;
        private char[][] board;

        public Game(int n, int k, char[] orders, int[] ball, char[][] board) {
            this.n = n;
            this.k = k;
            this.orders = orders;
            this.ball = ball;
            this.board = board;
        }

        public void setBall(int[] ball) {
            this.ball = ball;
        }

        public void setBoard(char[][] board) {
            this.board = board;
        }

        public void start() {
            int gravityDir = 0;
            int rotate = 0;
            for (char order : orders) {
                if (order == 'L') {
                    gravityDir = (gravityDir - 1) % 4;
                    if (gravityDir < 0) {
                        gravityDir = 3;
                    }
                    rotate--;
                } else if (order == 'D') {
                    gravityDir = (gravityDir + 1) % 4;
                    rotate++;
                }
                activateGravity(gravityDir);
            }
            rotate %= 4;
            board[ball[0]][ball[1]] = 'L';
            if (rotate == 1 || rotate == -3) {
                rotateClockwise();
            } else if (rotate == -1 || rotate == 3) {
                rotateCounterClockwise();
            } else if (rotate == 2 || rotate == -2) {
                rotateHalf();
            }
            for (char[] chars : board) {
                System.out.println(String.valueOf(chars));
            }
        }

        public void rotateClockwise() {
            char[][] result = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result[j][n - 1 - i] = board[i][j];
                }
            }
            setBall(new int[]{ball[1], n - 1 - ball[0]});
            setBoard(result);
        }

        public void rotateCounterClockwise() {
            char[][] result = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result[n - 1 - j][i] = board[i][j];
                }
            }
            setBall(new int[]{n - 1 - ball[1], ball[0]});
            setBoard(result);
        }

        public void rotateHalf() {
            char[][] result = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result[n - 1 - i][n - 1 - j] = board[i][j];
                }
            }
            setBall(new int[]{n - 1 - ball[0], n - 1 - ball[1]});
            setBoard(result);
        }

        private void activateGravity(int d) {
            int ny = ball[0];
            int nx = ball[1];
            while (-1 < ny + MOVE[d][0] && ny + MOVE[d][0] < n && -1 < nx + MOVE[d][1] && nx + MOVE[d][1] < n &&
                    board[ny + MOVE[d][0]][nx + MOVE[d][1]] != 'X') {
                ny += MOVE[d][0];
                nx += MOVE[d][1];
            }
            setBall(new int[]{ny, nx});
        }
    }

}
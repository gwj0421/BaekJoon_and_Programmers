import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

class Main {
    private static final boolean DEBUG_MODE = false;
    private static final int[][] MOVE = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final int HEIGHT_SIZE = 5;
    private static final int WIDTH_SIZE = 9;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            char[][] board = new char[HEIGHT_SIZE][WIDTH_SIZE];
            int pinCnt = 0;
            for (int j = 0; j < HEIGHT_SIZE; j++) {
                board[j] = br.readLine().toCharArray();
                for (int k = 0; k < WIDTH_SIZE; k++) {
                    if (board[j][k] == 'o') {
                        pinCnt++;
                    }
                }
            }
            PegSolitaire pegSolitaire = new PegSolitaire(board, pinCnt);
            pegSolitaire.play();

            br.readLine();
        }
    }

    static class PegSolitaire {
        private char[][] board;
        private final int pinCnt;
        private int minPinCnt;
        private int minMoveCnt;

        public PegSolitaire(char[][] board, int pinCnt) {
            this.board = board;
            this.pinCnt = pinCnt;
            this.minPinCnt = Integer.MAX_VALUE;
            this.minMoveCnt = Integer.MAX_VALUE;
        }

        public void play() {
            dfs(pinCnt, 0);
            System.out.println(minPinCnt + " " + minMoveCnt);
        }

        private void dfs(int remainPinCnt, int moveCnt) {
            if (remainPinCnt < this.minPinCnt) {
                this.minPinCnt = remainPinCnt;
                this.minMoveCnt = moveCnt;
            } else if (remainPinCnt == this.minPinCnt && moveCnt < this.minMoveCnt) {
                this.minMoveCnt = moveCnt;
            }
            for (int i = 0; i < HEIGHT_SIZE; i++) {
                for (int j = 0; j < WIDTH_SIZE; j++) {
                    if (board[i][j] == 'o') {
                        for (int k = 0; k < 4; k++) {
                            int ny = i + MOVE[k][0];
                            int nx = j + MOVE[k][1];
                            if (-1 < ny && ny < HEIGHT_SIZE && -1 < nx && nx < WIDTH_SIZE && board[ny][nx] == 'o') {
                                int nny = ny + MOVE[k][0];
                                int nnx = nx + MOVE[k][1];
                                if (-1 < nny && nny < HEIGHT_SIZE && -1 < nnx && nnx < WIDTH_SIZE && board[nny][nnx] == '.') {
                                    board[i][j] = board[ny][nx] = '.';
                                    board[nny][nnx] = 'o';
                                    dfs(remainPinCnt - 1, moveCnt + 1);
                                    board[i][j] = board[ny][nx] = 'o';
                                    board[nny][nnx] = '.';
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Game2048 {
    private static final int[] MOVE_Y = {0, 0, 1, -1};
    private static final int[] MOVE_X = {1, -1, 0, 0};
    private final int boardSize;
    private int[][] board;
    private int maxValue = 0;

    public Game2048(int boardSize, int[][] board) {
        this.boardSize = boardSize;
        this.board = board;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void moveBlock(int y, int x, int d, boolean[][] isMerge) {
        if (getBoard()[y][x] == 0) {
            return;
        }
        int originY = y;
        int originX = x;
        int value = getBoard()[originY][originX];

        while (-1 < y + MOVE_Y[d] && y + MOVE_Y[d] < getBoardSize() && -1 < x + MOVE_X[d] && x + MOVE_X[d] < getBoardSize()) {
            int nextY = y + MOVE_Y[d];
            int nextX = x + MOVE_X[d];
            if (getBoard()[nextY][nextX] == 0) {
                y = nextY;
                x = nextX;
                continue;
            }
            if (getBoard()[nextY][nextX] != value || (getBoard()[nextY][nextX] == value && isMerge[nextY][nextX])) {
                getBoard()[originY][originX] = 0;
                getBoard()[y][x] += value;
                return;
            } else {
                isMerge[nextY][nextX] = true;
                getBoard()[originY][originX] = 0;
                getBoard()[nextY][nextX] += value;
                return;
            }
        }
        getBoard()[originY][originX] = 0;
        getBoard()[y][x] += value;
    }

    public void move(int d) {
        boolean[][] isMerge = new boolean[getBoardSize()][getBoardSize()];
        if (d == 0) {
            for (int i = 0; i < getBoardSize(); i++) {
                for (int j = getBoardSize() - 1; j > -1; j--) {
                    moveBlock(i, j, d, isMerge);
                }
            }
        } else if (d == 1) {
            for (int i = 0; i < getBoardSize(); i++) {
                for (int j = 0; j < getBoardSize(); j++) {
                    moveBlock(i, j, d, isMerge);
                }
            }
        } else if (d == 2) {
            for (int i = getBoardSize() - 1; i > -1; i--) {
                for (int j = 0; j < getBoardSize(); j++) {
                    moveBlock(i, j, d, isMerge);
                }
            }
        } else {
            for (int i = 0; i < getBoardSize(); i++) {
                for (int j = 0; j < getBoardSize(); j++) {
                    moveBlock(i, j, d, isMerge);
                }
            }
        }
    }

    public void updateMaxValue() {
        int value = 0;
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                if (value < getBoard()[i][j]) {
                    value = getBoard()[i][j];
                }
            }
        }
        if (value > getMaxValue()) {
            setMaxValue(value);
        }
    }

    public int[][] copy2dArr() {
        int[][] copy = new int[getBoardSize()][];
        for (int i = 0; i < getBoardSize(); i++) {
            copy[i] = Arrays.copyOf(getBoard()[i], getBoardSize());
        }
        return copy;
    }

    public void dfs(int cnt) {
        if (cnt == 5) {
            updateMaxValue();
            return;
        }
        for (int i = 0; i < 4; i++) {
            int[][] origin = copy2dArr();
            move(i);
            dfs(cnt + 1);
            setBoard(origin);
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] inputLine = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(inputLine[j]);
            }
        }

        Game2048 game2048 = new Game2048(n, board);
        game2048.dfs(0);
        System.out.println(game2048.getMaxValue());
    }
}
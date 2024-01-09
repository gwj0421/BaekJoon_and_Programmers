import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Robot {
    private static final int[] MOVE_Y = {0, 0, 1};
    private static final int[] MOVE_X = {1, -1, 0};
    private final int n;
    private final int m;
    private final int[][] board;
    private final boolean[][] visit;
    private int maxValue;

    public Robot(int n, int m, int[][] board) {
        this.n = n;
        this.m = m;
        this.board = board;
        this.maxValue = 0;
        boolean[][] visit = new boolean[n][m];
        visit[0][0] = true;
        this.visit = visit;
    }

    public int getN() {
        return n;
    }

    public boolean[][] getVisit() {
        return visit;
    }

    public int getM() {
        return m;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void compareMaxValue(int value) {
        if (getMaxValue() < value) {
            setMaxValue(value);
        }
    }

    public void dfs(int y, int x, int value) {
        if (y == getN() - 1 && x == getM() - 1) {
            compareMaxValue(value);
            return;
        }
        for (int i = 0; i < 3; i++) {
            int nextY = y + MOVE_Y[i];
            int nextX = x + MOVE_X[i];
            if (-1 < nextY && nextY < getN() && -1 < nextX && nextX < getM()) {
                if (!getVisit()[nextY][nextX]) {
                    getVisit()[nextY][nextX] = true;
                    dfs(nextY, nextX, value + getBoard()[nextY][nextX]);
                    getVisit()[nextY][nextX] = false;
                }
            }
        }
    }

    public void dp() {
        int[][] valueBoard = new int[getN()][getM()];
        int[][] temp = new int[2][getM()];
        valueBoard[0][0] = getBoard()[0][0];
        for (int i = 1; i < getM(); i++) {
            valueBoard[0][i] = valueBoard[0][i - 1] + getBoard()[0][i];
        }

        for (int i = 1; i < getN(); i++) {
            temp[0][0] = valueBoard[i - 1][0] + getBoard()[i][0];
            for (int j = 1; j < getM(); j++) {
                temp[0][j] = Math.max(temp[0][j - 1], valueBoard[i - 1][j]) + getBoard()[i][j];
            }
            temp[1][getM() - 1] = valueBoard[i - 1][getM() - 1] + getBoard()[i][getM() - 1];
            for (int j = getM() - 2; j > -1; j--) {
                temp[1][j] = Math.max(temp[1][j + 1], valueBoard[i - 1][j]) + getBoard()[i][j];
            }
            for (int j = 0; j < getM(); j++) {
                valueBoard[i][j] = Math.max(temp[0][j], temp[1][j]);
            }
        }
        setMaxValue(valueBoard[getN() - 1][getM() - 1]);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] inputLine = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(inputLine[j]);
            }
        }
        Robot robot = new Robot(n, m, board);
//        robot.dfs(0, 0, board[0][0]);
        robot.dp();
        System.out.println(robot.getMaxValue());
    }
}
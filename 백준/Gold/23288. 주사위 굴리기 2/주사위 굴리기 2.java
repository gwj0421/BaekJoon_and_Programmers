import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Dice {
    private final Deque<Integer> vertical;
    private final Deque<Integer> horizontal;

    public Dice(List<Integer> verticalInfo, List<Integer> horizontalInfo) {
        this.vertical = makeDefaultPos(verticalInfo);
        this.horizontal = makeDefaultPos(horizontalInfo);
    }

    public Deque<Integer> getVertical() {
        return vertical;
    }

    public Deque<Integer> getHorizontal() {
        return horizontal;
    }

    private Deque<Integer> makeDefaultPos(List<Integer> numbers) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int num : numbers) {
            deque.add(num);
        }
        return deque;
    }

    public int getBottomNum() {
        return getVertical().peekLast();
    }

    // 2
    // 1
    // 5
    // 6 3 4
    public void move(int d) {
        if (d == 0) {
            getHorizontal().addFirst(getVertical().pollLast());
            getVertical().addLast(getHorizontal().pollLast());
            int hf = getHorizontal().pollFirst();
            int top = getHorizontal().peekFirst();
            getHorizontal().addFirst(hf);
            int vf = getVertical().pollFirst();
            getVertical().pollFirst();
            getVertical().addFirst(top);
            getVertical().addFirst(vf);
        } else if (d == 1) {
            getVertical().addFirst(getVertical().pollLast());
            int vf = getVertical().pollFirst();
            int top = getVertical().peekFirst();
            getVertical().addFirst(vf);
            int hf = getHorizontal().pollFirst();
            getHorizontal().pollFirst();
            getHorizontal().addFirst(top);
            getHorizontal().addFirst(hf);

        } else if (d == 2) {
            getHorizontal().addLast(getVertical().pollLast());
            getVertical().addLast(getHorizontal().pollFirst());
            int hf = getHorizontal().pollFirst();
            int top = getHorizontal().peekFirst();
            getHorizontal().addFirst(hf);
            int vf = getVertical().pollFirst();
            getVertical().pollFirst();
            getVertical().addFirst(top);
            getVertical().addFirst(vf);
        } else if (d == 3) {
            getVertical().addLast(getVertical().pollFirst());
            int vf = getVertical().pollFirst();
            int top = getVertical().peekFirst();
            getVertical().addFirst(vf);
            int hf = getHorizontal().pollFirst();
            getHorizontal().pollFirst();
            getHorizontal().addFirst(top);
            getHorizontal().addFirst(hf);
        }
    }
}

class DiceGame {
    private static final int[] MOVE_Y = {0, 1, 0, -1};
    private static final int[] MOVE_X = {1, 0, -1, 0};
    private final int n;
    private final int m;
    private final int k;
    private final int[][] board;
    private final Dice dice;
    private final int[][] scoreBoard;
    private int score;
    private int d;

    public DiceGame(int n, int m, int k, int[][] board) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.board = board;
        this.dice = new Dice(List.of(2, 1, 5, 6), List.of(4, 1, 3));
        this.scoreBoard = makeScoreBoard();
        this.score = 0;
        this.d = 0;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public int getK() {
        return k;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public Dice getDice() {
        return dice;
    }

    public int getD() {
        return d;
    }

    public int[][] getScoreBoard() {
        return scoreBoard;
    }

    public int[][] makeScoreBoard() {
        boolean[][] visit = new boolean[getN()][getM()];
        int[][] scoreBoard = new int[getN()][getM()];
        for (int i = 0; i < getN(); i++) {
            for (int j = 0; j < getM(); j++) {
                if (!visit[i][j]) {
                    visit[i][j] = true;
                    int b = getBoard()[i][j];
                    Queue<int[]> groupRoute = new ArrayDeque<>();
                    groupRoute.add(new int[]{i, j});
                    Queue<int[]> needVisit = new ArrayDeque<>();
                    needVisit.add(new int[]{i, j});
                    while (!needVisit.isEmpty()) {
                        int[] now = needVisit.poll();
                        for (int l = 0; l < 4; l++) {
                            int nextY = now[0] + MOVE_Y[l];
                            int nextX = now[1] + MOVE_X[l];
                            if (-1 < nextY && nextY < getN() && -1 < nextX && nextX < getM() && !visit[nextY][nextX] && getBoard()[nextY][nextX] == b) {
                                needVisit.add(new int[]{nextY, nextX});
                                groupRoute.add(new int[]{nextY, nextX});
                                visit[nextY][nextX] = true;
                            }
                        }
                    }
                    int c = groupRoute.size();
                    while (!groupRoute.isEmpty()) {
                        int[] groupPos = groupRoute.poll();
                        scoreBoard[groupPos[0]][groupPos[1]] = b * c;
                    }
                }
            }
        }
        return scoreBoard;
    }

    public void rotateClockwise() {
        this.d = (this.d + 1) % 4;
    }

    public void rotateCounterClockwise() {
        this.d--;
        if (this.d == -1) {
            this.d = 3;
        }
    }

    public void rotateBack() {
        this.d = (this.d + 2) % 4;
    }

    public void plusScore(int[] pos) {
        this.score += scoreBoard[pos[0]][pos[1]];
    }

    public void selectNextDir(int a, int b) {
        if (a > b) {
            rotateClockwise();
        } else if (a < b) {
            rotateCounterClockwise();
        }
    }

    public void moveDice() {
        int[] pos = new int[2];
        for (int i = 0; i < getK(); i++) {
            int nextY = pos[0] + MOVE_Y[getD()];
            int nextX = pos[1] + MOVE_X[getD()];
            if (!(-1 < nextY && nextY < getN() && -1 < nextX && nextX < getM())) {
                rotateBack();
                nextY = pos[0] + MOVE_Y[getD()];
                nextX = pos[1] + MOVE_X[getD()];
            }
            pos = new int[]{nextY, nextX};
            getDice().move(getD());

            plusScore(pos);

            selectNextDir(getDice().getBottomNum(), getBoard()[pos[0]][pos[1]]);
        }
        System.out.println(getScore());
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] inputLine = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(inputLine[j]);
            }
        }

        DiceGame diceGame = new DiceGame(n, m, k, board);
        diceGame.moveDice();
    }
}
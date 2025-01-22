import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[][] board = new String[n][m];
        String[] input;
        GameSituation initGameSituation = new GameSituation();
        for (int i = 0; i < n; i++) {
            input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                board[i][j] = input[j];
                if (input[j].equals("o")) {
                    initGameSituation.initCoin(i, j);
                }
            }
        }

        TwoCoin twoCoin = new TwoCoin(n, m, board, initGameSituation);
        twoCoin.play();
    }
}

class TwoCoin {
    private final int n;
    private final int m;
    private final String[][] board;
    private final Queue<GameSituation> queue;
    private final boolean[][][][] visit;

    public TwoCoin(int n, int m, String[][] board, GameSituation initGameSituation) {
        this.n = n;
        this.m = m;
        this.board = board;
        Queue<GameSituation> queue = new ArrayDeque<>();
        queue.add(initGameSituation);
        this.queue = queue;
        boolean[][][][] visit = new boolean[n][m][n][m];
        visit[initGameSituation.getCoinA().getY()][initGameSituation.getCoinA().getX()][initGameSituation.getCoinB().getY()][initGameSituation.getCoinB().getX()] = true;
        this.visit = visit;
    }

    public void play() {
        int outCnt;
        while (!queue.isEmpty()) {
            GameSituation now = queue.poll();
            if (now.getMoveCnt() > 9) {
                continue;
            }
            for (int dir = 0; dir < 4; dir++) {
                Coord nextCoinA = now.getCoinA().expectNextCoord(dir);
                Coord nextCoinB = now.getCoinB().expectNextCoord(dir);
                outCnt = 0;
                if (!nextCoinA.isIn(n,m)) {
                    outCnt++;
                }
                if (!nextCoinB.isIn(n,m)) {
                    outCnt++;
                }
                if (outCnt == 0) {
                    nextCoinA.processMoveWall(board, now.getCoinA());
                    nextCoinB.processMoveWall(board, now.getCoinB());
                    if (!visit[nextCoinA.getY()][nextCoinA.getX()][nextCoinB.getY()][nextCoinB.getX()]) {
                        visit[nextCoinA.getY()][nextCoinA.getX()][nextCoinB.getY()][nextCoinB.getX()] = true;
                        queue.add(new GameSituation(nextCoinA, nextCoinB, now.getMoveCnt() + 1));
                    }
                } else if (outCnt == 1) {
                    System.out.println(now.getMoveCnt() + 1);
                    return;
                }
            }
        }
        System.out.println(-1);
    }
}


class GameSituation {
    private Coord coinA;
    private Coord coinB;
    private int moveCnt;

    public GameSituation() {
        this.moveCnt = 0;
    }

    public GameSituation(Coord coinA, Coord coinB, int moveCnt) {
        this.coinA = coinA;
        this.coinB = coinB;
        this.moveCnt = moveCnt;
    }

    public Coord getCoinA() {
        return coinA;
    }

    public Coord getCoinB() {
        return coinB;
    }

    public int getMoveCnt() {
        return moveCnt;
    }

    public void initCoin(int i, int j) {
        if (this.coinA == null) {
            this.coinA = new Coord(i, j);
            return;
        }
        this.coinB = new Coord(i, j);
    }
}

class Coord {
    private static final int[][] MOVE_PATTERN = new int[][]{
            {0, -1},
            {0, 1},
            {-1, 0},
            {1, 0}
    };
    private int y;
    private int x;

    public Coord(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean isIn(int n,int m) {
        return -1 < y && y < n && -1 < x && x < m;
    }

    public Coord expectNextCoord(int dir) {
        return new Coord(y + MOVE_PATTERN[dir][0], x + MOVE_PATTERN[dir][1]);
    }

    public void processMoveWall(String[][] board,Coord original) {
        if (board[y][x].equals("#")) {
            this.y = original.getY();
            this.x = original.getX();
        }
    }
}
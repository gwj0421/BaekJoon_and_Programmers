import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        char[][] board = new char[r][];
        char[] inputLine;
        int[] gahee = new int[2];
        for (int i = 0; i < r; i++) {
            inputLine = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (inputLine[j] == 'G') {
                    gahee = new int[]{i, j};
                }
            }
            board[i] = inputLine;
        }

        SweetPotato sweetPotato = new SweetPotato(r, c, t, board, gahee);
        sweetPotato.search();

    }
}

class SweetPotato {
    private static final int[][] MOVE_PATTERN = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private final int r;
    private final int c;
    private final int t;
    private final char[][] board;
    private int[] gahee;
    private int maxCnt;

    public SweetPotato(int r, int c, int t, char[][] board, int[] gahee) {
        this.r = r;
        this.c = c;
        this.t = t;
        this.board = board;
        this.gahee = gahee;
        this.maxCnt = 0;
    }

    public void search() {
        boolean[][] isEaten = new boolean[r][c];
        searchPotato(0, gahee[0], gahee[1], 0, isEaten);
        System.out.println(maxCnt);
    }

    private void searchPotato(int depth, int i, int j, int cnt, boolean[][] isEaten) {
        if (depth == t) {
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }
        searchPotato(depth + 1, i, j, cnt, isEaten);
        for (int k = 0; k < 4; k++) {
            int ni = i + MOVE_PATTERN[k][0];
            int nj = j + MOVE_PATTERN[k][1];
            if (-1 < ni && ni < r && -1 < nj && nj < c) {
                if (board[ni][nj] == 'S' && !isEaten[ni][nj]) {
                    isEaten[ni][nj] = true;
                    searchPotato(depth + 1, ni, nj, cnt + 1, isEaten);
                    isEaten[ni][nj] = false;
                } else if (board[ni][nj] != '#') {
                    searchPotato(depth + 1, ni, nj, cnt, isEaten);
                }
            }
        }
    }
}
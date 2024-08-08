import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] canPlaceBishop = new boolean[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                if (st.nextToken().equals("1")) {
                    canPlaceBishop[i][j] = true;
                } else {
                    canPlaceBishop[i][j] = false;
                }
            }
        }
        Chess chess = new Chess(n, canPlaceBishop);
        chess.calculate();
    }

    static class Chess {
        private static final int[][] MOVE_PATTERN = new int[][]{{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};
        private final int n;
        private final boolean[][] canPlaceBishop;
        private int blackBishopCnt = 0;
        private int whiteBishopCnt = 0;

        public Chess(int n, boolean[][] canPlaceBishop) {
            this.n = n;
            this.canPlaceBishop = canPlaceBishop;

        }

        public int getN() {
            return n;
        }

        public boolean[][] getCanPlaceBishop() {
            return canPlaceBishop;
        }

        public int getBlackBishopCnt() {
            return blackBishopCnt;
        }

        public int getWhiteBishopCnt() {
            return whiteBishopCnt;
        }

        public void setBlackBishopCnt(int blackBishopCnt) {
            this.blackBishopCnt = blackBishopCnt;
        }

        public void setWhiteBishopCnt(int whiteBishopCnt) {
            this.whiteBishopCnt = whiteBishopCnt;
        }

        public void calculate() {
            boolean[][] visit = new boolean[getN()][getN()];
            searchWhite(0, 0, 0, visit);
            searchBlack(0, 1, 0, visit);
            System.out.println(getWhiteBishopCnt() + getBlackBishopCnt());
        }

        public void searchBlack(int y, int x, int cnt, boolean[][] visit) {
            if (cnt > getBlackBishopCnt()) {
                setBlackBishopCnt(cnt);
            }
            if (x > getN() - 1) {
                y++;
                x = y % 2 == 0 ? 1 : 0;
            }
            if (y > getN() - 1) {
                return;
            }

            if (canPlace(y, x, visit)) {
                visit[y][x] = true;
                searchBlack(y, x + 2, cnt + 1, visit);
                visit[y][x] = false;
            }
            searchBlack(y, x + 2, cnt, visit);
        }

        public void searchWhite(int y, int x, int cnt, boolean[][] visit) {
            if (cnt > getWhiteBishopCnt()) {
                setWhiteBishopCnt(cnt);
            }
            if (x > getN() - 1) {
                y++;
                x = y % 2 == 0 ? 0 : 1;
            }
            if (y > getN() - 1) {
                return;
            }

            if (canPlace(y, x, visit)) {
                visit[y][x] = true;
                searchWhite(y, x + 2, cnt + 1, visit);
                visit[y][x] = false;
            }
            searchWhite(y, x + 2, cnt, visit);
        }


        public boolean canPlace(int y, int x, boolean[][] visit) {
            if (!getCanPlaceBishop()[y][x]) {
                return false;
            }
            int ny, nx;
            for (int i = 0; i < 4; i++) {
                ny = y + MOVE_PATTERN[i][0];
                nx = x + MOVE_PATTERN[i][1];
                while (-1 < ny && ny < getN() && -1 < nx && nx < getN()) {
                    if (visit[ny][nx]) {
                        return false;
                    }
                    ny += MOVE_PATTERN[i][0];
                    nx += MOVE_PATTERN[i][1];
                }
            }
            return true;
        }

    }
}

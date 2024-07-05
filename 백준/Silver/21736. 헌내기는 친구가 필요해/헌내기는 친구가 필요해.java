import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] board = new int[n][m];
        int doyeanY = 0, doyeanX = 0;
        for (int i = 0; i < n; i++) {
            String[] eachLine = sc.next().split("");
            for (int j = 0; j < m; j++) {
                if (eachLine[j].equals("I")) {
                    doyeanY = i;
                    doyeanX = j;
                    board[i][j] = 0;
                } else if (eachLine[j].equals("O")) {
                    board[i][j] = 0;
                } else if (eachLine[j].equals("P")) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = -1;
                }
            }
        }
        SearchAlgorithm search = new SearchAlgorithm(doyeanY, doyeanX, board);
        search.start();
    }

    static class Coordinate {
        public static final int[][] MOVE_PATTERN = new int[][]{
                new int[]{0, 1},
                new int[]{0, -1},
                new int[]{1, 0},
                new int[]{-1, 0}
        };
        private int y;
        private int x;

        public Coordinate(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public int predictNextY(int pattern) {
            return getY() + MOVE_PATTERN[pattern][0];
        }

        public int predictNextX(int pattern) {
            return getX() + MOVE_PATTERN[pattern][1];
        }
    }

    static class SearchAlgorithm {
        private int[][] board;
        private boolean[][] visit;
        private Queue<Coordinate> needVisit;

        public SearchAlgorithm(int y, int x, int[][] board) {
            this.board = board;
            boolean[][] visit = new boolean[board.length][board[0].length];
            visit[y][x] = true;
            this.visit = visit;
            Queue<Coordinate> needVisit = new LinkedList<>();
            needVisit.add(new Coordinate(y, x));
            this.needVisit = needVisit;
        }

        public int[][] getBoard() {
            return board;
        }

        public boolean[][] getVisit() {
            return visit;
        }

        public Queue<Coordinate> getNeedVisit() {
            return needVisit;
        }

        public void visit(int nextY, int nextX) {
            getVisit()[nextY][nextX] = true;
            getNeedVisit().add(new Coordinate(nextY, nextX));
        }

        public boolean meetPeople(Coordinate coordinate) {
            return getBoard()[coordinate.getY()][coordinate.getX()] == 1;
        }

        public void start() {
            int meetPeopleCnt = 0;
            while (!getNeedVisit().isEmpty()) {
                Coordinate now = getNeedVisit().poll();
                if (meetPeople(now)) {
                    meetPeopleCnt++;
                }
                for (int i = 0; i < 4; i++) {
                    int nextY = now.predictNextY(i);
                    int nextX = now.predictNextX(i);
                    if (checkCoordinate(nextY, nextX)) {
                        visit(nextY, nextX);
                    }
                }
            }
            if (meetPeopleCnt == 0) {
                System.out.println("TT");
                return;
            }
            System.out.println(meetPeopleCnt);
        }

        private boolean checkCoordinate(int y, int x) {
            return -1 < y && y < getBoard().length && -1 < x && x < getBoard()[0].length &&
                    !getVisit()[y][x] && getBoard()[y][x] != -1;
        }

    }
}

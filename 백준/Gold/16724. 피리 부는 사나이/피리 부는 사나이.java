import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[][] board = new String[n][];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().split("");
        }
        PiedPiper piedPiper = new PiedPiper(n, m, board);

        piedPiper.searchSafeZone();
    }

    static class PiedPiper {
        private static final Map<String, int[]> MOVE_PATTERN = Map.of("U", new int[]{-1, 0}, "D",
                new int[]{1, 0}, "L", new int[]{0, -1}, "R", new int[]{0, 1});
        private final int n;
        private final int m;
        private final String[][] board;

        public PiedPiper(int n, int m, String[][] board) {
            this.n = n;
            this.m = m;
            this.board = board;
        }

        public int getN() {
            return n;
        }

        public int getM() {
            return m;
        }

        public String[][] getBoard() {
            return board;
        }

        public void searchSafeZone() {
            int[][] visit = new int[getN()][getM()];
            int safeZoneCnt = 0;
            int areaIdx = 0;
            for (int i = 0; i < getN(); i++) {
                for (int j = 0; j < getM(); j++) {
                    if (visit[i][j] < 1) {
                        areaIdx++;
                        visit[i][j] = areaIdx;
                        Queue<int[]> needVisit = new LinkedList<>();
                        needVisit.add(new int[]{i, j});
                        while (!needVisit.isEmpty()) {
                            int[] now = needVisit.poll();
                            int nextY = now[0] + MOVE_PATTERN.get(getBoard()[now[0]][now[1]])[0];
                            int nextX = now[1] + MOVE_PATTERN.get(getBoard()[now[0]][now[1]])[1];
                            if (visit[nextY][nextX] < 1) {
                                needVisit.add(new int[]{nextY, nextX});
                                visit[nextY][nextX] = areaIdx;
                            } else if (visit[nextY][nextX] == areaIdx) {
                                safeZoneCnt++;
                            }
                        }
                    }
                }
            }
            System.out.println(safeZoneCnt);
        }
    }

}

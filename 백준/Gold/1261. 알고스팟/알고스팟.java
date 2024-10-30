import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        boolean[][] isWall = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < m; j++) {
                if (inputLine.charAt(j) == '1') {
                    isWall[i][j] = true;
                }
            }
        }

        AlgoSpot algoSpot = new AlgoSpot(n, m, isWall);
        algoSpot.activate();
    }

    static class AlgoSpot {
        private static final int[][] MOVE_PATTERN = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        private final int n;
        private final int m;
        private final boolean[][] isWall;

        public AlgoSpot(int n, int m, boolean[][] isWall) {
            this.n = n;
            this.m = m;
            this.isWall = isWall;
        }

        public void activate() {
            Deque<int[]> deque = new ArrayDeque<>();
            deque.add(new int[]{0, 0, 0});
            boolean[][] visit = new boolean[n][m];
            visit[0][0] = true;
            while (!deque.isEmpty()) {
                int[] now = deque.pollFirst();
                if (now[0] == (n - 1) && now[1] == (m - 1)) {
                    System.out.println(now[2]);
                    return;
                }
                for (int i = 0; i < 4; i++) {
                    int ny = now[0] + MOVE_PATTERN[i][0];
                    int nx = now[1] + MOVE_PATTERN[i][1];
                    if (-1 < ny && ny < n && -1 < nx && nx < m && !visit[ny][nx]) {
                        visit[ny][nx] = true;
                        if (isWall[ny][nx]) {
                            deque.addLast(new int[]{ny, nx, now[2] + 1});
                        } else {
                            deque.addFirst(new int[]{ny, nx, now[2]});
                        }
                    }
                }
            }
        }

    }
}



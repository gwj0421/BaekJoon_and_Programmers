import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static final Map<Character, int[]> MOVE = Map.of(
            'N', new int[]{-1, 0},
            'S', new int[]{1, 0},
            'W', new int[]{0, -1},
            'E', new int[]{0, 1}
    );

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] movePattern = new char[n][m];
        for (int i = 0; i < n; i++) {
            movePattern[i] = br.readLine().toCharArray();
        }
        SearchApple searchApple = new SearchApple(n, m, movePattern);
        searchApple.search();

    }


    static class SearchApple {

        private final int n;
        private final int m;
        private final char[][] appleMovePattern;

        public SearchApple(int n, int m, char[][] appleMovePattern) {
            this.n = n;
            this.m = m;
            this.appleMovePattern = appleMovePattern;
        }

        public void search() {
            int circleType = 0;
            int[][] visit = new int[n][m];
            for (int i = 0; i < n; i++) {
                Arrays.fill(visit[i], -1);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visit[i][j] < 0) {
                        visit[i][j] = circleType;
                        visit[i][j] = dfs(visit, new Coord(i, j), circleType);
                        if (visit[i][j] == circleType) {
                            circleType++;
                        }
                    }
                }
            }
            System.out.println(circleType);

        }

        private int dfs(int[][] visit, Coord now, int circleType) {
            Coord next = now.getNextCoord(appleMovePattern[now.y][now.x]);
            if (visit[next.y][next.x] < 0) {
                visit[next.y][next.x] = circleType;
                return visit[next.y][next.x] = dfs(visit, next, circleType);
            } else if (visit[next.y][next.x] != circleType) {
                return visit[next.y][next.x];
            }
            return circleType;
        }
    }

    static class Coord {
        private final int y;
        private final int x;

        public Coord(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Coord getNextCoord(char movePattern) {
            return new Coord(
                    this.y + MOVE.get(movePattern)[0],
                    this.x + MOVE.get(movePattern)[1]
            );
        }

        @Override
        public String toString() {
            return "Coord{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }
}



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        Map<Integer, List<Coordinate>> ground = new HashMap<>();
        int maxHeight = 0, minHeight = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int[] inputLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                maxHeight = Math.max(maxHeight, inputLine[j]);
                minHeight = Math.min(minHeight, inputLine[j]);
                if (ground.containsKey(inputLine[j])) {
                    ground.get(inputLine[j]).add(new Coordinate(i,j));
                } else {
                    ground.put(inputLine[j], new ArrayList<>(List.of(new Coordinate(i,j))));
                }
            }
        }

        boolean[][] isFlooding = new boolean[n][n];
        boolean[][] visit;
        Queue<Coordinate> needVisit;
        int maxAreaCnt = 1;
        int tmpAreaCnt;
        for (int rainHeight = minHeight; rainHeight < maxHeight; rainHeight++) {
            if (!ground.containsKey(rainHeight)) {
                continue;
            }
            for (Coordinate coord : ground.get(rainHeight)) {
                isFlooding[coord.y][coord.x] = true;
            }

            visit = new boolean[n][n];
            needVisit = new LinkedList<>();
            tmpAreaCnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!isFlooding[i][j] && !visit[i][j]) {
                        tmpAreaCnt++;
                        visit[i][j] = true;
                        needVisit.add(new Coordinate(i, j));
                        while (!needVisit.isEmpty()) {
                            Coordinate now = needVisit.poll();
                            for (int k = 0; k < 4; k++) {
                                Coordinate next = now.getNext(now, k);
                                if (-1 < next.y && next.y < n && -1 < next.x && next.x < n && !visit[next.y][next.x] && !isFlooding[next.y][next.x]) {
                                    visit[next.y][next.x] = true;
                                    needVisit.add(next);
                                }
                            }
                        }
                    }
                }
            }
            maxAreaCnt = Math.max(maxAreaCnt, tmpAreaCnt);
        }
        System.out.println(maxAreaCnt);

    }


    static class Coordinate {
        private static final int[][] MOVE_PATTERN = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        private final int y;
        private final int x;

        public Coordinate(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Coordinate getNext(Coordinate now, int d) {
            return new Coordinate(now.y + MOVE_PATTERN[d][0], now.x + MOVE_PATTERN[d][1]);
        }
    }
}
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    private static final int[][] MOVE = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();

        Map<Character, Set<Container>> containers = new HashMap<>();
        boolean[][] isOutside = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char containerType = storage[i].charAt(j);
                if (containers.containsKey(containerType)) {
                    containers.get(containerType).add(new Container(i, j));
                } else {
                    containers.put(containerType, new HashSet<>(Set.of(new Container(i, j))));
                }
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    isOutside[i][j] = true;
                }
            }
        }
        boolean[][] isOut = new boolean[n][m];

        for (String request : requests) {
            char target = request.charAt(0);
            if (containers.containsKey(target)) {
                if (request.length() == 1) {
                    boolean[][] nextIsOut = new boolean[n][m];
                    for (int i = 0; i < n; i++) {
                        nextIsOut[i] = isOut[i].clone();
                    }
                    for (Container container : containers.get(target)) {
                        if (isOut[container.getY()][container.getX()]) {
                            continue;
                        }
                        boolean[][] visit = new boolean[n][m];
                        visit[container.getY()][container.getX()] = true;
                        if (findExit(container.getY(), container.getX(), n, m, visit, isOut)) {
                            nextIsOut[container.getY()][container.getX()] = true;
                        }
                    }
                    isOut = nextIsOut;
                } else if (request.length() == 2) {
                    for (Container container : containers.get(target)) {
                        isOut[container.getY()][container.getX()] = true;
                    }
                }
            }
//            System.out.println(target);
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if (isOut[i][j]) {
//                        sb.append("   ");
//                    } else {
//                        sb.append("'"+storage[i].charAt(j)+"'");
//                    }
//                }
//                sb.append("\n");
//            }
//            System.out.println(sb);
//            System.out.println();
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!isOut[i][j]) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private boolean findExit(int y, int x, int n, int m, boolean[][] visit, boolean[][] isOut) {
        if (y == 0 || y == n - 1 || x == 0 || x == m - 1) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            int ny = y + MOVE[i][0];
            int nx = x + MOVE[i][1];
            if (-1 < ny && ny < n && -1 < nx && nx < m && !visit[ny][nx] && isOut[ny][nx]) {
                visit[ny][nx] = true;
                if (findExit(ny, nx, n, m, visit, isOut)) {
                    return true;
                }
            }
        }
        return false;
    }

}

class Container {
    private final int y;
    private final int x;

    public Container(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        int[][] visited = new int[n][m];
        visited[0][0] = 1;

        int[] controlY = {0, 0, 1, -1};
        int[] controlX = {1, -1, 0, 0};

        Queue<int[]> needVisited = new LinkedList<>();
        needVisited.add(new int[]{0, 0});

        while (!needVisited.isEmpty()) {
            int[] now = needVisited.poll();

            for (int i = 0; i < 4; i++) {
                int nextY = now[0] + controlY[i];
                int nextX = now[1] + controlX[i];
                if (-1 < nextY && nextY < n && -1 < nextX && nextX < m) {
                    if (maps[nextY][nextX] == 1 && visited[nextY][nextX] == 0) {
                        visited[nextY][nextX] = visited[now[0]][now[1]] + 1;
                        needVisited.add(new int[]{nextY, nextX});
                    }
                }
            }
        }

        return visited[n - 1][m - 1] != 0 ? visited[n - 1][m - 1] : -1;
    }
}
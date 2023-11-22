import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static final int MAX_LENGTH = 102;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] road = makeRoad(rectangle);
        Queue<int[]> needVisited = new LinkedList<>();
        needVisited.add(new int[]{characterY * 2, characterX * 2});

        int[] controlY = {0, 0, 1, -1};
        int[] controlX = {1, -1, 0, 0};
        while (!needVisited.isEmpty()) {
            int[] now = needVisited.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = now[0] + controlY[i];
                int nextX = now[1] + controlX[i];
                if (-1 < nextY && nextY < MAX_LENGTH && -1 < nextX && nextX < MAX_LENGTH) {
                    if (road[nextY][nextX] == 0) {
                        road[nextY][nextX] = road[now[0]][now[1]] + 1;
                        needVisited.add(new int[]{nextY, nextX});
                    }
                }
            }
        }

        return road[itemY * 2][itemX * 2] / 2;
    }

    private int[][] makeRoad(int[][] rectangles) {
        int[][] road = new int[MAX_LENGTH][MAX_LENGTH];
        for (int i = 0; i < road.length; i++) {
            Arrays.fill(road[i], -1);
        }
        for (int[] rectangle : rectangles) {
            int x1 = rectangle[0] * 2;
            int y1 = rectangle[1] * 2;
            int x2 = rectangle[2] * 2;
            int y2 = rectangle[3] * 2;
            for (int i = y1; i < y2 + 1; i++) {
                for (int j = x1; j < x2 + 1; j++) {
                    if (y1 < i && i < y2 && x1 < j && j < x2) {
                        road[i][j] = -2;
                    } else if (road[i][j] != -2) {
                        road[i][j] = 0;
                    }
                }
            }
        }
        return road;
    }
}
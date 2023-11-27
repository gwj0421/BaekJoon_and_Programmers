import java.util.*;

class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        int[] controlY = {0, 0, 1, -1};
        int[] controlX = {1, -1, 0, 0};
        int row = land.length;
        int col = land[0].length;
        int[] oil = new int[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (land[i][j] > 0) {
                    land[i][j] = 0;
                    Queue<int[]> needVisited = new LinkedList<>();
                    int left = j;
                    int right = j;
                    int oilAmount = 0;
                    needVisited.add(new int[]{i, j});
                    while (!needVisited.isEmpty()) {
                        int[] now = needVisited.poll();
                        left = Math.min(left, now[1]);
                        right = Math.max(right, now[1]);
                        oilAmount++;
                        for (int k = 0; k < 4; k++) {
                            int nextY = now[0] + controlY[k];
                            int nextX = now[1] + controlX[k];
                            if (-1 < nextY && nextY < row && -1 < nextX && nextX < col && land[nextY][nextX] > 0) {
                                land[nextY][nextX] = 0;
                                needVisited.add(new int[]{nextY, nextX});
                            }
                        }

                    }
                    for (int k = left; k < right + 1; k++) {
                        oil[k] += oilAmount;
                    }
                }
            }
        }
        return getMaxValue(oil);
    }

    private int getMaxValue(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            temp = Math.max(temp, arr[i]);
        }
        return temp;
    }
}
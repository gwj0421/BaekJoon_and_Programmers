class Solution {
    private static final int[][] MOVE = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int solution(String[][] board, int h, int w) {
        int n = board.length;
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nh = h + MOVE[i][0];
            int nw = w + MOVE[i][1];
            if (-1 < nh && nh < n && -1 < nw && nw < n && board[h][w].equals(board[nh][nw])) {
                cnt++;
            }
        }
        return cnt;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(
                new String[][]{{"blue", "red", "orange", "red"}, {"red", "red", "blue", "orange"}, {"blue", "orange", "red", "red"}, {"orange", "orange", "red", "blue"}},
                1, 1
        ));
    }
}
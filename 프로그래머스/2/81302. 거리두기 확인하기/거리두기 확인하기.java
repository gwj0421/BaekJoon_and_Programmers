class Solution {
    private static final int N = 5;
    private static final int[][] MOVE = new int[][]{{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[] solution(String[][] places) {
        int[] answer = new int[N];
        for (int i = 0; i < places.length; i++) {
            if (check(places[i])) {
                answer[i] = 1;
            }
        }
        return answer;
    }

    private boolean check(String[] place) {
        boolean[][] visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (place[i].charAt(j) == 'P') {
                    for (int k = 0; k < 5; k++) {
                        int ni = i + MOVE[k][0];
                        int nj = j + MOVE[k][1];
                        if (-1 < ni && ni < N && -1 < nj && nj < N && place[ni].charAt(nj) != 'X') {
                            if (visit[ni][nj]) {
                                return false;
                            }
                            visit[ni][nj] = true;
                        }
                    }
                }
            }
        }
        return true;
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(new String[][]{
                        {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                        {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                        {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                        {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                        {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
                })
        );
        ;
    }
}
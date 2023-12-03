import java.io.*;
import java.util.Map;


public class Main {
    private static final int[] CONTROL_Y = {0, 1, 1};
    private static final int[] CONTROL_X = {1, 1, 0};
    private static int N;
    private static int ans;

    public static void main(String[] args) throws IOException {
//        System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Boolean> pipeMap = Map.of("0", false, "1", true);
        N = Integer.parseInt(br.readLine());
        ans = 0;

        boolean[][] visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String[] _input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                visit[i][j] = pipeMap.get(_input[j]);
            }
        }
        visit[0][0] = true;
        visit[0][1] = true;
        dfs(visit,0,1,0);

        System.out.println(ans);
    }

    private static void dfs(boolean[][] board, int y, int x, int beforeType) {
        if (y == N - 1 && x == N - 1) {
            ans++;
            return;
        }
        for (int i = 0; i < 3; i++) {
            int nextY = y + CONTROL_Y[i];
            int nextX = x + CONTROL_X[i];
            if (-1 < nextY && nextY < N && -1 < nextX && nextX < N && !board[nextY][nextX] && Math.abs(beforeType - i) < 2) {
                if (i == 1 && (board[y][nextX] || board[nextY][x])) {
                    continue;
                }
                board[nextY][nextX] = true;
                dfs(board, nextY, nextX, i);
                board[nextY][nextX] = false;
            }
        }
    }
}


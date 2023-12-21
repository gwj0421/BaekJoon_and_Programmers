import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] MOVE_Y = {0, 0, 1, -1};
    private static final int[] MOVE_X = {1, -1, 0, 0};
    private static int N;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int textCaseIdx = 0;
        while (true) {
            textCaseIdx++;
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            int[][] board = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(input[j]);
                }
            }
            int[][] loss = new int[N][N];
            for (int[] eachLine : loss) {
                Arrays.fill(eachLine, 2242);
            }
            loss[0][0] = board[0][0];
            boolean[][] visit = new boolean[N][N];
            visit[0][0] = true;
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
            minHeap.add(new int[]{board[0][0], 0, 0});
            while (!minHeap.isEmpty()) {
                int[] now = minHeap.poll();
                for (int i = 0; i < 4; i++) {
                    int nextY = now[1] + MOVE_Y[i];
                    int nextX = now[2] + MOVE_X[i];
                    if (-1 < nextY && nextY < N && -1 < nextX && nextX < N) {
                        if (!visit[nextY][nextX]) {
                            loss[nextY][nextX] = Math.min(loss[nextY][nextX], loss[now[1]][now[2]] + board[nextY][nextX]);
                            minHeap.add(new int[]{loss[nextY][nextX], nextY, nextX});
                            visit[nextY][nextX] = true;
                        }
                    }
                }
            }
            System.out.println(String.format("Problem %s: %d", textCaseIdx, loss[N - 1][N - 1]));
        }
    }
}
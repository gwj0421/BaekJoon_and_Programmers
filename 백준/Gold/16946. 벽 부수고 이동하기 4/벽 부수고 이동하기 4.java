import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[][] MOVE = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int[] areaCnt = new int[1000000];
        boolean[][] visit = new boolean[n][m];
        int areaIdx = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0 && !visit[i][j]) {
                    visit[i][j] = true;
                    Queue<int[]> needVisit = new LinkedList<>();
                    needVisit.add(new int[]{i, j});
                    while (!needVisit.isEmpty()) {
                        int[] now = needVisit.poll();
                        areaCnt[areaIdx]++;
                        board[now[0]][now[1]] = areaIdx;
                        for (int k = 0; k < 4; k++) {
                            int ny = now[0] + MOVE[k][0];
                            int nx = now[1] + MOVE[k][1];
                            if (-1 < ny && ny < n && -1 < nx && nx < m) {
                                if (!visit[ny][nx] && board[ny][nx] == 0) {
                                    visit[ny][nx] = true;
                                    needVisit.offer(new int[]{ny, nx});
                                }
                            }
                        }
                    }
                    areaIdx++;
                }
            }
        }
        Set<Integer> around;
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    ans[i][j]++;
                    around = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int ny = i + MOVE[k][0];
                        int nx = j + MOVE[k][1];
                        if (-1 < ny && ny < n && -1 < nx && nx < m) {
                            if (board[ny][nx] > 1) {
                                around.add(board[ny][nx]);
                            }
                        }
                    }
                    for (int aroundAreaIdx : around) {
                        ans[i][j] += areaCnt[aroundAreaIdx];
                    }
                    ans[i][j] %= 10;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(ans[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

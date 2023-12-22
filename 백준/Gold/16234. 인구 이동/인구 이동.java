import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int L;
    private static int R;
    private static final int[] MOVE_Y = {0, 0, 1, -1};
    private static final int[] MOVE_X = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] countries = new int[2 * N - 1][2 * N - 1];
        int posY = 0;
        for (int i = 0; i < N; i++) {
            String[] inputLine = br.readLine().split(" ");
            int posX = 0;
            for (int j = 0; j < N; j++) {
                countries[posY][posX] = Integer.parseInt(inputLine[j]);
                posX += 2;
            }
            posY += 2;
        }

        int moveCnt = 0;
        while (true) {
            makeUnionLine(countries);
            boolean isMove = false;
            boolean[][] visit = new boolean[2 * N - 1][2 * N - 1];
            for (int i = 0; i < 2 * N - 1; i += 2) {
                for (int j = 0; j < 2 * N - 1; j += 2) {
                    if (!visit[i][j]) {
                        visit[i][j] = true;
                        List<int[]> unions = bfs(i, j, countries, visit);
                        int sum = 0;
                        for (int[] union : unions) {
                            sum += countries[union[0]][union[1]];
                        }
                        for (int[] union : unions) {
                            if (countries[union[0]][union[1]] != sum / unions.size()) {
                                isMove = true;
                                countries[union[0]][union[1]] = sum / unions.size();
                            }
                        }
                    }
                }
            }
            if (!isMove) {
                break;
            } else {
                moveCnt++;
            }
        }
        System.out.println(moveCnt);
    }

    private static void makeUnionLine(int[][] countries) {
        for (int i = 0; i < 2 * N - 1; i += 2) {
            for (int j = 0; j < 2 * N - 1; j += 2) {
                for (int k = 0; k < 4; k++) {
                    int nextY = i + MOVE_Y[k] * 2;
                    int nextX = j + MOVE_X[k] * 2;
                    if (-1 < nextY && nextY < 2 * N - 1 && -1 < nextX && nextX < 2 * N - 1) {
                        int diff = Math.abs(countries[i][j] - countries[nextY][nextX]);
                        if (L <= diff && diff <= R) {
                            countries[i + MOVE_Y[k]][j + MOVE_X[k]] = -1;
                        } else {
                            countries[i + MOVE_Y[k]][j + MOVE_X[k]] = -2;
                        }
                    }

                }
            }
        }
    }

    private static List<int[]> bfs(int i, int j, int[][] countries, boolean[][] visit) {
        List<int[]> unions = new ArrayList<>();
        Queue<int[]> needVisited = new LinkedList<>();
        needVisited.add(new int[]{i, j});
        unions.add(new int[]{i, j});
        while (!needVisited.isEmpty()) {
            int[] now = needVisited.poll();
            for (int k = 0; k < 4; k++) {
                int nextY = now[0] + MOVE_Y[k] * 2;
                int nextX = now[1] + MOVE_X[k] * 2;
                if (-1 < nextY && nextY < 2 * N - 1 && -1 < nextX && nextX < 2 * N - 1) {
                    if (countries[now[0] + MOVE_Y[k]][now[1] + MOVE_X[k]] == -1 && !visit[nextY][nextX]) {
                        visit[nextY][nextX] = true;
                        unions.add(new int[]{nextY, nextX});
                        needVisited.add(new int[]{nextY, nextX});
                    }
                }
            }
        }
        return unions;
    }
}
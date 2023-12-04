import java.io.*;
import java.util.*;

public class Main {
    private static final int[] CONTROL_Y = {0, 0, 1, -1};
    private static final int[] CONTROL_X = {1, -1, 0, 0};

    private static int N, M, D;
    private static int ans = 0;
    private static int lastEnemy = N - 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        List<int[]> archerPos = new ArrayList<>();
        boolean[][] enemy = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] enemyLine = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                if (enemyLine[j].equals("1")) {
                    enemy[i][j] = true;
                    lastEnemy = Math.min(lastEnemy, i);
                }
            }
        }
        setArchers(archerPos, 0, -1, new int[]{-1, -1, -1});

        for (int[] archers : archerPos) {
            int removeCnt = 0;
            boolean[][] enemyPos = copy2d(enemy);
            for (int bottom = N; bottom > lastEnemy; bottom--) {
                if (bottom == 0) {
                    break;
                }
                List<int[]> attackTarget = new ArrayList<>();
                for (int archer : archers) {
                    int[] target = getAttack(bottom, archer, enemyPos);
                    if (target[0] != Integer.MAX_VALUE) {
                        attackTarget.add(target);
                    }
                }
                for (int[] removeEnemy : attackTarget) {
                    if (enemyPos[removeEnemy[0]][removeEnemy[1]]) {
                        enemyPos[removeEnemy[0]][removeEnemy[1]] = false;
                        removeCnt++;
                    }
                }
            }
            ans = Math.max(ans, removeCnt);
        }

        System.out.println(ans);
    }

    public static void setArchers(List<int[]> archerPos, int cnt, int archer, int[] pos) {
        if (cnt == 3) {
            archerPos.add(Arrays.copyOf(pos, 3));
            return;
        }
        for (int i = archer + 1; i < M; i++) {
            pos[cnt] = i;
            setArchers(archerPos, cnt + 1, i, pos);
            pos[cnt] = -1;
        }
    }

    public static boolean[][] copy2d(boolean[][] arr) {
        boolean[][] result = new boolean[N][];
        for (int i = 0; i < N; i++) {
            result[i] = Arrays.copyOf(arr[i], M);
        }
        return result;
    }

    public static int[] getAttack(int bottom, int archer, boolean[][] enemy) {
        if (enemy[bottom - 1][archer]) {
            return new int[]{bottom - 1, archer};
        }
        int[] canAttackEnemy = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        int canAttackEnemyDistance = Integer.MAX_VALUE;
        Queue<int[]> needVisited = new LinkedList<>();
        needVisited.add(new int[]{bottom - 1, archer, 1});
        boolean[][] visited = new boolean[N][M];
        visited[bottom - 1][archer] = true;
        while (!needVisited.isEmpty()) {
            int[] now = needVisited.poll();
            if (now[2] > D || now[2] > canAttackEnemyDistance) {
                continue;
            }
            if (enemy[now[0]][now[1]]) {
                if (now[2] < canAttackEnemyDistance) {
                    canAttackEnemyDistance = now[2];
                    canAttackEnemy[0] = now[0];
                    canAttackEnemy[1] = now[1];
                } else if (now[2] == canAttackEnemyDistance && now[1] < canAttackEnemy[1]) {
                    canAttackEnemy[0] = now[0];
                    canAttackEnemy[1] = now[1];
                }
            }
            for (int i = 0; i < 4; i++) {
                int nextY = now[0] + CONTROL_Y[i];
                int nextX = now[1] + CONTROL_X[i];
                if (-1 < nextY && nextY < N && -1 < nextX && nextX < M && !visited[nextY][nextX] && nextY < bottom) {
                    needVisited.add(new int[]{nextY, nextX, now[2] + 1});
                    visited[nextY][nextX] = true;
                }
            }
        }
        return canAttackEnemy;
    }
}


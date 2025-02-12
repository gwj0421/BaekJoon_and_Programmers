import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

//        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] coords = new int[4][2];
        for (int i = 0; i < 4; i++) {
            coords[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Algorithm algorithm = new Algorithm(coords);
        algorithm.solve();
    }
}

class Algorithm {
    private final int[][] coords;
    private double[][] dist;
    private double ans;

    public Algorithm(int[][] coords) {
        this.coords = coords;
        double[][] dist = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    dist[i][j] = Math.sqrt(
                            Math.pow(coords[i][0] - coords[j][0], 2) + Math.pow(coords[i][1] - coords[j][1], 2)
                    );
                }
            }
        }
        this.dist = dist;
        this.ans = Integer.MAX_VALUE;
    }

    public void solve() {
        boolean[] visit = new boolean[4];
        visit[0] = true;
        search(1, 0, 0, visit);
        System.out.println((int) ans);
    }

    public void search(int depth, int idx, double moveDist, boolean[] visit) {
        if (depth == 4) {
            ans = Math.min(ans, moveDist);
            return;
        }
        for (int i = 1; i < 4; i++) {
            if (!visit[i]) {
                visit[i] = true;
                search(depth + 1, i, moveDist + dist[idx][i], visit);
                visit[i] = false;
            }
        }
    }
}
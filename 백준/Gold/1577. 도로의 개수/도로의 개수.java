import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(br.readLine());
        Set<Edge> warningRoads = new HashSet<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (b > d || (b == d && a > c)) {
                warningRoads.add(new Edge(d, c, b, a));
            } else {
                warningRoads.add(new Edge(b, a, d, c));
            }
        }
        Algorithm algorithm = new Algorithm(m, n, warningRoads);
        algorithm.printAnswer();
    }
}

class Algorithm {
    private final int m;
    private final int n;
    private final Set<Edge> warningRoads;

    public Algorithm(int m, int n,Set<Edge> warningRoads) {
        this.m = m;
        this.n = n;
        this.warningRoads = warningRoads;
    }

    public void printAnswer() {
        long[][] dp = new long[m+1][n+1];
        for (int i = 1; i <= n; i++) {
            if (warningRoads.contains(new Edge(0, i - 1, 0, i))) {
                break;
            }
            dp[0][i] = 1;
        }
        for (int i = 1; i <= m; i++) {
            if (warningRoads.contains(new Edge(i - 1, 0, i, 0))) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (!warningRoads.contains(new Edge(i, j - 1, i, j))) {
                    dp[i][j] += dp[i][j - 1];
                }
                if (!warningRoads.contains(new Edge(i - 1, j, i, j))) {
                    dp[i][j] += dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[m][n]);
    }
}

class Edge {
    private final int ay;
    private final int ax;
    private final int by;
    private final int bx;

    public Edge(int ay, int ax, int by, int bx) {
        this.ay = ay;
        this.ax = ax;
        this.by = by;
        this.bx = bx;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Edge edge = (Edge) object;
        return ay == edge.ay && ax == edge.ax && by == edge.by && bx == edge.bx;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ay, ax, by, bx);
    }
}
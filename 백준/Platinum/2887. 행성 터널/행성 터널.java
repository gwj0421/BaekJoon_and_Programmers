import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        int[][] planet = new int[n][];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int xCoord = Integer.parseInt(st.nextToken());
            int yCoord = Integer.parseInt(st.nextToken());
            int zCoord = Integer.parseInt(st.nextToken());
            planet[i] = new int[]{i, xCoord, yCoord, zCoord};
        }

        PriorityQueue<PlanetEdge> pq = new PriorityQueue<>();
        for (int i = 1; i < 4; i++) {
            int dimension = i;
            Arrays.sort(planet, Comparator.comparingInt(o -> o[dimension]));
            for (int j = 0; j < n-1; j++) {
                pq.offer(new PlanetEdge(planet[j][0], planet[j+1][0], planet[j + 1][dimension] - planet[j][dimension]));
            }
        }

        int[] head = new int[n];
        for (int i = 0; i < n; i++) {
            head[i] = i;
        }
        long totalCost = 0;
        while (!pq.isEmpty()) {
            PlanetEdge now = pq.poll();
            if (union(head, now.getStartIdx(), now.getEndIdx())) {
                continue;
            }
            totalCost += now.getCost();
        }
        System.out.println(totalCost);
    }

    static class PlanetEdge implements Comparable<PlanetEdge> {
        private final int startIdx;
        private final int endIdx;
        private final long cost;

        public PlanetEdge(int startIdx, int endIdx, long cost) {
            this.startIdx = startIdx;
            this.endIdx = endIdx;
            this.cost = cost;
        }

        public int getStartIdx() {
            return startIdx;
        }

        public int getEndIdx() {
            return endIdx;
        }

        public long getCost() {
            return cost;
        }

        @Override
        public int compareTo(PlanetEdge o) {
            return Long.compare(this.cost,o.getCost());
        }
    }
    private static boolean union(int[] head, int a, int b) {
        int rootA = find(head, a);
        int rootB = find(head, b);
        if (rootA == rootB) {
            return true;
        }
        head[rootB] = rootA;
        return false;
    }

    private static int find(int[] head, int target) {
        if (head[target] == target) {
            return target;
        }
        return head[target] = find(head, head[target]);
    }
}




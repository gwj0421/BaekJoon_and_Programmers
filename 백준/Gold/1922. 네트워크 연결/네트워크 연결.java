import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            int[] inputLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pq.add(new Edge(inputLine[0], inputLine[1], inputLine[2]));
        }

        int[] parent = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
        }

        int totalCost = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (union(parent, now.p1, now.p2)) {
                totalCost += now.cost;
            }
        }
        System.out.println(totalCost);
    }

    private static boolean union(int[] parent, int idx1, int idx2) {
        int root1 = find(parent, idx1);
        int root2 = find(parent, idx2);
        if (root1 < root2) {
            parent[root2] = root1;
        } else if (root1 > root2) {
            parent[root1] = root2;
        } else {
            return false;
        }
        return true;
    }

    private static int find(int[] parent, int idx) {
        if (parent[idx] == idx) {
            return idx;
        }
        return parent[idx] = find(parent, parent[idx]);
    }

    static class Edge implements Comparable<Edge> {
        private final int p1;
        private final int p2;
        private final int cost;

        public Edge(int p1, int p2, int cost) {
            this.p1 = p1;
            this.p2 = p2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

}
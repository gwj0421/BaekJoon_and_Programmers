import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        Map<Integer, List<int[]>> edges = new HashMap<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            edges.put(p1, new ArrayList<>());
            while (true) {
                int p2 = Integer.parseInt(st.nextToken());
                if (p2 == -1) {
                    break;
                }
                int cost = Integer.parseInt(st.nextToken());
                edges.get(p1).add(new int[]{p2, cost});
            }
        }

        TreeAlgorithm treeAlgorithm = new TreeAlgorithm(n, edges);
        treeAlgorithm.searchDiameter();

    }

    static class TreeAlgorithm {
        private final int n;
        private final Map<Integer, List<int[]>> edges;
        private int diameterNode;
        private int maxDiameter;
        private boolean[] visit;

        public TreeAlgorithm(int n, Map<Integer, List<int[]>> edges) {
            this.n = n;
            this.edges = edges;
            this.diameterNode = 1;
            this.maxDiameter = 0;
        }

        public void searchDiameter() {
            initVisit();
            dfs(1, 0);

            initVisit();
            dfs(diameterNode, 0);

            System.out.println(maxDiameter);
        }

        private void initVisit() {
            boolean[] visit = new boolean[n + 1];
            this.visit = visit;
        }

        private void dfs(int parent, int cost) {
            if (cost > maxDiameter) {
                maxDiameter = cost;
                diameterNode = parent;
            }
            visit[parent] = true;
            for (int[] child : edges.get(parent)) {
                if (!visit[child[0]]) {
                    dfs(child[0], cost + child[1]);
                    visit[child[0]] = true;
                }
            }
        }

    }

}
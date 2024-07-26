import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            edges.put(i, new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            Edge edge = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            edges.get(edge.getP1()).add(edge.getP2());
            edges.get(edge.getP2()).add(edge.getP1());
        }

        List<Integer> queries = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            queries.add(Integer.parseInt(br.readLine()));
        }


        Tree tree = new Tree(n, r, q, edges, queries);
        tree.solveQuery();

    }


    static class Tree {
        private final int n;
        private final int r;
        private final int q;
        private final Map<Integer, List<Integer>> edges;
        private final List<Integer> queries;
        private boolean[] visit;
        private int[] subTreeCnt;

        public Tree(int n, int r, int q, Map<Integer, List<Integer>> edges, List<Integer> queries) {
            this.n = n;
            this.r = r;
            this.q = q;
            this.edges = edges;
            this.queries = queries;
        }

        public int getN() {
            return n;
        }

        public int getR() {
            return r;
        }

        public int getQ() {
            return q;
        }

        public Map<Integer, List<Integer>> getEdges() {
            return edges;
        }

        public List<Integer> getQueries() {
            return queries;
        }

        public boolean[] getVisit() {
            return visit;
        }

        public int[] getSubTreeCnt() {
            return subTreeCnt;
        }

        public void clearVisit() {
            boolean[] visit = new boolean[getN() + 1];
            visit[getR()] = true;
            this.visit = visit;

            int[] subTreeCnt = new int[getN() + 1];
            Arrays.fill(subTreeCnt, 1);
            this.subTreeCnt = subTreeCnt;

        }

        public void solveQuery() {
            clearVisit();
            dfs(getR());
            for (int query : getQueries()) {
                System.out.println(getSubTreeCnt()[query]);
            }
        }

        private int dfs(int root) {
            for (int sub : edges.get(root)) {
                if (!getVisit()[sub]) {
                    getVisit()[sub] = true;
                    getSubTreeCnt()[root] += dfs(sub);
                    getVisit()[sub] = false;
                }
            }
            return getSubTreeCnt()[root];
        }
    }

    static class Edge {
        private final int p1;
        private final int p2;

        public Edge(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public int getP1() {
            return p1;
        }

        public int getP2() {
            return p2;
        }
    }

}

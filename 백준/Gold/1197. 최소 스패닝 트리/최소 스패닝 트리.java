import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 13
    //
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
        }
        Kruskal kruskal = new Kruskal(v, e, edges);
        kruskal.solve();
    }

    static class Kruskal extends MST {

        public Kruskal(int v, int e, List<Edge> edges) {
            super(v, e, edges);
        }

        public void solve() {
            int[] head = new int[getV()];
            for (int i = 0; i < getV(); i++) {
                head[i] = i;
            }
            Queue<Edge> needVisit = new PriorityQueue<>(getEdges());
            int totalCost = 0;
            while (!needVisit.isEmpty()) {
                Edge edge = needVisit.poll();
                if (!union(head, edge.getStart(), edge.getEnd())) {
                    totalCost += edge.getCost();
                }
            }
            System.out.println(totalCost);
        }

        public boolean union(int[] head, int a, int b) {
            int headA = find(head, a);
            int headB = find(head, b);
            if (headA == headB) {
                return true;
            }
            if (headA < headB) {
                head[headB] = headA;
            } else {
                head[headA] = headB;
            }
            return false;
        }

        public int find(int[] head, int searchIdx) {
            if (head[searchIdx] == searchIdx) {
                return searchIdx;
            }
            return head[searchIdx] = find(head, head[searchIdx]);
        }

    }

    static class MST {
        private final int v;
        private final int e;
        private final List<Edge> edges;

        public MST(int v, int e, List<Edge> edges) {
            this.v = v;
            this.e = e;
            this.edges = edges;
        }

        public int getV() {
            return v;
        }

        public int getE() {
            return e;
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }

    static class Edge implements Comparable<Edge> {
        private final int start;
        private final int end;
        private final int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.getCost(), o.getCost());
        }
    }
}

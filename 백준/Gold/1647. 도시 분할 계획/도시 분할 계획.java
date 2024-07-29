import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_CARD_NUMBER = 1000000;
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Queue<Node> needVisit = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            needVisit.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int[] head = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            head[i] = i;
        }
        int routeCost = 0;
        int totalCost = 0;
        while (!needVisit.isEmpty()) {
            Node node = needVisit.poll();
            if (!Node.checkCircle(head, node.getP1(), node.getP2())) {
                routeCost = node.getCost();
                totalCost += routeCost;
            }
        }
        System.out.println(totalCost - routeCost);

    }

    static class Node implements Comparable<Node> {
        private final int p1;
        private final int p2;
        private final int cost;

        public Node(int p1, int p2, int cost) {
            this.p1 = p1;
            this.p2 = p2;
            this.cost = cost;
        }

        public int getP1() {
            return p1;
        }

        public int getP2() {
            return p2;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.getCost());
        }

        public static boolean checkCircle(int[] head, int a, int b) {
            int rootA = find(head, a);
            int rootB = find(head, b);
            if (rootA == rootB) {
                return true;
            }
            if (rootA < rootB) {
                head[rootB] = rootA;
            } else {
                head[rootA] = rootB;
            }
            return false;
        }

        private static int find(int[] head, int target) {
            if (head[target] == target) {
                return target;
            }
            return head[target] = find(head, head[target]);
        }
    }
}

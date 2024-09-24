import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MOD = 1_000_000_007;

    static class Node {
        private Node l;
        private Node r;
        private long v;

        public Node() {
            this.l = this.r = null;
            this.v = 0;
        }

        public void setL(Node l) {
            this.l = l;
        }

        public void setR(Node r) {
            this.r = r;
        }

        public void setV(long v) {
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }
        SegmentTree segmentTree = new SegmentTree(n, numbers);
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                segmentTree.updateQuery(b, c);
            } else if (a == 2) {
                segmentTree.getSubtotalQuery(b, c - 1);
            }
        }
    }

    static class SegmentTree {
        private final int n;
        private final int[] numbers;
        private Node tree;

        public SegmentTree(int n, int[] numbers) {
            this.n = n;
            this.numbers = numbers;
            Node tree = new Node();
            init(tree, 0, n - 1);
            this.tree = tree;
        }

        private void init(Node node, int s, int e) {
            if (s == e) {
                node.setV(numbers[s]);
                return;
            }
            node.setL(new Node());
            node.setR(new Node());

            int mid = (s + e) / 2;
            init(node.l, s, mid);
            init(node.r, mid + 1, e);
            node.setV(node.l.v * node.r.v % MOD);
        }

        public void updateQuery(int b, int c) {
            update(tree, 0, n - 1, b, c);
        }

        public void getSubtotalQuery(int b, int c) {
            System.out.println(getSubtotal(tree, 0, n - 1, b, c));
        }

        private void update(Node tree, int s, int e, int b, int c) {
            if (s == e) {
                tree.setV(c);
                return;
            }
            int mid = (s + e) / 2;
            if (b <= mid) {
                update(tree.l, s, mid, b, c);
            } else {
                update(tree.r, mid + 1, e, b, c);
            }
            tree.setV(tree.l.v * tree.r.v % MOD);
        }

        private long getSubtotal(Node node, int s, int e, int b, int c) {
            if (c < s || e < b) {
                return 1;
            }
            if (b <= s && e <= c) {
                return node.v;
            }
            int m = (s + e) / 2;
            return getSubtotal(node.l, s, m, b, c) * getSubtotal(node.r, m + 1, e, b, c) % MOD;
        }
    }
}
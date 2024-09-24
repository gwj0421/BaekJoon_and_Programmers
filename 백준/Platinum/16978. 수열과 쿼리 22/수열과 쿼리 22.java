import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine());

        int[][] queries = new int[m][4];
        for (int queryIdx = 0; queryIdx < m; queryIdx++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int i = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken());
                queries[queryIdx] = new int[]{type, i, v, 0};
            } else if (type == 2) {
                int k = Integer.parseInt(st.nextToken());
                int i = Integer.parseInt(st.nextToken()) - 1;
                int j = Integer.parseInt(st.nextToken()) - 1;
                queries[queryIdx] = new int[]{type, k, i, j};
            }
        }
        OfflineQuery offlineQuery = new OfflineQuery(n, m, numbers, queries);
        offlineQuery.activate();
    }

    static class OfflineQuery {
        private final int n;
        private final int m;
        private final int[] numbers;
        private final int[][] queries;
        private Node[] trees;

        public OfflineQuery(int n, int m, int[] numbers, int[][] queries) {
            this.n = n;
            this.m = m;
            this.numbers = numbers;
            this.queries = queries;
            this.trees = new Node[101010];
            trees[0] = new Node();
            build(trees[0], 0, n - 1);
        }

        public void activate() {
            int prvIdx = 0;
            int nowIdx = 1;
            for (int[] query : queries) {
                if (query[0] == 1) {
                    trees[nowIdx] = new Node();
                    add(trees[prvIdx++], trees[nowIdx++], 0, n - 1, query[1], query[2]);
                } else if (query[0] == 2) {
                    System.out.println(query(trees[query[1]], 0, n - 1, query[2], query[3]));
                }
            }
        }

        void build(Node node, int s, int e) {
            if (s == e) {
                node.setValue(numbers[s]);
                return;
            }
            int m = (s + e) / 2;
            node.setLeft(new Node());
            node.setRight(new Node());
            build(node.left, s, m);
            build(node.right, m + 1, e);
            node.setValue(node.left.value + node.right.value);
        }

        public void add(Node prv, Node now, int s, int e, int x, long v) {
            if (s == e) {
                now.setValue(v);
                return;
            }
            int m = (s + e) / 2;
            if (x <= m) {
                now.setLeft(new Node());
                now.setRight(prv.right);
                add(prv.left, now.left, s, m, x, v);
            } else {
                now.setLeft(prv.left);
                now.setRight(new Node());
                add(prv.right, now.right, m + 1, e, x, v);
            }
            now.setValue(now.left.value + now.right.value);
        }

        public long query(Node node, int s, int e, int l, int r) {
            if (r < s || e < l) {
                return 0;
            }
            if (l <= s && e <= r) {
                return node.value;
            }
            int m = (s + e) / 2;
            return query(node.left, s, m, l, r) + query(node.right, m + 1, e, l, r);
        }
    }

    static class Node {
        private Node left;
        private Node right;
        private long value;

        public Node() {
            this.left = this.right = null;
            value = 0;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }
}
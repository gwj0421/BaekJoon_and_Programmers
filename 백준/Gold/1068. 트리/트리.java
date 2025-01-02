import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        int[] parents = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < n; i++) {
            if (parents[i] != -1) {
                nodes[parents[i]].addChildren(nodes[i]);
            }
        }

        int targetIdx = Integer.parseInt(br.readLine());
        nodes[targetIdx].removeChildren();

        int ans = 0;
        boolean flag;
        for (Node node : nodes) {
            if (!node.isRemoved) {
                if (node.children.size() == 0) {
                    ans++;
                } else {
                    flag = false;
                    for (Node child : node.children) {
                        if (!child.isRemoved) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        ans++;
                    }
                }
            }
        }
//        for (Node node : nodes) {
//            System.out.println(node);
//        }
        System.out.println(ans);
    }

    static class Node {
        private final int idx;
        private boolean isRemoved;
        private Set<Node> children;

        public Node(int idx) {
            this.idx = idx;
            this.isRemoved = false;
            this.children = new HashSet<>();
        }

        public void addChildren(Node child) {
            this.children.add(child);
        }

        public void removeChildren() {
            this.isRemoved = true;
            for (Node child : children) {
                child.removeChildren();
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", isRemoved=" + isRemoved +
                    ", children=" + children +
                    '}';
        }
    }
}
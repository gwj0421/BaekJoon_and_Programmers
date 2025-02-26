import java.util.*;

//class Tmp {
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        System.out.println(Arrays.toString(
//                solution.solution(new int[]{11, 9, 3, 2, 4, 6}, new int[][]{{9, 11}, {2, 3}, {6, 3}, {3, 4}})
//        ));;
//
//    }
//}
class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int n = nodes.length;

        Map<Integer, List<Integer>> rel = new HashMap<>();
        for (int node : nodes) {
            rel.put(node, new ArrayList<>());
        }
        for (int[] edge : edges) {
            rel.get(edge[0]).add(edge[1]);
            rel.get(edge[1]).add(edge[0]);
        }
        List<Set<Integer>> trees = new ArrayList<>();
        boolean[] used = new boolean[1_000_001];

        for (int node : nodes) {
            if (!used[node]) {
                used[node] = true;
                Set<Integer> tmp = new HashSet<>();
                tmp.add(node);
                Queue<Integer> queue = new LinkedList<>();
                queue.add(node);
                while (!queue.isEmpty()) {
                    int now = queue.poll();
                    for (int next : rel.get(now)) {
                        if (!used[next]) {
                            used[next] = true;
                            tmp.add(next);
                            queue.add(next);
                        }
                    }
                }
                trees.add(tmp);
            }
        }

        int[] ans = new int[2];

        for (Set<Integer> tree : trees) {
            for (int root : tree) {
                if ((isEven(root) && isEven(rel.get(root).size())) || (isOdd(root) && isOdd(rel.get(root).size()))) {
                    boolean flag = true;
                    for (int child : rel.get(root)) {
                        if (!searchEvenOrOdd(child,root, rel)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        ans[0]++;
                    }
                } else {
                    boolean flag = true;
                    for (int child : rel.get(root)) {
                        if (!searchReverseEvenOrOdd(child,root, rel)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        ans[1]++;
                    }
                }
            }
        }
        return ans;
    }

    private boolean searchEvenOrOdd(int node, int beforeNode, Map<Integer, List<Integer>> rel) {
        if ((isEven(node) && isEven(rel.get(node).size() - 1)) || (isOdd(node) && isOdd(rel.get(node).size() - 1))) {
            for (int child : rel.get(node)) {
                if (child != beforeNode) {
                    if (!searchEvenOrOdd(child, node, rel)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean searchReverseEvenOrOdd(int node, int beforeNode, Map<Integer, List<Integer>> rel) {
        if ((isEven(node) && isOdd(rel.get(node).size() - 1)) || (isOdd(node) && isEven(rel.get(node).size() - 1))) {
            for (int child : rel.get(node)) {
                if (child != beforeNode) {
                    if (!searchReverseEvenOrOdd(child, node, rel)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }

    private boolean isOdd(int num) {
        return num % 2 == 1;
    }

}
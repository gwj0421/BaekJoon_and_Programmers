import java.util.HashSet;
import java.util.Set;

class Solution {
    //    10, new int[][]
//    {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {3, 7, 8, 9, 10}, {2, 5, 7, 9, 10}, {3, 4, 5, 6, 7}},
//            new int[]{2, 3, 4, 3, 3}
    private int ans = 0;

    public int solution(int n, int[][] q, int[] ans) {
        search(1, n, q, ans, new HashSet<>());
        return this.ans;
    }

    public void search(int st, int n, int[][] q, int[] ans, Set<Integer> comb) {
        if (comb.size() == 5) {
            boolean isSuccess = true;
            for (int i = 0; i < q.length; i++) {
                int cnt = 0;
                for (int j = 0; j < 5; j++) {
                    if (comb.contains(q[i][j])) {
                        cnt++;
                    }
                }
                if (cnt != ans[i]) {
                    isSuccess = false;
                    break;
                }
            }
            if (isSuccess) {
                this.ans++;
            }
            return;
        }
        for (int i = st; i <= n; i++) {
            comb.add(i);
            search(i + 1, n, q, ans, comb);
            comb.remove(i);
        }
    }
}
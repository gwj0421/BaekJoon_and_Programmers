import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        Map<Integer, List<Integer>> rel = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            rel.put(i, new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int childIdx = 2; childIdx < n + 1; childIdx++) {
            rel.get(Integer.parseInt(st.nextToken())).add(childIdx);
        }

        Solution solution = new Solution(n,rel);
        solution.solve();

    }

    static class Solution {
        private final int n;
        private final Map<Integer, List<Integer>> rel;
        private int cnt;

        public Solution(int n,Map<Integer, List<Integer>> rel) {
            this.n=n;
            this.rel = rel;
            this.cnt = 0;
        }

        public void solve() {
            dfs(1, false);
            System.out.println(Math.max(cnt,n-cnt));
        }

        private void dfs(int cur, boolean p) {
            if (p) {
                cnt++;
            }
            p = !p;
            for (Integer next : rel.get(cur)) {
                dfs(next, p);
            }
        }

    }

}
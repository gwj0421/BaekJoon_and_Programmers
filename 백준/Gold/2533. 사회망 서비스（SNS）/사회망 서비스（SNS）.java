import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        Map<Integer, List<Integer>> relationship = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            relationship.put(i, new ArrayList<>());
        }
        StringTokenizer st;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            relationship.get(f1).add(f2);
            relationship.get(f2).add(f1);
        }
        EarlyAdopter earlyAdopter = new EarlyAdopter(n, relationship);
        earlyAdopter.search();

    }

    static class EarlyAdopter {
        private int n;
        private Map<Integer, List<Integer>> relationship;
        private int[][] dp;
        private boolean[] visit;

        public EarlyAdopter(int n, Map<Integer, List<Integer>> relationship) {
            this.n = n;
            this.relationship = relationship;
            this.dp = new int[n + 1][2];
            this.visit = new boolean[n + 1];
        }

        void search() {
            dfs(1);
            System.out.println(Math.min(dp[1][0], dp[1][1]));
        }

        void dfs(int num) {
            visit[num] = true;
            dp[num][0] = 0;
            dp[num][1] = 1;

            for (int child : relationship.get(num)) {
                if (!visit[child]) {
                    dfs(child);
                    dp[num][0] += dp[child][1];
                    dp[num][1] += Math.min(dp[child][0], dp[child][1]);
                }
            }
        }
    }
}


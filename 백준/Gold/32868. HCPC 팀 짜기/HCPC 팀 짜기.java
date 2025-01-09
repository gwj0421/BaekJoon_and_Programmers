import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        int n = Integer.parseInt(br.readLine());
        int[] required = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, List<Integer>> tailCntMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            required[i] = Integer.parseInt(st.nextToken());
            if (tailCntMap.containsKey(required[i])) {
                tailCntMap.get(required[i]).add(i);
            } else {
                tailCntMap.put(required[i], new ArrayList<>(List.of(i)));
            }
        }

        Solution solution = new Solution(n, required, tailCntMap);
        solution.solve();
    }

    static class Solution {
        private static final int MOD = 1_000_000_007;
        private final int n;
        private final int[] required;
        private final Map<Integer, List<Integer>> tailCntMap;

        private long ans;


        public Solution(int n, int[] required, Map<Integer, List<Integer>> tailCntMap) {
            this.n = n;
            this.required = required;
            this.tailCntMap = tailCntMap;
            this.ans = 0;
        }

        public void solve() {
            // 전체 solo,
            int[] solo = new int[3];
            int[] duo = new int[2];
            boolean[] visit = new boolean[n + 1];
            int trioCnt = 0;
            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
                    if (isSolo(i)) {
                        visit[i] = true;
                        solo[0] = (solo[0] + 1) % MOD;
                        if (tailCntMap.get(i).size() > 1) {
                            solo[1] = (solo[1] + tailCntMap.get(i).size() - 1) % MOD;
                            // 3번
                            ans = (ans + nC2(tailCntMap.get(i).size() - 1)) % MOD;
                            // 4번
                            for (int firstTail : tailCntMap.get(i)) {
                                if (firstTail != i && tailCntMap.containsKey(firstTail)) {
                                    ans = (ans + tailCntMap.get(firstTail).size() % MOD) % MOD;
                                }
                            }
                        }
                    } else if (isDuo(i)) {
                        visit[i] = true;
                        visit[required[i]] = true;
                        duo[0] = (duo[0] + 1) % MOD;
                        if (tailCntMap.get(i).size() > 1 || tailCntMap.get(required[i]).size() > 1) {
                            duo[1] = (duo[1] + tailCntMap.get(i).size() + tailCntMap.get(required[i]).size() - 2) % MOD;
                        }
                    } else if (isTrio(i)) {
                        visit[i] = true;
                        visit[required[i]] = true;
                        visit[required[required[i]]] = true;
                        trioCnt = (trioCnt + 1) % MOD;
                    }
                }
            }

            // 1번
            ans = (ans + nC3(solo[0])) % MOD;
            // 2번
            ans = (ans + (long) (solo[0] - 1) * solo[1] % MOD) % MOD;
            // 5번
            ans = (ans + (long) duo[0] * solo[0] % MOD) % MOD;
            // 6번
            ans = (ans + duo[1]) % MOD;
            // 7번
            ans = (ans + trioCnt) % MOD;

            System.out.println(ans);


        }

        private boolean isSolo(int i) {
            return i == required[i];
        }

        private boolean isDuo(int i) {
            return i == required[required[i]];
        }

        private boolean isTrio(int i) {
            return i == required[required[required[i]]];
        }

        private int nC2(long value) {
            if (value < 2) {
                return 0;
            }
            return (int) (value * (value - 1) / 2 % MOD);
        }

        private int nC3(long value) {
            if (value < 3) {
                return 0;
            }
            return (int) (value * (value - 1) * (value - 2) / 6 % MOD);
        }
    }
}
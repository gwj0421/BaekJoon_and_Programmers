import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // VI(RUS) RU(ST) STAND = 9
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        Vaccine vaccine = new Vaccine(n, words);
        vaccine.generate();
    }

    static class Vaccine {
        private final int n;
        private final String[] words;
        private String ans;

        public Vaccine(int n, String[] words) {
            this.n = n;
            this.words = words;
            StringBuilder sb = new StringBuilder();
            ans = "A".repeat(100);
        }

        public void generate() {
            for (int i = 0; i < n; i++) {
                boolean[] visit = new boolean[n];
                visit[i] = true;
                dfs(1, words[i], visit);
            }
            System.out.println(ans.length());
        }

        private void dfs(int depth, String comb, boolean[] visit) {
            if (depth == n) {
                if (comb.length() < ans.length()) {
                    ans = comb;
                }
                return;
            }
            for (int i = 0; i < n; i++) {
                if (!visit[i]) {
                    int combineIdx = combine(comb, words[i]);
                    if (combineIdx > 0) {
                        visit[i] = true;
                        dfs(depth + 1, comb + words[i].substring(combineIdx), visit);
                        visit[i] = false;
                    }
                }
            }
        }

        private static int combine(String a, String b) {
            int length = Math.min(a.length(), b.length());
            for (int i = length; i > 0; i--) {
                if (a.endsWith(b.substring(0, i))) {
                    return i;
                }
            }
            return 0;
        }

    }
}



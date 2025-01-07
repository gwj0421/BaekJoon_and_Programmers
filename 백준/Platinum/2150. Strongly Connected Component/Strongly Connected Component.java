import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int[][] conditions = new int[e][2];
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            conditions[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Kosariju kosariju = new Kosariju(v, conditions);
        kosariju.solve();
    }

    static class Kosariju {
        private final int n;
        private final Map<Integer, List<Integer>> rel;
        private final Map<Integer, List<Integer>> revRel;
        private Stack<Integer> path;

        public Kosariju(int n, int[][] conditions) {
            HashMap<Integer, List<Integer>> rel = new HashMap<>();
            HashMap<Integer, List<Integer>> revRel = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                rel.put(i, new ArrayList<>());
                revRel.put(i, new ArrayList<>());
            }
            for (int[] condition : conditions) {
                rel.get(condition[0]).add(condition[1]);
                revRel.get(condition[1]).add(condition[0]);
            }
            this.n = n;
            this.rel = rel;
            this.revRel = revRel;
            this.path = new Stack<>();
        }
        
        public void solve() {
            boolean[] visit = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    dfs(i, visit);
                }
            }

            visit = new boolean[n + 1];
            List<List<Integer>> ans = new ArrayList<>();
            while (!path.isEmpty()) {
                int st = path.pop();
                if (!visit[st]) {
                    visit[st] = true;
                    List<Integer> footprint = new ArrayList<>();
                    revDfs(st, visit, footprint);
                    ans.add(footprint);
                }
            }

            ans.forEach(it -> it.sort(Comparator.naturalOrder()));
            ans.sort(Comparator.comparingInt(it -> it.get(0)));
            System.out.println(ans.size());

            for (List<Integer> an : ans) {
                an.add(-1);
                System.out.println(an.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            }
        }

        private void dfs(int u, boolean[] visit) {
            for (int v : rel.get(u)) {
                if (!visit[v]) {
                    visit[v] = true;
                    dfs(v, visit);
                }
            }
            path.add(u);
        }

        private void revDfs(int u, boolean[] visit, List<Integer> footprint) {
            footprint.add(u);
            for (int v : revRel.get(u)) {
                if (!visit[v]) {
                    visit[v] = true;
                    revDfs(v, visit, footprint);
                }
            }
        }


    }
}
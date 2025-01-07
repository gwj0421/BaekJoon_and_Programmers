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
        int[][] graph = new int[e][2];
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Kosariju kosariju = new Kosariju(v, e, graph);
        kosariju.solve();
    }

    /**
     * 코사라주 알고리즘
     * 1. 전체 정점에 대해 DFS 수행, 탐색을 마친 순서 저장
     * 2. 해당 순서의 역순으로(마지막으로 마친 순서대로) 역방향 DFS 수행
     */

    static class Kosariju {
        private final int v;
        private final int e;
        private final Map<Integer, List<Integer>> forwardGraph;
        private final Map<Integer, List<Integer>> reverseGraph;

        public Kosariju(int v, int e, int[][] graph) {
            this.v = v;
            this.e = e;
            Map<Integer, List<Integer>> forwardGraph = new HashMap<>();
            Map<Integer, List<Integer>> reverseGraph = new HashMap<>();
            for (int i = 1; i <= v; i++) {
                forwardGraph.put(i, new ArrayList<>());
                reverseGraph.put(i, new ArrayList<>());
            }
            for (int[] edge : graph) {
                forwardGraph.get(edge[0]).add(edge[1]);
                reverseGraph.get(edge[1]).add(edge[0]);
            }
            this.forwardGraph = forwardGraph;
            this.reverseGraph = reverseGraph;
        }

        public void solve() {
            boolean[] visit = new boolean[v + 1];
            List<Integer> forwardPathOrderBySearchEnd = new ArrayList<>();
            for (int i = 1; i <= v; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    dfs(i, visit, forwardPathOrderBySearchEnd);
                }
            }

            visit = new boolean[v + 1];
            List<List<Integer>> sccGroups = new ArrayList<>();
            List<Integer> sccPath;
            int st;
            for (int i = forwardPathOrderBySearchEnd.size() - 1; i > -1; i--) {
                st = forwardPathOrderBySearchEnd.get(i);
                if (!visit[st]) {
                    visit[st] = true;
                    sccPath = new ArrayList<>();
                    reverseDfs(st, visit, sccPath);
                    sccGroups.add(sccPath);
                }
            }
            for (List<Integer> sccGroup : sccGroups) {
                sccGroup.sort(Comparator.naturalOrder());
            }
            sccGroups.sort(Comparator.comparingInt(it -> it.get(0)));

            System.out.println(sccGroups.size());
            for (List<Integer> sccGroup : sccGroups) {
                sccGroup.add(-1);
                System.out.println(sccGroup.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            }
        }

        private void dfs(int u, boolean[] visit, List<Integer> footprint) {
            for (int v : forwardGraph.get(u)) {
                if (!visit[v]) {
                    visit[v] = true;
                    dfs(v, visit, footprint);
                }
            }
            footprint.add(u);
        }

        private void reverseDfs(int u, boolean[] visit, List<Integer> footprint) {
            footprint.add(u);
            for (int v : reverseGraph.get(u)) {
                if (!visit[v]) {
                    visit[v] = true;
                    reverseDfs(v, visit, footprint);
                }
            }
        }
    }
}
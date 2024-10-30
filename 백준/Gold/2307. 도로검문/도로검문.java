import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, List<int[]>> rel = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            rel.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            rel.get(array[0]).add(new int[]{array[1], array[2]});
            rel.get(array[1]).add(new int[]{array[0], array[2]});
        }

        Dijkstra dijkstra = new Dijkstra(n, rel);
        dijkstra.printDiff();
    }

    static class Dijkstra {
        private final int n;
        private final Map<Integer, List<int[]>> rel;
        private int[] path;
        private int[] costTable;
        private PriorityQueue<int[]> pq;

        public Dijkstra(int n, Map<Integer, List<int[]>> rel) {
            this.n = n;
            this.rel = rel;
            this.path = new int[n + 1];
            this.costTable = new int[n + 1];
            this.pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1],o2[1]));
        }

        public void printDiff() {
            int minCost = dijkstra();
            int cost;
            int diff = 0;
            for (int[] policeEdge : extractPath()) {
                cost = dijkstraWithPolice(policeEdge);
                if (cost == Integer.MAX_VALUE) {
                    System.out.println(-1);
                    return;
                }
                diff = Math.max(diff, cost - minCost);
            }
            System.out.println(diff);
        }

        private int dijkstra() {
            Arrays.fill(costTable, Integer.MAX_VALUE);
            costTable[1] = 0;
            pq.clear();
            pq.add(new int[]{1, 0});
            while (!pq.isEmpty()) {
                int[] now = pq.poll();
                if (now[1] > costTable[now[0]]) {
                    continue;
                }
                for (int[] next : rel.get(now[0])) {
                    if (costTable[next[0]] > now[1] + next[1]) {
                        costTable[next[0]] = now[1] + next[1];
                        pq.add(new int[]{next[0], now[1] + next[1]});

                        path[next[0]] = now[0];
                    }
                }
            }
            return costTable[n];
        }

        private int dijkstraWithPolice(int[] policeEdge) {
            Arrays.fill(costTable, Integer.MAX_VALUE);
            costTable[1] = 0;
            pq.clear();
            pq.add(new int[]{1, 0});
            while (!pq.isEmpty()) {
                int[] now = pq.poll();
                if (now[1] > costTable[now[0]]) {
                    continue;
                }
                for (int[] next : rel.get(now[0])) {
                    if (!isEqualToEdge(now[0], next[0], policeEdge) && costTable[next[0]] > now[1] + next[1]) {
                        costTable[next[0]] = now[1] + next[1];
                        pq.add(new int[]{next[0], now[1] + next[1]});
                    }
                }
            }

            return costTable[n];
        }

        private Set<int[]> extractPath() {
            Set<int[]> route = new HashSet<>();
            int idx = n;
            while (idx != 1) {
                route.add(new int[]{path[idx], idx});
                idx = path[idx];
            }
            return route;
        }

        private boolean isEqualToEdge(int p1, int p2, int[] p3) {
            return (p1 == p3[0] && p2 == p3[1]) || (p1 == p3[1] && p2 == p3[0]);
        }
    }
}



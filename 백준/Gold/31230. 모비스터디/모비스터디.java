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
        int mingyeomCityIdx = Integer.parseInt(st.nextToken());
        int sieunIdx = Integer.parseInt(st.nextToken());

        Map<Integer, List<City>> roads = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            roads.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            roads.get(a).add(new City(b, c));
            roads.get(b).add(new City(a, c));
        }

        Algorithm algorithm = new Algorithm(n, m, mingyeomCityIdx, sieunIdx, roads);
        algorithm.solve();
    }

    static class Algorithm {
        private final int n;
        private final int m;
        private final int mingyeomCityIdx;
        private final int sieunIdx;
        private final Map<Integer, List<City>> roads;

        public Algorithm(int n, int m, int mingyeomCityIdx, int sieunIdx, Map<Integer, List<City>> roads) {
            this.n = n;
            this.m = m;
            this.mingyeomCityIdx = mingyeomCityIdx;
            this.sieunIdx = sieunIdx;
            this.roads = roads;
        }

        public void solve() {
            long[] mingyeom = dijkstra(mingyeomCityIdx);
            long[] sieun = dijkstra(sieunIdx);

            long shortestCost = mingyeom[sieunIdx];
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            for (int i = 1; i < n + 1; i++) {
                if (mingyeom[i] + sieun[i] == shortestCost) {
                    cnt++;
                    sb.append(i).append(" ");
                }
            }
            System.out.println(cnt);
            System.out.println(sb);
        }

        private long[] dijkstra(int startIdx) {
            long[] costTable = new long[n + 1];
            Arrays.fill(costTable, Long.MAX_VALUE);
            costTable[startIdx] = 0L;

            PriorityQueue<City> pq = new PriorityQueue<>();
            pq.add(new City(startIdx, 0));

            long nextCost;
            while (!pq.isEmpty()) {
                City cur = pq.poll();
                if (costTable[cur.getIdx()] < cur.getCost()) {
                    continue;
                }

                for (City nextCity : roads.get(cur.getIdx())) {
                    nextCost = cur.getCost() + nextCity.getCost();
                    if (nextCost < costTable[nextCity.getIdx()]) {
                        costTable[nextCity.getIdx()] = nextCost;
                        pq.add(new City(nextCity.getIdx(), nextCost));
                    }
                }
            }

            return costTable;
        }

    }


    static class City implements Comparable<City> {
        private final int idx;
        private final long cost;

        public City(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public int getIdx() {
            return idx;
        }

        public long getCost() {
            return cost;
        }

        @Override
        public int compareTo(City o) {
            return Long.compare(this.cost, o.getCost());
        }
    }

}
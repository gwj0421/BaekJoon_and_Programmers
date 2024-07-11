import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int MAX_INT = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] eachLine = br.readLine().split(" ");
        int n = Integer.parseInt(eachLine[0]);
        int m = Integer.parseInt(eachLine[1]);
        int r = Integer.parseInt(eachLine[2]);
        int[] item = new int[n + 1];
        eachLine = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            item[i+1] = Integer.parseInt(eachLine[i]);
        }
        Map<Integer, List<int[]>> itemMap = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            itemMap.put(i, new ArrayList());
        }
        for (int i = 0; i < r; i++) {
            eachLine = br.readLine().split(" ");
            int st = Integer.parseInt(eachLine[0]);
            int en = Integer.parseInt(eachLine[1]);
            int di = Integer.parseInt(eachLine[2]);
            itemMap.get(st).add(new int[]{en, di});
            itemMap.get(en).add(new int[]{st, di});
        }
        int[][] distance = new int[n + 1][n + 1];
        Arrays.fill(distance[0], MAX_INT);
        for (int i = 1; i < n + 1; i++) {
            Arrays.fill(distance[i], MAX_INT);
            distance[i][i] = 0;
            PriorityQueue<Area> heap = new PriorityQueue<>();
            heap.add(new Area(i, 0));
            while (!heap.isEmpty()) {
                Area now = heap.poll();
                if (distance[i][now.getIdx()] < now.getDis()) {
                    continue;
                }
                for (int[] around : itemMap.get(now.getIdx())) {
                    Area next = new Area(around[0], now.getDis() + around[1]);
                    if (distance[i][around[0]] > next.getDis()) {
                        distance[i][around[0]] = next.getDis();
                        heap.add(next);
                    }
                }
            }
        }

        int maxFarmingItemCnt = 0;
        for (int i = 1; i < n+1; i++) {
            int itemCnt = item[i];
            for (int j = 1; j < n+1; j++) {
                if (distance[i][j] <= m && i != j) {
                    itemCnt += item[j];
                }
            }
            maxFarmingItemCnt = Math.max(maxFarmingItemCnt, itemCnt);
        }
        System.out.println(maxFarmingItemCnt);
    }

    static class Area implements Comparable<Area>{
        private int idx;
        private int dis;

        public Area(int idx, int dis) {
            this.idx = idx;
            this.dis = dis;
        }

        public int getIdx() {
            return idx;
        }

        public int getDis() {
            return dis;
        }

        @Override
        public int compareTo(Area o) {
            if (this.dis < o.dis) {
                return 1;
            }
            return -1;
        }
    }
}

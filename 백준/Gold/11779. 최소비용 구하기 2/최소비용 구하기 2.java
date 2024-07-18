import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] eachLine;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        Map<Integer, List<PosInfo>> roadMap = new HashMap<>();
        for (int i = 0; i < n + 1; i++) {
            roadMap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            eachLine = br.readLine().split(" ");
            int st = Integer.parseInt(eachLine[0]);
            int en = Integer.parseInt(eachLine[1]);
            int co = Integer.parseInt(eachLine[2]);
            roadMap.get(st).add(new PosInfo(en, co));
        }
        eachLine = br.readLine().split(" ");
        int start = Integer.parseInt(eachLine[0]);
        int end = Integer.parseInt(eachLine[1]);

        BusAlgorithm.searchWithDijkstra(n, roadMap, start, end);

    }

    static class BusAlgorithm {
        public static void searchWithDijkstra(int n, Map<Integer, List<PosInfo>> roadMap, int start, int end) {
            int[] costTable = new int[n + 1];
            Arrays.fill(costTable, Integer.MAX_VALUE);
            costTable[start] = 0;
            String[] routeTable = new String[n + 1];
            routeTable[start] = start + " ";
            Queue<Route> queue = new PriorityQueue<>();
            queue.add(new Route(start, 0));

            while (!queue.isEmpty()) {
                Route nowRoute = queue.poll();
                if (costTable[nowRoute.getPos()] < nowRoute.getCost()) {
                    continue;
                }
                for (PosInfo next : roadMap.get(nowRoute.getPos())) {
                    if (costTable[nowRoute.getPos()] + next.getCost() < costTable[next.getPos()]) {
                        Route nextRoute = new Route(nowRoute, next);
                        costTable[nextRoute.getPos()] = nextRoute.getCost();
                        routeTable[nextRoute.getPos()] = nextRoute.getRoute();
                        queue.add(nextRoute);
                    }
                }
            }
            System.out.println(costTable[end]);
            System.out.println(routeTable[end].split(" ").length);
            System.out.println(routeTable[end].trim());
        }

        static class Route extends PosInfo implements Comparable<Route> {
            private String route;

            public Route(int pos, int cost) {
                super(pos, cost);
                this.route = pos + " ";
            }

            public Route(Route originRoute, PosInfo nextPos) {
                super(nextPos.getPos(), originRoute.getCost() + nextPos.getCost());
                this.route = originRoute.getRoute() + nextPos.getPos() + " ";
            }

            public String getRoute() {
                return route;
            }

            @Override
            public int compareTo(Route o) {
                return this.getCost() - o.getCost();
            }
        }

    }

    static class PosInfo {
        private int pos;
        private int cost;

        public PosInfo(int pos, int cost) {
            this.pos = pos;
            this.cost = cost;
        }

        public int getPos() {
            return pos;
        }

        public int getCost() {
            return cost;
        }
    }
}

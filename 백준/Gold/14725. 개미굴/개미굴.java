import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        Route[] routes = new Route[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int roomCnt = Integer.parseInt(st.nextToken());
            String[] tmp = new String[roomCnt];
            for (int j = 0; j < roomCnt; j++) {
                tmp[j] = st.nextToken();
            }
            routes[i] = new Route(roomCnt, tmp);
        }
        Arrays.sort(routes);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < routes[0].getRoomCnt(); i++) {
            sb.append("--".repeat(i)).append(routes[0].getRouteInfo()[i]).append("\n");
        }
        for (int i = 1; i < n; i++) {
            Route beforeRoute = routes[i - 1];
            Route nowRoute = routes[i];
            int tmp = Math.min(beforeRoute.getRoomCnt(), nowRoute.getRoomCnt());
            for (int j = 0; j < tmp; j++) {
                if (!beforeRoute.getRouteInfo()[j].equals(nowRoute.getRouteInfo()[j])) {
                    for (int k = j; k < nowRoute.getRoomCnt(); k++) {
                        sb.append("--".repeat(k)).append(nowRoute.getRouteInfo()[k]).append("\n");
                    }
                    break;
                }
            }
        }
        System.out.println(sb);
    }

    static class Route implements Comparable<Route> {
        private final int roomCnt;
        private final String[] routeInfo;

        public Route(int roomCnt, String[] routeInfo) {
            this.roomCnt = roomCnt;
            this.routeInfo = routeInfo;
        }

        public int getRoomCnt() {
            return roomCnt;
        }

        public String[] getRouteInfo() {
            return routeInfo;
        }

        @Override
        public int compareTo(Route o) {
            for (int i = 0; i < Math.min(this.roomCnt, o.getRoomCnt()); i++) {
                if (!this.routeInfo[i].equals(o.routeInfo[i])) {
                    return this.routeInfo[i].compareTo(o.routeInfo[i]);
                }
            }
            return Integer.compare(this.roomCnt, o.getRoomCnt());
        }
    }

}
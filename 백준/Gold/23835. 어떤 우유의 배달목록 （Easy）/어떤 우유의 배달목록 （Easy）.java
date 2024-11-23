import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        Map<Integer, List<Integer>> route = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            route.put(i, new ArrayList<>());
        }
        StringTokenizer st;
        int ai, bi;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            ai = Integer.parseInt(st.nextToken());
            bi = Integer.parseInt(st.nextToken());
            route.get(ai).add(bi);
            route.get(bi).add(ai);
        }

        DeliveryService deliveryService = new DeliveryService(n, route);
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")) {
                deliveryService.delivery(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {
                deliveryService.showResult(Integer.parseInt(st.nextToken()));
            }
        }
    }

    static class DeliveryService {
        private int n;
        private Map<Integer, List<Integer>> route;
        private int[] result;

        public DeliveryService(int n, Map<Integer, List<Integer>> route) {
            this.n = n;
            this.route = route;
            this.result = new int[n + 1];
        }


        public void delivery(int st, int en) {
            dfs(st, 0, new boolean[n + 1], en);
        }

        private boolean dfs(int node, int depth, boolean[] visit, int en) {
            visit[node] = true;
            if (node == en) {
                result[node] += depth;
                return true;
            }

            for (int nextNode : route.get(node)) {
                if (!visit[nextNode]) {
                    if (dfs(nextNode, depth + 1, visit, en)) {
                        result[node] += depth;
                        return true;
                    }
                }
            }
            return false;
        }

        public void showResult(int xi) {
            System.out.println(result[xi]);
        }
    }
}



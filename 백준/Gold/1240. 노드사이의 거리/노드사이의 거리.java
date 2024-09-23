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

        Map<Integer, List<int[]>> connections = new HashMap<>();
        int[][] costTable = new int[n+1][n+1];
        for (int i = 1; i < n+1; i++) {
            connections.put(i, new ArrayList<>());
            Arrays.fill(costTable[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            connections.get(p1).add(new int[]{p2, cost});
            connections.get(p2).add(new int[]{p1, cost});
        }


        for (int i = 1; i < n + 1; i++) {
            costTable[i][i] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
            pq.add(new int[]{i, 0});
            while (!pq.isEmpty()) {
                int[] now = pq.poll();
                for (int[] next : connections.get(now[0])) {
                    if (now[1] + next[1] < costTable[i][next[0]]) {
                        pq.add(new int[]{next[0], now[1] + next[1]});
                        costTable[i][next[0]] = now[1] + next[1];
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int q1 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());
            System.out.println(costTable[q1][q2]);
        }
    }


}
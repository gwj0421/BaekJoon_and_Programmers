import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Party {
    private final int n;
    private final int x;
    private final Map<Integer, List<int[]>> roadInfo;

    public Party(int n, int x, Map<Integer, List<int[]>> roadInfo) {
        this.n = n;
        this.x = x;
        this.roadInfo = roadInfo;
    }

    public int getN() {
        return n;
    }
    
    public int getX() {
        return x;
    }

    public Map<Integer, List<int[]>> getRoadInfo() {
        return roadInfo;
    }

    public void start() {
        int[][] situation = new int[getN()+1][];
        for (int i = 1; i < getN() + 1; i++) {
            situation[i] = dijkstra(i);
        }
        int maxValue = -1;
        for (int i = 1; i < getN() + 1; i++) {
            maxValue = Math.max(maxValue, situation[i][getX()] + situation[getX()][i]);
        }
        System.out.println(maxValue);
    }

    private int[] dijkstra(int startNode) {
        int[] result = new int[getN() + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[startNode] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{startNode, 0});
        while (!pq.isEmpty()) {
            int[] nowInfo = pq.poll();
            for (int[] nextInfo : getRoadInfo().get(nowInfo[0])) {
                if (nowInfo[1] + nextInfo[1] < result[nextInfo[0]]) {
                    result[nextInfo[0]] = nowInfo[1] + nextInfo[1];
                    pq.add(new int[]{nextInfo[0], nowInfo[1] + nextInfo[1]});
                }
            }
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        Map<Integer, List<int[]>> roadInfo = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            if (roadInfo.containsKey(start)) {
                roadInfo.get(start).add(new int[]{end, time});
            } else {
                roadInfo.put(start, new ArrayList<>(List.of(new int[]{end, time})));
            }
        }
        Party party = new Party(n, x, roadInfo);
        party.start();

    }
}
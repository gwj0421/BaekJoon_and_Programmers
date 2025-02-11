import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int robotAIdx = Integer.parseInt(st.nextToken());
        int robotBIdx = Integer.parseInt(st.nextToken());

        Map<Integer, List<int[]>> edges = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            edges.put(i, new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int e1 = Integer.parseInt(st.nextToken());
            int e2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.get(e1).add(new int[]{e2, cost});
            edges.get(e2).add(new int[]{e1, cost});
        }

        if (n == 1 || robotAIdx == robotBIdx) {
            System.out.println(0);
            return;
        }

        int[] robotA = new int[n + 1];
        Arrays.fill(robotA, -1);
        robotA[robotAIdx] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(robotAIdx);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int[] next : edges.get(now)) {
                if (robotA[next[0]] < 0) {
                    robotA[next[0]] = robotA[now] + next[1];
                    queue.add(next[0]);
                }
            }
        }
        long ans = Long.MAX_VALUE;
        int[] robotB = new int[n + 1];
        Arrays.fill(robotB, -1);
        robotB[robotBIdx] = 0;
        queue = new LinkedList<>();
        queue.add(robotBIdx);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int[] next : edges.get(now)) {
                if (robotB[next[0]] < 0) {
                    ans = Math.min(ans, robotA[next[0]] + robotB[now]);
                    robotB[next[0]] = robotB[now] + next[1];
                    queue.add(next[0]);
                }
            }
        }
        System.out.println(ans);
    }
}
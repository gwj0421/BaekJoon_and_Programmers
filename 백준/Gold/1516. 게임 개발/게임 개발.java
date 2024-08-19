import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int n = Integer.parseInt(br.readLine());
        Map<Integer, List<Integer>> relationship = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            relationship.put(i, new ArrayList<>());
        }
        StringTokenizer st;
        int[] inDegree = new int[n + 1];
        int[] buildTime = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            buildTime[i] = Integer.parseInt(st.nextToken());
            int require;
            while (st.hasMoreTokens()) {
                String input = st.nextToken();
                if (input.equals("-1")) {
                    break;
                }
                require = Integer.parseInt(input);
                inDegree[i]++;
                relationship.get(require).add(i);
            }
        }

        int[] timeTable = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : relationship.get(now)) {
                timeTable[next] = Math.max(timeTable[next], timeTable[now] + buildTime[now]);
                if (--inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            System.out.println(timeTable[i] + buildTime[i]);
        }
    }
}

